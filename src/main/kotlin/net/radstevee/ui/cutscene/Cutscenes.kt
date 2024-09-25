package net.radstevee.ui.cutscene

import net.radstevee.ui.util.image
import java.io.File
import java.time.Duration

object Cutscenes {
    val TEST_CUTSCENE = Cutscene("test", listOf(Frame("test", "mountains", File("/home/radsteve/Pictures/wallpapers/Montains.png").image(), Duration.ofSeconds(1))))
}