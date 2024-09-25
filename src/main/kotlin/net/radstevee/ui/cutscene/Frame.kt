package net.radstevee.ui.cutscene

import net.radstevee.ui.util.split
import java.awt.image.BufferedImage
import java.time.Duration

class Frame(val cutsceneId: String, val id: String, val image: BufferedImage, val timeDisplayed: Duration) {
    fun bakeParts() = image.split(512, 512).mapIndexed { i, it -> it to FrameGlyph(cutsceneId, id, i) }
}