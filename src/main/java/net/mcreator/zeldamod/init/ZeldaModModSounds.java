
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.zeldamod.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;
import java.util.HashMap;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ZeldaModModSounds {
	public static Map<ResourceLocation, SoundEvent> REGISTRY = new HashMap<>();
	static {
		REGISTRY.put(new ResourceLocation("zelda_mod", "get_blue_rupee"), new SoundEvent(new ResourceLocation("zelda_mod", "get_blue_rupee")));
		REGISTRY.put(new ResourceLocation("zelda_mod", "get_red_rupee"), new SoundEvent(new ResourceLocation("zelda_mod", "get_red_rupee")));
		REGISTRY.put(new ResourceLocation("zelda_mod", "get_green_rupee"), new SoundEvent(new ResourceLocation("zelda_mod", "get_green_rupee")));
		REGISTRY.put(new ResourceLocation("zelda_mod", "get_wallet_upgrade"),
				new SoundEvent(new ResourceLocation("zelda_mod", "get_wallet_upgrade")));
	}

	@SubscribeEvent
	public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
		for (Map.Entry<ResourceLocation, SoundEvent> sound : REGISTRY.entrySet())
			event.getRegistry().register(sound.getValue().setRegistryName(sound.getKey()));
	}
}
