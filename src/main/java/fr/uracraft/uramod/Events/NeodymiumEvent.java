package fr.uracraft.uramod.Events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.uracraft.uramod.Utils.Random;
import fr.uracraft.uramod.common.UraBlocks;
import fr.uracraft.uramod.common.UraItems;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;

public class NeodymiumEvent {

    @SubscribeEvent
    public void onHarvestDrops(BlockEvent.HarvestDropsEvent e) {
        if (e.block != null) {

            if (e.block == UraBlocks.neodymium_ore) {
                e.drops.clear();
                e.drops.add(new ItemStack(UraItems.neodymium, Random.Random(1) + 1));
            }
        }
    }
}
