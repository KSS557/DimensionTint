package net.kss557.dimensiontint.tab

import net.fabricmc.fabric.api.entity.event.v1.ServerEntityLevelChangeEvents
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents
import net.fabricmc.fabric.api.networking.v1.PlayerLookup
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents
import net.kss557.dimensiontint.DimensionTint
import net.kss557.dimensiontint.config.ModConfigs
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.MutableComponent
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket
import net.minecraft.server.MinecraftServer
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.scores.PlayerTeam
import java.util.*

object ColorTab {

    private var dirty = true

    fun registerColor() {
        DimensionTint.LOGGER.info("Registering color for: " + DimensionTint.MOD_ID)

        ServerPlayConnectionEvents.JOIN.register { _, _, _ -> dirty = true }
        ServerEntityLevelChangeEvents.AFTER_PLAYER_CHANGE_LEVEL.register { _, _, _ -> dirty = true }

        ServerTickEvents.END_SERVER_TICK.register { server ->
            if (dirty) {
                dirty = false

                refreshTabNames(server)
            }
        }
    }

    @JvmStatic
    fun tabDisplayName(player: ServerPlayer): Component? {
        val value = ModConfigs.getColorNameForDimension(dimensionOf(player)) ?: return null
        val base: MutableComponent = PlayerTeam.formatNameForTeam(
            player.team,
            Component.literal(player.gameProfile.name)
        )

        return style(base, value)
    }

    private fun refreshTabNames(server: MinecraftServer) {
        val online = PlayerLookup.all(server)

        if (online.isEmpty()) return

        val packet = ClientboundPlayerInfoUpdatePacket(
            EnumSet.of(ClientboundPlayerInfoUpdatePacket.Action.UPDATE_DISPLAY_NAME),
            online
        )

        server.playerList.broadcastAll(packet)
    }

    private fun dimensionOf(player: ServerPlayer): String = player.level().dimension().identifier().toString()

    private fun style(base: MutableComponent, value: String): MutableComponent {
        val raw = value.trim().lowercase()

        if (raw.isEmpty() || raw == "none") return base

        val code = raw
            .replace("_", "")
            .replace(" ", "")
            .let {
                if (it.length >= 2 && (it[0] == '$' || it[0] == '&')) {
                    it.substring(1)
                } else {
                    null
                }
            }
            ?: return base

        if (code.length != 1) return base

        val formatting = ChatFormatting.getByCode(code[0]) ?: return base

        if (!formatting.isColor) return base

        return base.withStyle(formatting)
    }
}