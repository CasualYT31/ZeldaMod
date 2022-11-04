package net.mcreator.zeldamod.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import net.mcreator.zeldamod.network.ZeldaModModVariables;
import net.mcreator.zeldamod.init.ZeldaModModItems;

public class RupeeAddRupeeStackProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		double rupeeValue = 0;
		if (itemstack.getItem() == ZeldaModModItems.RUPEE_GREEN.get()) {
			rupeeValue = 1;
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, new BlockPos(x, y, z),
							ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zelda_mod:get_green_rupee")), SoundSource.NEUTRAL, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zelda_mod:get_green_rupee")),
							SoundSource.NEUTRAL, 1, 1, false);
				}
			}
		} else if (itemstack.getItem() == ZeldaModModItems.RUPEE_BLUE.get()) {
			rupeeValue = 5;
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, new BlockPos(x, y, z),
							ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zelda_mod:get_blue_rupee")), SoundSource.NEUTRAL, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zelda_mod:get_blue_rupee")),
							SoundSource.NEUTRAL, 1, 1, false);
				}
			}
		} else if (itemstack.getItem() == ZeldaModModItems.RUPEE_RED.get()) {
			rupeeValue = 20;
			if (world instanceof Level _level) {
				if (!_level.isClientSide()) {
					_level.playSound(null, new BlockPos(x, y, z),
							ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zelda_mod:get_red_rupee")), SoundSource.NEUTRAL, 1, 1);
				} else {
					_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zelda_mod:get_red_rupee")),
							SoundSource.NEUTRAL, 1, 1, false);
				}
			}
		}
		{
			double _setval = (entity.getCapability(ZeldaModModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new ZeldaModModVariables.PlayerVariables())).rupee_count + (itemstack).getCount() * rupeeValue;
			entity.getCapability(ZeldaModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.rupee_count = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		if ((entity.getCapability(ZeldaModModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new ZeldaModModVariables.PlayerVariables())).rupee_count > (entity
						.getCapability(ZeldaModModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new ZeldaModModVariables.PlayerVariables())).rupee_limit) {
			{
				double _setval = (entity.getCapability(ZeldaModModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new ZeldaModModVariables.PlayerVariables())).rupee_limit;
				entity.getCapability(ZeldaModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.rupee_count = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		(itemstack).setCount(0);
	}
}
