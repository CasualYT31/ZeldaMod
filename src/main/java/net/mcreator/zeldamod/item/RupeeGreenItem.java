
package net.mcreator.zeldamod.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.Entity;

import net.mcreator.zeldamod.procedures.RupeeAddGreenRupeeProcedure;
import net.mcreator.zeldamod.init.ZeldaModModTabs;

public class RupeeGreenItem extends Item {
	public RupeeGreenItem() {
		super(new Item.Properties().tab(ZeldaModModTabs.TAB_ZELDA_TAB).stacksTo(64).rarity(Rarity.COMMON));
	}

	@Override
	public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
		super.inventoryTick(itemstack, world, entity, slot, selected);
		RupeeAddGreenRupeeProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity, itemstack);
	}
}
