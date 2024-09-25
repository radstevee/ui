package net.radstevee.ui

import com.github.shynixn.mccoroutine.bukkit.SuspendingJavaPlugin
import io.papermc.paper.command.brigadier.CommandSourceStack
import net.radstevee.ui.ResourcePack.pack
import net.radstevee.ui.cutscene.Cutscene
import net.radstevee.ui.cutscene.Cutscenes
import net.radstevee.ui.cutscene.Frame
import net.radstevee.ui.util.image
import org.bukkit.entity.Player
import org.incendo.cloud.brigadier.BrigadierSetting
import org.incendo.cloud.execution.ExecutionCoordinator.asyncCoordinator
import org.incendo.cloud.kotlin.extension.buildAndRegister
import org.incendo.cloud.paper.PaperCommandManager
import org.incendo.cloud.parser.standard.StringParser
import org.incendo.cloud.parser.standard.StringParser.quotedStringParser
import java.time.Duration
import kotlin.io.path.Path

object UiPlugin : SuspendingJavaPlugin() {
    override suspend fun onEnableAsync() {
        commandManager = PaperCommandManager.builder().executionCoordinator(asyncCoordinator()).buildOnEnable(this)
        val brigSettings = commandManager.brigadierManager().settings()
        brigSettings.set(BrigadierSetting.FORCE_EXECUTABLE, true)

        commandManager.buildAndRegister("createFrame") {
            required("cutsceneId", quotedStringParser())
            required("id", quotedStringParser())
            required("path", quotedStringParser())

            handler { ctx ->
                val player = ctx.sender().sender as Player
                val cutsceneId = ctx.get<String>("cutsceneId")
                val file = Path(ctx.get("path")).toFile()
                val id = ctx.get<String>("id")

                val cutscene = Cutscene(cutsceneId, listOf(Frame(cutsceneId, id, file.image(), Duration.ofSeconds(2))))
                cutscene.addToPack()
                pack.save()
            }
        }

        Cutscenes.TEST_CUTSCENE.addToPack()
        pack.save()
    }

    override suspend fun onDisableAsync() {}

    lateinit var commandManager: PaperCommandManager<CommandSourceStack>
}
