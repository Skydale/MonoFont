package io.github.mg138.monofont

import eu.pb4.polymer.api.resourcepack.PolymerRPUtils
import io.github.mg138.monofont.command.IconTable
import net.fabricmc.api.DedicatedServerModInitializer
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.block.Block
import net.minecraft.block.entity.BlockEntity
import net.minecraft.server.command.CommandManager.literal
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

@Suppress("UNUSED")
object Main : DedicatedServerModInitializer {
    const val modId = "mono_font"
    val logger: Logger = LogManager.getLogger(modId)

    override fun onInitializeServer() {
        PolymerRPUtils.addAssetSource(modId)
        IconTable.register()

        logger.info("Registered mono font.")
    }
}