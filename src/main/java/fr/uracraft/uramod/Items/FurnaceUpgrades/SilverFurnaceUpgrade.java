package fr.uracraft.uramod.Items.FurnaceUpgrades;

import fr.uracraft.uramod.common.UraMod;
import net.minecraft.item.Item;

public class SilverFurnaceUpgrade extends Item {

    public SilverFurnaceUpgrade() {
        this.setUnlocalizedName("silver_furnace_upgrade");
        this.setTextureName(UraMod.MODID + ":silver_furnace_upgrade");
        this.setCreativeTab(UraMod.uramodcreativetab);
    }
}
