package fr.uracraft.uramod.Items.FurnaceUpgrades;

import fr.uracraft.uramod.common.UraMod;
import net.minecraft.item.Item;

public class DiamondFurnaceUpgrade extends Item {

    public DiamondFurnaceUpgrade() {
        this.setUnlocalizedName("diamond_furnace_upgrade");
        this.setTextureName(UraMod.MODID + ":diamond_furnace_upgrade");
        this.setCreativeTab(UraMod.uramodcreativetab);
    }
}
