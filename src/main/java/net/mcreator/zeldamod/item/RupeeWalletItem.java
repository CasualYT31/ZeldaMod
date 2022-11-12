
package net.mcreator.zeldamod.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.Component;

import net.mcreator.zeldamod.procedures.RupeeAddRupeeStackProcedure;
import net.mcreator.zeldamod.init.ZeldaModModTabs;

import java.util.List;

public class RupeeWalletItem extends Item {
	public RupeeWalletItem() {
		super(new Item.Properties().tab(ZeldaModModTabs.TAB_ZELDA_TAB).stacksTo(1).rarity(Rarity.COMMON));
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
		double rupeeCount = 0;
		if (itemstack.getOrCreateTag().contains("rupeeCount")) {
			rupeeCount = itemstack.getTag().getDouble("rupeeCount");
		}
		String rupeesLabel = " rupee";
		if (rupeeCount != 1) {
			rupeesLabel = rupeesLabel + "s";
		}
		list.add(Component.literal("Holds a player's rupees when they die."));
		list.add(Component.literal(String.format("%.0f", rupeeCount) + rupeesLabel + "."));
		list.add(Component.literal("Use right-click to add the rupees in this wallet to your own."));
	}

}
