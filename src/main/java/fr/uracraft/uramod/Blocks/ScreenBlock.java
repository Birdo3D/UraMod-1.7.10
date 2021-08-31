package fr.uracraft.uramod.Blocks;

import fr.uracraft.uramod.common.UraMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ScreenBlock extends Block {

    public ScreenBlock(String color) {
        super(Material.glass);
        this.setHardness(0.3F);
        this.setResistance(1.5F);
        this.setLightLevel(1.0F);
        this.setHarvestLevel("pickaxe", 2);
        this.useNeighborBrightness = true;
        this.setStepSound(soundTypeGlass);
        this.setBlockTextureName(UraMod.MODID + ":" + color + "_screen_block");
        this.setBlockName(color + "_screen_block");
        this.setCreativeTab(UraMod.uramodcreativetab);
    }
}
