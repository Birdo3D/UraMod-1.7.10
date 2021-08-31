package fr.uracraft.uramod.Items;

import fr.uracraft.uramod.common.UraMod;
import net.minecraft.item.Item;

public class RandomOreItem extends Item {

    public RandomOreItem() {
        this.setUnlocalizedName("random_ore_item");
        this.setTextureName(UraMod.MODID + ":random_ore_item");
        this.setCreativeTab(UraMod.uramodcreativetab);
        this.setMaxStackSize(1);
    }
}
