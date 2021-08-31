package fr.uracraft.uramod.Blocks;

import fr.uracraft.uramod.common.UraMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class SilverOre extends Block {

    public SilverOre() {
        super(Material.rock);
        this.setHardness(3F);
        this.setResistance(3F);
        this.setLightOpacity(255);
        this.setHarvestLevel("pickaxe", 2);
        this.useNeighborBrightness = true;
        this.setStepSound(soundTypeStone);
        this.setBlockTextureName(UraMod.MODID + ":silver_ore");
        this.setBlockName("silver_ore");
        this.setCreativeTab(UraMod.uramodcreativetab);
    }
}
