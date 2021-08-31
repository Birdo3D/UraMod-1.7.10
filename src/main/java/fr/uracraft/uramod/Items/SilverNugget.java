package fr.uracraft.uramod.Items;

import fr.uracraft.uramod.common.UraMod;
import net.minecraft.item.Item;

public class SilverNugget extends Item {

    public SilverNugget() {
        this.setUnlocalizedName("silver_nugget");
        this.setTextureName(UraMod.MODID + ":silver_nugget");
        this.setCreativeTab(UraMod.uramodcreativetab);
    }
}
