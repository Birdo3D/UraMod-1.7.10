package fr.uracraft.uramod.Items;

import fr.uracraft.uramod.common.UraMod;
import net.minecraft.item.Item;

public class IridiumIngot extends Item {

    public IridiumIngot() {
        this.setUnlocalizedName("iridium_ingot");
        this.setTextureName(UraMod.MODID + ":iridium_ingot");
        this.setCreativeTab(UraMod.uramodcreativetab);
    }
}
