package fr.uracraft.uramod.Items;

import fr.uracraft.uramod.common.UraMod;
import net.minecraft.item.Item;

public class SilverParticle extends Item {

    public SilverParticle() {
        this.setUnlocalizedName("silver_particle");
        this.setTextureName(UraMod.MODID + ":silver_particle");
        this.setCreativeTab(UraMod.uramodcreativetab);
    }
}
