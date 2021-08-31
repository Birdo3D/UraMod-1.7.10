package fr.uracraft.uramod.Items;

import fr.uracraft.uramod.common.UraMod;
import net.minecraft.item.Item;

public class UraParticle extends Item {

    public UraParticle() {
        this.setUnlocalizedName("ura_particle");
        this.setTextureName(UraMod.MODID + ":ura_particle");
        this.setCreativeTab(UraMod.uramodcreativetab);
    }
}
