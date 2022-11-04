
package net.mcreator.zeldamod.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.Component;

import net.mcreator.zeldamod.init.ZeldaModModTabs;

import java.util.List;

public class RupeeWalletUpgradeItem extends Item {
	public RupeeWalletUpgradeItem() {
		super(new Item.Properties().tab(ZeldaModModTabs.TAB_ZELDA_TAB).stacksTo(16).rarity(Rarity.UNCOMMON));
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
		list.add(new TextComponent("Upgrade your wallet by one level!"));
		list.add(new TextComponent("Use right-click to activate!"));
	}
}
