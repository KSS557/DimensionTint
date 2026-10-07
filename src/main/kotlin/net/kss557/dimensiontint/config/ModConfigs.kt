package net.kss557.dimensiontint.config

import com.mojang.datafixers.util.Pair
import net.kss557.dimensiontint.DimensionTint
import kotlin.collections.iterator

object ModConfigs {

    lateinit var CONFIG: SimpleConfig
        private set

    private lateinit var configs: ModConfigProvider

    const val DEFAULT_COLOR_KEY: String = "default"

    private val DEFAULT_WORLD_COLORS = linkedMapOf(
        DEFAULT_COLOR_KEY       to "&f",
        "minecraft:overworld"  to "&a",
        "minecraft:the_nether" to "&c",
        "minecraft:the_end"    to "&5"
    )

    val worldColors: MutableMap<String, String> = LinkedHashMap()

    fun registerConfigs() {
        configs = ModConfigProvider()
        createConfigs()
        CONFIG = SimpleConfig.of(DimensionTint.MOD_ID + "config").provider(configs).request()
        assignConfigs()
    }

    private fun createConfigs() {
        for ((dimId, color) in DEFAULT_WORLD_COLORS) {
            configs.addKeyValuePair(
                Pair.of(dimId, color),
                if (dimId == DEFAULT_COLOR_KEY) {
                    "Color used for dimensions without their own entry"
                } else {
                    "Color for $dimId"
                }
            )
        }
    }

    private fun assignConfigs() {
        worldColors.putAll(DEFAULT_WORLD_COLORS)

        for (key in CONFIG.getAllKeys()) {
            val color = CONFIG.getOrDefault(key, "White")
            worldColors[key] = color
        }
    }

    fun getColorNameForDimension(dimensionId: String): String? {
        return worldColors[dimensionId] ?: worldColors[DEFAULT_COLOR_KEY]
    }
}