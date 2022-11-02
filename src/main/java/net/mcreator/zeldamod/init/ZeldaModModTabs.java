
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.zeldamod.init;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;

public class ZeldaModModTabs {
	public static CreativeModeTab TAB_ZELDA_TAB;

	public static void load() {
		TAB_ZELDA_TAB = new CreativeModeTab("tabzelda_tab") {
			@Override
			public ItemStack makeIcon() {
				return new ItemStack(ZeldaModModItems.RUPEE_GREEN.get());
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return true;
			}
		}.setBackgroundSuffix("item_search.png");
	}
}
