package net.mcreator.zeldamod.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.ElderGuardian;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;

import net.mcreator.zeldamod.network.ZeldaModModVariables;
import net.mcreator.zeldamod.init.ZeldaModModItems;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class RupeeEntityDropsProcedure {
	@SubscribeEvent
	public static void onEntityDeath(LivingDeathEvent event) {
		if (event != null && event.getEntity() != null) {
			execute(event, event.getEntity().level, event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		ItemStack rupeeWallet = ItemStack.EMPTY;
		if (entity instanceof Player) {
			rupeeWallet = new ItemStack(ZeldaModModItems.RUPEE_WALLET.get());
			rupeeWallet.getOrCreateTag().putDouble("rupeeCount", ((entity.getCapability(ZeldaModModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new ZeldaModModVariables.PlayerVariables())).rupee_count));
			if (world instanceof Level _level && !_level.isClientSide()) {
				ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, rupeeWallet);
				entityToSpawn.setPickUpDelay(10);
				_level.addFreshEntity(entityToSpawn);
			}
		} else if (entity instanceof Animal || entity instanceof Villager) {
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands()
						.performCommand(
								new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", new TextComponent(""),
										_level.getServer(), null).withSuppressedOutput(),
								"loot spawn ~ ~ ~ loot zelda_mod:rupee_loot_table_friendly");
		} else if (entity instanceof EnderMan || entity instanceof AbstractGolem || entity instanceof Blaze || entity instanceof Ghast
				|| entity instanceof Piglin || entity instanceof WitherSkeleton || entity instanceof Witch || entity instanceof Guardian
				|| entity instanceof ElderGuardian || entity instanceof Hoglin) {
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4,
						"", new TextComponent(""), _level.getServer(), null).withSuppressedOutput(),
						"loot spawn ~ ~ ~ loot zelda_mod:rupee_loot_table_rare");
		} else {
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performCommand(new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4,
						"", new TextComponent(""), _level.getServer(), null).withSuppressedOutput(),
						"loot spawn ~ ~ ~ loot zelda_mod:entities/rupee_drops");
		}
	}
}
