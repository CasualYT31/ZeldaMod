
package net.mcreator.zeldamod.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;

public class ZeldaIconItem extends Item {
	public ZeldaIconItem() {
		super(new Item.Properties().tab(null).stacksTo(1).rarity(Rarity.COMMON));
	}
}
