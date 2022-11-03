
package net.mcreator.zeldamod.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.Entity;

import net.mcreator.zeldamod.procedures.RupeeAddRupeeStackProcedure;
import net.mcreator.zeldamod.init.ZeldaModModTabs;

public class RupeeRedItem extends Item {
	public RupeeRedItem() {
		super(new Item.Properties().tab(ZeldaModModTabs.TAB_ZELDA_TAB).stacksTo(64).rarity(Rarity.UNCOMMON));
	}

	@Override
	public void inventoryTick(ItemStack itemstack, Level world, Entity entity, int slot, boolean selected) {
		super.inventoryTick(itemstack, world, entity, slot, selected);
		RupeeAddRupeeStackProcedure.execute(world, entity.getX(), entity.getY(), entity.getZ(), entity, itemstack);
	}
}
