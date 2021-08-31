package fr.uracraft.uramod.Blocks;

import fr.uracraft.uramod.common.UraMod;
import net.minecraft.block.BlockStairs;
import net.minecraft.init.Blocks;

public class BedrockStairs extends BlockStairs {

    public BedrockStairs() {
        super(Blocks.bedrock, 0);
        this.setLightOpacity((int) 1.0F);
        this.setHardness(-1F);
        this.setResistance(3600000F);
        this.useNeighborBrightness = true;
        this.setStepSound(soundTypeStone);
        this.setBlockName("bedrock_stairs");
        this.setCreativeTab(UraMod.uramodcreativetab);
    }
}
