package net.kss557.dimensiontint.mixin;

import net.kss557.dimensiontint.tab.ColorTab;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayer.class)
public class ServerPlayerTabMixin {
	@Inject(at = @At("HEAD"), method = "getTabListDisplayName", cancellable = true)
	private void dimensiontint$tabListDisplayName(CallbackInfoReturnable<Component> info) {
		info.setReturnValue(ColorTab.tabDisplayName((ServerPlayer) (Object) this));
	}
}