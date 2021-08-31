package fr.uracraft.uramod.Items.FurnaceUpgrades;

import fr.uracraft.uramod.common.UraMod;
import net.minecraft.item.Item;

public class UraFurnaceUpgrade extends Item {

    public UraFurnaceUpgrade() {
        this.setUnlocalizedName("ura_furnace_upgrade");
        this.setTextureName(UraMod.MODID + ":ura_furnace_upgrade");
        this.setCreativeTab(UraMod.uramodcreativetab);
    }
}
