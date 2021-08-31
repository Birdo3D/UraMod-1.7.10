package fr.uracraft.uramod.Blocks;

import fr.uracraft.uramod.common.UraMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class GenericBox extends Block {

    public GenericBox(String type) {
        super(Material.rock);
        this.setHardness(-1F);
        this.setResistance(3600000F);
        this.setLightOpacity(255);
        this.setHarvestLevel("pickaxe", 3);
        this.useNeighborBrightness = true;
        this.setStepSound(soundTypeStone);
        this.setBlockTextureName(UraMod.MODID + ":" + type + "_box");
        this.setBlockName(type + "_box");
        this.setCreativeTab(UraMod.uramodcreativetab);
    }
}
