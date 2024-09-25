package net.radstevee.ui.util

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

fun BufferedImage.split(tileHeight: Int, tileWidth: Int): List<BufferedImage> {
    val rows = (height + tileHeight - 1) / tileHeight
    val columns = (width + tileWidth - 1) / tileWidth
    val images = mutableListOf<BufferedImage>()

    for (y in 0 until rows) {
        for (x in 0 until columns) {
            val startX = x * tileWidth
            val startY = y * tileHeight
            val width = if (startX + tileWidth > width) width - startX else tileWidth
            val height = if (startY + tileHeight > height) height - startY else tileHeight
            val subImage = getSubimage(startX, startY, width, height)
            images.add(subImage)
        }
    }

    return images
}

fun File.image() = ImageIO.read(this)