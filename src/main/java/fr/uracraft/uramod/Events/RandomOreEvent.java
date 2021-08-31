package fr.uracraft.uramod.Events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.uracraft.uramod.Utils.Random;
import fr.uracraft.uramod.common.UraBlocks;
import fr.uracraft.uramod.common.UraItems;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;

public class RandomOreEvent {

    @SubscribeEvent
    public void onPlayerInteractRandomOreItem(PlayerInteractEvent event) {
        if (event.entityPlayer != null) {
            if (event.action == PlayerInteractEvent.Action.RIGHT_CLICK_AIR || event.action == PlayerInteractEvent.Action.RIGHT_CLICK_BLOCK) {
                ItemStack stack = event.entityPlayer.getCurrentEquippedItem();
                if (stack != null && (stack.getItem() == UraItems.random_ore_item)) {

                    int v = Random.Random(4);
                    int v1 = Random.Random(2);

                    if (v == 0) {
                        if (v1 == 0) {
                            event.entityPlayer.setCurrentItemOrArmor(0, new ItemStack(Blocks.diamond_ore, 1));
                        } else if (v1 == 1) {
                            event.entityPlayer.setCurrentItemOrArmor(0, new ItemStack(Blocks.diamond_ore, 2));
                        } else if (v1 == 2) {
                            event.entityPlayer.setCurrentItemOrArmor(0, new ItemStack(Blocks.diamond_ore, 3));
                        }
                    } else if (v == 1) {
                        if (v1 == 0) {
                            event.entityPlayer.setCurrentItemOrArmor(0, new ItemStack(UraBlocks.silver_ore, 1));
                        } else if (v1 == 1) {
                            event.entityPlayer.setCurrentItemOrArmor(0, new ItemStack(UraBlocks.silver_ore, 2));
                        } else if (v1 == 2) {
                            event.entityPlayer.setCurrentItemOrArmor(0, new ItemStack(UraBlocks.silver_ore, 3));
                        }
                    } else if (v == 2) {
                        if (v1 == 0) {
                            event.entityPlayer.setCurrentItemOrArmor(0, new ItemStack(UraBlocks.ura_ore, 1));
                        } else if (v1 == 1) {
                            event.entityPlayer.setCurrentItemOrArmor(0, new ItemStack(UraBlocks.ura_ore, 2));
                        } else if (v1 == 2) {
                            event.entityPlayer.setCurrentItemOrArmor(0, new ItemStack(UraBlocks.ura_ore, 3));
                        }
                    } else if (v == 3) {
                        if (v1 == 0) {
                            event.entityPlayer.setCurrentItemOrArmor(0, new ItemStack(UraBlocks.neodymium_ore, 1));
                        } else if (v1 == 1) {
                            event.entityPlayer.setCurrentItemOrArmor(0, new ItemStack(UraBlocks.neodymium_ore, 2));
                        } else if (v1 == 2) {
                            event.entityPlayer.setCurrentItemOrArmor(0, new ItemStack(UraBlocks.neodymium_ore, 3));
                        }
                    } else if (v == 4) {
                        event.entityPlayer.setCurrentItemOrArmor(0, new ItemStack(UraBlocks.random_ore, 1));
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onHarvestDrops(BlockEvent.HarvestDropsEvent e) {
        if (e.block != null) {

            if (e.block == UraBlocks.random_ore) {
                e.drops.clear();
                e.drops.add(new ItemStack(UraItems.random_ore_item, 1));
            }
        }
    }
}
