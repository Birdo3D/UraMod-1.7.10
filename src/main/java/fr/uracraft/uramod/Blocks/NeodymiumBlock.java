package fr.uracraft.uramod.Blocks;

import fr.uracraft.uramod.common.UraMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class NeodymiumBlock extends Block{

    public NeodymiumBlock() {
        super(Material.rock);
        this.setHardness(3F);
        this.setResistance(3F);
        this.setLightOpacity(255);
        this.setHarvestLevel("pickaxe", 2);
        this.useNeighborBrightness = true;
        this.setStepSound(soundTypeStone);
        this.setBlockTextureName(UraMod.MODID + ":neodymium_block");
        this.setBlockName("neodymium_block");
        this.setCreativeTab(UraMod.uramodcreativetab);
    }
}
