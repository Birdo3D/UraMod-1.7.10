package fr.uracraft.uramod.Items;

import fr.uracraft.uramod.common.UraMod;
import net.minecraft.item.Item;

public class Dollar extends Item {

    public Dollar(String amount) {
        this.setUnlocalizedName("dollar_" + amount);
        this.setTextureName(UraMod.MODID + ":dollar_" + amount);
        this.setCreativeTab(UraMod.uramodcreativetab);
    }
}
