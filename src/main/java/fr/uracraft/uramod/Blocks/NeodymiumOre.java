package fr.uracraft.uramod.Blocks;

import fr.uracraft.uramod.common.UraMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class NeodymiumOre extends Block {

    public NeodymiumOre() {
        super(Material.rock);
        this.setHardness(3F);
        this.setResistance(3F);
        this.setLightOpacity(255);
        this.setHarvestLevel("pickaxe", 3);
        this.useNeighborBrightness = true;
        this.setStepSound(soundTypeStone);
        this.setBlockTextureName(UraMod.MODID + ":neodymium_ore");
        this.setBlockName("neodymium_ore");
        this.setCreativeTab(UraMod.uramodcreativetab);
    }
}
