package net.radstevee.ui.cutscene

import net.kyori.adventure.title.Title
import net.radstevee.packed.core.key.Key
import net.radstevee.ui.ResourcePack.NAMESPACE
import net.radstevee.ui.ResourcePack.pack
import org.bukkit.entity.Player
import java.io.File
import javax.imageio.ImageIO

class Cutscene(val id: String, val frames: List<Frame>) {
    fun bake() = frames.map(Frame::bakeParts)

    fun addToPack() {
        val baked = bake()
        baked.forEachIndexed { frameId, images ->
            val frame = frames[frameId]
            val dir = File(pack.outputDir, "assets/$NAMESPACE/textures/cutscenes/$id/frame-${frame.id}")
            dir.mkdirs()

            pack.addFont {
                key = Key(NAMESPACE, "cutscenes/$id/frame-${frame.id}")

                images.forEachIndexed { partId, (image, glyph) ->
                    val output = File(dir, "part-$partId.png")
                    ImageIO.write(image, "png", output)

                    bitmap {
                        key = Key(NAMESPACE, "cutscenes/$id/frame-${frame.id}/part-$partId.png")
                        height = 128.0
                        ascent = 64.0
                        chars = listOf(glyph.char().toString())
                    }
                }
            }
        }
    }

    fun display(player: Player) {
        frames.forEach { frame ->

        }
    }
}