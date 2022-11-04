package net.mcreator.zeldamod.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.core.BlockPos;

import net.mcreator.zeldamod.network.ZeldaModModVariables;
import net.mcreator.zeldamod.init.ZeldaModModItems;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class RupeeRightClickActionsProcedure {
	@SubscribeEvent
	public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
		if (event.getHand() != event.getPlayer().getUsedItemHand())
			return;
		execute(event, event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), event.getPlayer());
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double newRupeeCount = 0;
		double rupeeDifference = 0;
		if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == ZeldaModModItems.RUPEE_WALLET_UPGRADE
				.get()) {
			if (entity instanceof LivingEntity _entity)
				_entity.swing(InteractionHand.MAIN_HAND, true);
			if ((entity.getCapability(ZeldaModModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new ZeldaModModVariables.PlayerVariables())).rupee_limit < 999) {
				{
					double _setval = 999;
					entity.getCapability(ZeldaModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.rupee_limit = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				if (entity instanceof Player _player && !_player.level.isClientSide())
					_player.displayClientMessage(new TextComponent("Upgraded your wallet to level 2!"), (true));
				if (!(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false)) {
					if (entity instanceof LivingEntity _entity) {
						ItemStack _setstack = new ItemStack(Blocks.AIR);
						_setstack.setCount(1);
						_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
						if (_entity instanceof Player _player)
							_player.getInventory().setChanged();
					}
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, new BlockPos(x, y, z),
								ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zelda_mod:get_wallet_upgrade")), SoundSource.NEUTRAL, 1,
								1);
					} else {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zelda_mod:get_wallet_upgrade")),
								SoundSource.NEUTRAL, 1, 1, false);
					}
				}
			} else if ((entity.getCapability(ZeldaModModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new ZeldaModModVariables.PlayerVariables())).rupee_limit < 9999) {
				{
					double _setval = 9999;
					entity.getCapability(ZeldaModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.rupee_limit = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				if (entity instanceof Player _player && !_player.level.isClientSide())
					_player.displayClientMessage(new TextComponent("Upgraded your wallet to level 3!"), (true));
				if (!(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false)) {
					if (entity instanceof LivingEntity _entity) {
						ItemStack _setstack = new ItemStack(Blocks.AIR);
						_setstack.setCount(1);
						_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
						if (_entity instanceof Player _player)
							_player.getInventory().setChanged();
					}
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, new BlockPos(x, y, z),
								ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zelda_mod:get_wallet_upgrade")), SoundSource.NEUTRAL, 1,
								1);
					} else {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zelda_mod:get_wallet_upgrade")),
								SoundSource.NEUTRAL, 1, 1, false);
					}
				}
			} else {
				if (entity instanceof Player _player && !_player.level.isClientSide())
					_player.displayClientMessage(new TextComponent("Your wallet is already at the highest level!"), (true));
			}
		} else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == ZeldaModModItems.RUPEE_WALLET
				.get()) {
			if (entity instanceof LivingEntity _entity)
				_entity.swing(InteractionHand.MAIN_HAND, true);
			newRupeeCount = (entity.getCapability(ZeldaModModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new ZeldaModModVariables.PlayerVariables())).rupee_count
					+ (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getOrCreateTag().getDouble("rupeeCount");
			if (newRupeeCount > (entity.getCapability(ZeldaModModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new ZeldaModModVariables.PlayerVariables())).rupee_limit) {
				rupeeDifference = newRupeeCount - (entity.getCapability(ZeldaModModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new ZeldaModModVariables.PlayerVariables())).rupee_limit;
				(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getOrCreateTag().putDouble("rupeeCount",
						rupeeDifference);
				{
					double _setval = (entity.getCapability(ZeldaModModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new ZeldaModModVariables.PlayerVariables())).rupee_limit;
					entity.getCapability(ZeldaModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.rupee_count = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				if (rupeeDifference == 1) {
					if (entity instanceof Player _player && !_player.level.isClientSide())
						_player.displayClientMessage(
								new TextComponent("Couldn't fit all of the rupees into your wallet. There is 1 rupee remaining!"), (true));
				} else {
					if (entity instanceof Player _player && !_player.level.isClientSide())
						_player.displayClientMessage(new TextComponent(("Couldn't fit all of the rupees into your wallet. There are "
								+ new java.text.DecimalFormat("#").format(rupeeDifference) + " rupees remaining!")), (true));
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, new BlockPos(x, y, z),
								ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zelda_mod:get_green_rupee")), SoundSource.NEUTRAL, 1, 1);
					} else {
						_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("zelda_mod:get_green_rupee")),
								SoundSource.NEUTRAL, 1, 1, false);
					}
				}
			} else {
				{
					double _setval = newRupeeCount;
					entity.getCapability(ZeldaModModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.rupee_count = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getOrCreateTag()
						.getDouble("rupeeCount") == 1) {
					if (entity instanceof Player _player && !_player.level.isClientSide())
						_player.displayClientMessage(new TextComponent("Added 1 rupee to your wallet!"), (true));
				} else {
					if (entity instanceof Player _player && !_player.level.isClientSide())
						_player.displayClientMessage(new TextComponent(("Added " + (new java.text.DecimalFormat("#")
								.format((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getOrCreateTag()
										.getDouble("rupeeCount")))
								+ " rupees to your wallet!")), (true));
				}
				if (!(entity instanceof Player _plr ? _plr.getAbilities().instabuild : false)) {
					if (entity instanceof LivingEntity _entity) {
						ItemStack _setstack = new ItemStack(Blocks.AIR);
						_setstack.setCount(1);
						_entity.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
						if (_entity instanceof Player _player)
							_player.getInventory().setChanged();
					}
				}
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
		}
	}
}
