package net.mcreator.zeldamod.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.world.BlockEvent;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.util.Mth;
import net.minecraft.core.BlockPos;

import net.mcreator.zeldamod.init.ZeldaModModItems;

import javax.annotation.Nullable;

import java.util.Random;

@Mod.EventBusSubscriber
public class RupeeBlockDropsProcedure {
	@SubscribeEvent
	public static void onBlockBreak(BlockEvent.BreakEvent event) {
		execute(event, event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ());
	}

	public static void execute(LevelAccessor world, double x, double y, double z) {
		execute(null, world, x, y, z);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z) {
		if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == Blocks.GRASS
				|| (world.getBlockState(new BlockPos(x, y, z))).getBlock() == Blocks.SEAGRASS
				|| (world.getBlockState(new BlockPos(x, y, z))).getBlock() == Blocks.TALL_SEAGRASS
				|| (world.getBlockState(new BlockPos(x, y, z))).getBlock() == Blocks.DEAD_BUSH
				|| (world.getBlockState(new BlockPos(x, y, z))).getBlock() == Blocks.TALL_GRASS) {
			if (Mth.nextInt(new Random(), 1, 75) == 1) {
				if (world instanceof Level _level && !_level.isClientSide()) {
					ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack(ZeldaModModItems.RUPEE_GREEN.get()));
					entityToSpawn.setPickUpDelay(10);
					_level.addFreshEntity(entityToSpawn);
				}
			}
		}
	}
}
