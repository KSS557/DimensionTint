package net.kss557.dimensiontint

import net.fabricmc.api.ModInitializer
import net.kss557.dimensiontint.config.ModConfigs
import net.kss557.dimensiontint.tab.ColorTab
import net.minecraft.resources.Identifier
import org.slf4j.LoggerFactory

object DimensionTint : ModInitializer {
	const val MOD_ID: String = "dimensiontint"

	val LOGGER = LoggerFactory.getLogger(MOD_ID)

	override fun onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!")
		ModConfigs.registerConfigs()
		ColorTab.registerColor()
	}

	fun id(path: String): Identifier = Identifier.fromNamespaceAndPath(MOD_ID, path)
}
