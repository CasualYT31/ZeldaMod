package net.mcreator.zeldamod.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.TextComponent;

import net.mcreator.zeldamod.network.ZeldaModModVariables;

public class RupeeCountDisplayProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player && !_player.level.isClientSide())
			_player.displayClientMessage(new TextComponent(("Your rupee count is "
					+ new java.text.DecimalFormat("#").format((entity.getCapability(ZeldaModModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new ZeldaModModVariables.PlayerVariables())).rupee_count))),
					(false));
		if (entity instanceof Player _player && !_player.level.isClientSide())
			_player.displayClientMessage(new TextComponent(("Your rupee limit is "
					+ new java.text.DecimalFormat("#").format((entity.getCapability(ZeldaModModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new ZeldaModModVariables.PlayerVariables())).rupee_limit))),
					(false));
	}
}
