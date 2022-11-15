
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.zeldamod.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.Item;

import net.mcreator.zeldamod.item.ZeldaIconItem;
import net.mcreator.zeldamod.item.RupeeWalletUpgradeItem;
import net.mcreator.zeldamod.item.RupeeWalletItem;
import net.mcreator.zeldamod.item.RupeeRedItem;
import net.mcreator.zeldamod.item.RupeeGreenItem;
import net.mcreator.zeldamod.item.RupeeBlueItem;
import net.mcreator.zeldamod.ZeldaModMod;

public class ZeldaModModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, ZeldaModMod.MODID);
	public static final RegistryObject<Item> RUPEE_GREEN = REGISTRY.register("rupee_green", () -> new RupeeGreenItem());
	public static final RegistryObject<Item> RUPEE_BLUE = REGISTRY.register("rupee_blue", () -> new RupeeBlueItem());
	public static final RegistryObject<Item> RUPEE_RED = REGISTRY.register("rupee_red", () -> new RupeeRedItem());
	public static final RegistryObject<Item> RUPEE_WALLET = REGISTRY.register("rupee_wallet", () -> new RupeeWalletItem());
	public static final RegistryObject<Item> RUPEE_WALLET_UPGRADE = REGISTRY.register("rupee_wallet_upgrade", () -> new RupeeWalletUpgradeItem());
	public static final RegistryObject<Item> ZELDA_ICON = REGISTRY.register("zelda_icon", () -> new ZeldaIconItem());
}
