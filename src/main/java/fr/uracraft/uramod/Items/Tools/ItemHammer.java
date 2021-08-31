package fr.uracraft.uramod.Items.Tools;

import fr.uracraft.uramod.common.UraBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemHammer extends ItemPickaxe {

    public ItemHammer(ToolMaterial material) {
        super(material);
    }

    public boolean onBlockDestroyed(ItemStack stack, World world, Block block, int x, int y, int z, EntityLivingBase living)
    {
        if(living instanceof EntityPlayer)
        {
            for(int x1 = -1; x1 < 2; x1++)
            {
                for(int y1 = -1; y1 < 2; y1++)
                {
                    for(int z1 = -1; z1 < 2; z1++) {

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
        return super.onBlockDestroyed(stack, world, block, x, y, z, living);
    }
}
