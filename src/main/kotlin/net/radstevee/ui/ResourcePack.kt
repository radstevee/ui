package net.radstevee.ui

import net.radstevee.packed.core.asset.impl.SourceDirectoryAssetResolutionStrategy
import net.radstevee.packed.core.pack.PackFormat
import net.radstevee.packed.core.pack.ResourcePackBuilder.Companion.resourcePack
import java.io.File

object ResourcePack {
    val pack = resourcePack {
        meta {
            description = "radstevee/ui test pack"
            format = PackFormat.LATEST
            outputDir = File("/home/radsteve/Minecraft/Additive 1.21/minecraft/resourcepacks/ui")
        }

        assetResolutionStrategy = SourceDirectoryAssetResolutionStrategy(meta.outputDir)
    }

    const val NAMESPACE = "ui"
}