package fr.uracraft.uramod.Items;

import fr.uracraft.uramod.common.UraMod;
import net.minecraft.item.Item;

public class UraNugget extends Item {

    public UraNugget() {
        this.setUnlocalizedName("ura_nugget");
        this.setTextureName(UraMod.MODID + ":ura_nugget");
        this.setCreativeTab(UraMod.uramodcreativetab);
    }
}
