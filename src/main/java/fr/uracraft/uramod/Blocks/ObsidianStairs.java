package fr.uracraft.uramod.Blocks;

import fr.uracraft.uramod.common.UraMod;
import net.minecraft.block.BlockStairs;
import net.minecraft.init.Blocks;

public class ObsidianStairs extends BlockStairs {

    public ObsidianStairs() {
        super(Blocks.obsidian, 0);
        this.setLightOpacity((int) 1.0F);
        this.setHardness(50F);
        this.setResistance(1200F);
        this.setHarvestLevel("pickaxe", 3);
        this.useNeighborBrightness = true;
        this.setStepSound(soundTypeStone);
        this.setBlockName("obsidian_stairs");
        this.setCreativeTab(UraMod.uramodcreativetab);
    }
}
