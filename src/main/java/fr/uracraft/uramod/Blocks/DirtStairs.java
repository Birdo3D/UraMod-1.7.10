package fr.uracraft.uramod.Blocks;

import fr.uracraft.uramod.common.UraMod;
import net.minecraft.block.BlockStairs;
import net.minecraft.init.Blocks;

public class DirtStairs extends BlockStairs {

    public DirtStairs() {
        super(Blocks.dirt, 0);
        this.setLightOpacity((int) 1.0F);
        this.setHardness(0.5F);
        this.setResistance(0.5F);
        this.setHarvestLevel("shovel", 0);
        this.useNeighborBrightness = true;
        this.setStepSound(soundTypeGrass);
        this.setBlockName("dirt_stairs");
        this.setCreativeTab(UraMod.uramodcreativetab);
    }
}
