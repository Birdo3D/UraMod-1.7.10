package fr.mod.Ura_Mod.items;

import fr.mod.Ura_Mod.utils.Seed;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFarmland;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SeedPlanter extends Item {
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        int playerX = (int) player.posX;
        int playerY = (int) player.posY;
        int playerZ = (int) player.posZ;

        for (int blockX = -3; blockX <= 3; blockX ++){
            for (int blockZ = -4; blockZ <= 2; blockZ ++){
                if(world.blockExists(playerX+blockX,playerY-1,playerZ+blockZ)){
                    Block blockFarmlands = world.getBlock(playerX+blockX,playerY-1,playerZ+blockZ);
                    if(blockFarmlands instanceof BlockFarmland && world.isAirBlock(playerX+blockX,playerY,playerZ+blockZ)){
                        boolean done = false;
                        for(int i = 0; i < 9 && !done ; i++) {
                            if (player.inventory.getStackInSlot(i) != null) {
                                if (player.inventory.getStackInSlot(i).getItem() instanceof ItemSeeds) {
                                    done = true;
                                    int seeds = player.inventory.getStackInSlot(i).stackSize;
                                    Item item_seed = player.inventory.getStackInSlot(i).getItem();
                                    if (seeds == 1) {
                                        player.inventory.setInventorySlotContents(i, null);
                                        world.setBlock(playerX + blockX, playerY, playerZ + blockZ, Seed.getFarmType(item_seed));
                                    } else {
                                        player.inventory.setInventorySlotContents(i, new ItemStack(item_seed, seeds - 1));
                                        world.setBlock(playerX + blockX, playerY, playerZ + blockZ, Seed.getFarmType(item_seed));
                                    }
                                    player.inventoryContainer.detectAndSendChanges();

                                }
                            }
                        }
                    }
                }
            }
        }
        return itemStack;
    }
    /*
    public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer player, World world, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
    {
        int playerX = (int) player.posX;
        int playerY = (int) player.posY;
        int playerZ = (int) player.posZ;

        for (int blockX = -3; blockX <= 7; blockX ++){
            for (int blockZ = -3; blockZ <= 7; blockZ ++){
                if(world.blockExists(playerX+blockX,playerY-1,playerZ+blockZ)){
                    Block blockFarmlands = world.getBlock(playerX+blockX,playerY-1,playerZ+blockZ);
                    if(blockFarmlands instanceof BlockFarmland && world.blockExists(playerX+blockX,playerY,playerZ+blockZ)){
                        for(int i = 0; i < 8; i++) {
                            if (player.inventory.getStackInSlot(i) != null) {
                                if (player.inventory.getStackInSlot(i).getItem() instanceof ItemSeeds) {
                                    if (player.inventory.getStackInSlot(i).getItem().onItemUse(player.inventory.getStackInSlot(i), player, world, playerX + blockX, playerY, playerZ + blockZ, p_77648_7_, p_77648_8_, p_77648_9_, p_77648_10_)) {
                                    }
                                }
                            }
                        }
                    }
                }
            }
    }
        return true;

}
     */
}

