package fr.uracraft.uramod.Items;

import fr.uracraft.uramod.common.UraMod;
import net.minecraft.item.Item;

public class SilverIngot extends Item {

    public SilverIngot() {
        this.setUnlocalizedName("silver_ingot");
        this.setTextureName(UraMod.MODID + ":silver_ingot");
        this.setCreativeTab(UraMod.uramodcreativetab);
    }
}
