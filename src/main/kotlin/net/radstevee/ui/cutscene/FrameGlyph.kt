package net.radstevee.ui.cutscene

import net.kyori.adventure.key.Key.key
import net.radstevee.ui.ResourcePack.NAMESPACE
import org.apache.commons.lang.StringEscapeUtils

data class FrameGlyph(val cutsceneId: String, val id: String, val partId: Int) {
    fun font() = key(NAMESPACE, "cutscene/$cutsceneId/frame_$id")
    fun char() = StringEscapeUtils.unescapeJava(String.format("\\uE%03d", partId)).first()
}
