package fr.uracraft.uramod.Events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.uracraft.uramod.common.UraBlocks;
import fr.uracraft.uramod.common.UraItems;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.FillBucketEvent;

public class FluidsEvent {

    @SubscribeEvent
    public void onBucketFill(FillBucketEvent event) {

        Block id = event.world.getBlock(event.target.blockX, event.target.blockY, event.target.blockZ);
        int metadata = event.world.getBlockMetadata(event.target.blockX, event.target.blockY, event.target.blockZ);

        if (id == UraBlocks.fake_water_block && metadata == 0) {
            event.world.setBlockToAir(event.target.blockX, event.target.blockY, event.target.blockZ);
            if (!event.world.isRemote) {
                event.entityPlayer.setCurrentItemOrArmor(0, new ItemStack(UraItems.fake_water_bucket));
            }
        }
        if (id == UraBlocks.fake_lava_block && metadata == 0) {
            event.world.setBlockToAir(event.target.blockX, event.target.blockY, event.target.blockZ);
            if (!event.world.isRemote) {
                event.entityPlayer.setCurrentItemOrArmor(0, new ItemStack(UraItems.fake_lava_bucket));
            }
        }
        if (id == UraBlocks.glowstone_liquide_block && metadata == 0) {
            event.world.setBlockToAir(event.target.blockX, event.target.blockY, event.target.blockZ);
            if (!event.world.isRemote) {
                event.entityPlayer.setCurrentItemOrArmor(0, new ItemStack(UraItems.glowstone_liquide_bucket));
            }
        }
    }
}
