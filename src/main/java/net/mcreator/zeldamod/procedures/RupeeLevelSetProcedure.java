package net.mcreator.zeldamod.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.commands.CommandSourceStack;

import net.mcreator.zeldamod.network.ZeldaModModVariables;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.DoubleArgumentType;

public class RupeeLevelSetProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		if (Math.floor(DoubleArgumentType.getDouble(arguments, "level")) == 1) {
			{
				double _setval = 99;
				entity.getCapability(ZeldaModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.rupee_limit = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else if (Math.floor(DoubleArgumentType.getDouble(arguments, "level")) == 2) {
			{
				double _setval = 999;
				entity.getCapability(ZeldaModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.rupee_limit = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		} else if (Math.floor(DoubleArgumentType.getDouble(arguments, "level")) == 3) {
			{
				double _setval = 9999;
				entity.getCapability(ZeldaModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.rupee_limit = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		RupeeCountDisplayProcedure.execute(entity);
	}
}
