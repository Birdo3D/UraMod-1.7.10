package fr.uracraft.uramod.Items;

import fr.uracraft.uramod.common.UraMod;
import net.minecraft.item.Item;

public class UraIngot extends Item {

    public UraIngot() {
        this.setUnlocalizedName("ura_ingot");
        this.setTextureName(UraMod.MODID + ":ura_ingot");
        this.setCreativeTab(UraMod.uramodcreativetab);
    }
}
