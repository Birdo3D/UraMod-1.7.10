package fr.uracraft.uramod.Blocks;

import fr.uracraft.uramod.common.UraMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class CaveBlock extends Block {

    public CaveBlock() {
        super(Material.glass);
        this.setHardness(0.3F);
        this.setResistance(1.5F);
        this.setLightOpacity(0);
        this.setHarvestLevel("pickaxe", 2);
        this.useNeighborBrightness = true;
        this.setStepSound(soundTypeGlass);
        this.setBlockTextureName(UraMod.MODID + ":cave_block");
        this.setBlockName("cave_block");
        this.setCreativeTab(UraMod.uramodcreativetab);
    }
}
