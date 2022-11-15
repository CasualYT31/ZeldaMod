package net.mcreator.zeldamod.procedures;

import net.minecraft.world.entity.Entity;
import net.minecraft.commands.CommandSourceStack;

import net.mcreator.zeldamod.network.ZeldaModModVariables;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.arguments.DoubleArgumentType;

public class RupeeCountSetProcedure {
	public static void execute(CommandContext<CommandSourceStack> arguments, Entity entity) {
		if (entity == null)
			return;
		{
			double _setval = Math.floor(DoubleArgumentType.getDouble(arguments, "rupees"));
			entity.getCapability(ZeldaModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.rupee_count = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		RupeeCountDisplayProcedure.execute(entity);
	}
}
