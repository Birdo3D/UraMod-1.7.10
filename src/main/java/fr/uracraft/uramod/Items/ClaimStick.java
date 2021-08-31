package fr.uracraft.uramod.Items;

import fr.uracraft.uramod.common.UraMod;
import net.minecraft.item.Item;

public class ClaimStick extends Item {

    public ClaimStick() {
        this.setUnlocalizedName("claim_stick");
        this.setTextureName(UraMod.MODID + ":claim_stick");
        this.setCreativeTab(UraMod.uramodcreativetab);
    }
}
