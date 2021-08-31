package fr.uracraft.uramod.Items;

import fr.uracraft.uramod.common.UraMod;
import net.minecraft.item.Item;

public class MagnifyingGlass extends Item {

    public MagnifyingGlass() {
        this.setUnlocalizedName("magnifying_glass");
        this.setTextureName(UraMod.MODID + ":magnifying_glass");
        this.setCreativeTab(UraMod.uramodcreativetab);
    }
}
