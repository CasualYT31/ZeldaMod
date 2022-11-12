
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.zeldamod.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

import net.mcreator.zeldamod.ZeldaModMod;

public class ZeldaModModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, ZeldaModMod.MODID);
	public static final RegistryObject<SoundEvent> GET_BLUE_RUPEE = REGISTRY.register("get_blue_rupee",
			() -> new SoundEvent(new ResourceLocation("zelda_mod", "get_blue_rupee")));
	public static final RegistryObject<SoundEvent> GET_RED_RUPEE = REGISTRY.register("get_red_rupee",
			() -> new SoundEvent(new ResourceLocation("zelda_mod", "get_red_rupee")));
	public static final RegistryObject<SoundEvent> GET_GREEN_RUPEE = REGISTRY.register("get_green_rupee",
			() -> new SoundEvent(new ResourceLocation("zelda_mod", "get_green_rupee")));
	public static final RegistryObject<SoundEvent> GET_WALLET_UPGRADE = REGISTRY.register("get_wallet_upgrade",
			() -> new SoundEvent(new ResourceLocation("zelda_mod", "get_wallet_upgrade")));
}
