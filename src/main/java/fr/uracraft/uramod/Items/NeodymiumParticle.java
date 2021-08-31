package fr.uracraft.uramod.Items;

import fr.uracraft.uramod.common.UraMod;
import net.minecraft.item.Item;

public class NeodymiumParticle extends Item {

    public NeodymiumParticle() {
        this.setUnlocalizedName("neodymium_particle");
        this.setTextureName(UraMod.MODID + ":neodymium_particle");
        this.setCreativeTab(UraMod.uramodcreativetab);
    }
}
