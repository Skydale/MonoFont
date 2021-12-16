package io.github.mg138.monofont

import io.github.mg138.rloader.resource.ResourceHelper
import net.fabricmc.api.DedicatedServerModInitializer
import net.fabricmc.loader.api.FabricLoader
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

@Suppress("UNUSED")
object Main : DedicatedServerModInitializer {
    const val modId = "mono_font"
    val logger: Logger = LogManager.getLogger(modId)
    val fabricLoader = FabricLoader.getInstance()

    override fun onInitializeServer() {
        val font = fabricLoader.getModContainer(modId).get()
            .getPath("font")

        ResourceHelper.register(font)

        logger.info("Registered mono font.")
    }
}