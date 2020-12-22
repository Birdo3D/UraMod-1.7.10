package fr.mod.Ura_Mod.Ura_ModCommon;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class BucketEvent
{
    @SubscribeEvent
    public void onBucketFill(FillBucketEvent event)
    {
        Block block = event.world.getBlock(event.target.blockX, event.target.blockY, event.target.blockZ);
        int metadata = event.world.getBlockMetadata(event.target.blockX, event.target.blockY, event.target.blockZ);
        if(block == Ura_ModMain.fakewaterblock && metadata == 0)
        {
            event.world.setBlockToAir(event.target.blockX, event.target.blockY, event.target.blockZ);
            event.result = new ItemStack(Ura_ModMain.fakewaterbucket);
            event.setResult(Result.ALLOW);
        }
        if(block == Ura_ModMain.fakelavablock && metadata == 0)
        {
            event.world.setBlockToAir(event.target.blockX, event.target.blockY, event.target.blockZ);
            event.result = new ItemStack(Ura_ModMain.fakelavabucket);
            event.setResult(Result.ALLOW);
        }
        if(block == Ura_ModMain.glowstoneliquideblock && metadata == 0)
        {
            event.world.setBlockToAir(event.target.blockX, event.target.blockY, event.target.blockZ);
            event.result = new ItemStack(Ura_ModMain.glowstoneliquidebucket);
            event.setResult(Result.ALLOW);
        }
    }
}