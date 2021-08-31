package fr.uracraft.uramod.Items;

import fr.uracraft.uramod.common.UraMod;
import net.minecraft.item.Item;

public class GenericBoxKey extends Item {

    public GenericBoxKey(String type) {
        this.setUnlocalizedName(type + "_box_key");
        this.setTextureName(UraMod.MODID + ":" + type + "_box_key");
        this.setCreativeTab(UraMod.uramodcreativetab);
    }
}
