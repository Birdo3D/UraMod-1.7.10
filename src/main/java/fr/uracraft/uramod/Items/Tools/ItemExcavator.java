package fr.uracraft.uramod.Items.Tools;

import fr.uracraft.uramod.common.UraBlocks;
import fr.uracraft.uramod.common.UraMod;
import net.minecraft.block.Block;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

import java.util.List;

public class ItemExcavator extends net.minecraft.item.ItemPickaxe {

    public ItemExcavator(Item.ToolMaterial material) {
        super(material);
        this.setCreativeTab(UraMod.uramodcreativetab);
    }

    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (player.isSneaking()) {
            if (!stack.hasTagCompound()) {
                stack.setTagCompound(new NBTTagCompound());
            }
            byte mode = stack.getTagCompound().getByte("mode");
            mode++;
            if (mode == 3) {
                mode = 0;
            }
            stack.getTagCompound().setByte("mode", mode);
            if (!world.isRemote) {
                player.addChatMessage(new ChatComponentTranslation("excavator.mode.message." + mode));
            }
        } else {
            super.onItemRightClick(stack, world, player);
        }
        return stack;
    }

    public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase living) {
        if (stack.getTagCompound().getByte("mode") == 0) // si le mode est 0
        {
            if (living instanceof EntityPlayer) {
                for (int x1 = -1; x1 < 2; x1++) {
                    for (int y1 = -1; y1 < 2; y1++) {
                        for (int z1 = -1; z1 < 2; z1++) {

                            //verifie que le bloc a une hardness superieur a 0 pour la bedrock et verifie que le block n'est pas egal a de l'obsi...
                            if (world.getBlock(x + x1, y + y1, z + z1).getBlockHardness(world, z1, z1, z1) >= 0.0F &&
                                    world.getBlock(x + x1, y + y1, z + z1) == UraBlocks.ura_ore ||
                                    world.getBlock(x + x1, y + y1, z + z1) == UraBlocks.silver_ore ||
                                    world.getBlock(x + x1, y + y1, z + z1) == Blocks.iron_ore ||
                                    world.getBlock(x + x1, y + y1, z + z1) == Blocks.gold_ore ||
                                    world.getBlock(x + x1, y + y1, z + z1) == Blocks.stone) {
                                world.getBlock(x + x1, y + y1, z + z1).harvestBlock(world, (EntityPlayer) living, x + x1, y + y1, z + z1, world.getBlockMetadata(x + x1, y + y1, z + z1));
                                world.setBlockToAir(x + x1, y + y1, z + z1);
                            }
                        }
                    }
                }
            }
        } else if (stack.getTagCompound().getByte("mode") == 1) { // si le mode est 1
            if (living instanceof EntityPlayer) {
                for (int x1 = -1; x1 < 2; x1++) {
                    for (int y1 = -1; y1 < 2; y1++) {
                        for (int z1 = -1; z1 < 2; z1++) {

                            //verifie que le bloc a une hardness superieur a 0 pour la bedrock et verifie que le block n'est pas egal a de l'obsi...
                            if (world.getBlock(x + x1, y + y1, z + z1).getBlockHardness(world, z1, z1, z1) >= 0.0F &&
                                    world.getBlock(x + x1, y + y1, z + z1) == UraBlocks.ura_ore ||
                                    world.getBlock(x + x1, y + y1, z + z1) == UraBlocks.silver_ore ||
                                    world.getBlock(x + x1, y + y1, z + z1) == Blocks.iron_ore ||
                                    world.getBlock(x + x1, y + y1, z + z1) == Blocks.gold_ore ||
                                    world.getBlock(x + x1, y + y1, z + z1) == Blocks.stone) {
                                world.getBlock(x + x1, y + y1, z + z1).harvestBlock(world, (EntityPlayer) living, x + x1, y + y1, z + z1, world.getBlockMetadata(x + x1, y + y1, z + z1));
                                world.setBlockToAir(x + x1, y + y1, z + z1);
                            }
                        }
                    }
                }
            }
        } else {

        }
        return super.onBlockDestroyed(stack, world, block, x, y, z, living);
    }
}
