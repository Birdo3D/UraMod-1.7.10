package fr.mod.Ura_Mod.tools;

import com.mojang.realmsclient.dto.PlayerInfo;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.mod.Ura_Mod.Ura_ModCommon.Ura_ModMain;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;

import java.util.List;

public class ItemModHamer extends ItemPickaxe {
    public int pos;

    public ItemModHamer(ToolMaterial material) {
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
                                world.getBlock(x + x1, y + y1, z + z1) == Ura_ModMain.granite ||
                                world.getBlock(x + x1, y + y1, z + z1) == Ura_ModMain.andesite ||
                                world.getBlock(x + x1, y + y1, z + z1) == Ura_ModMain.diorite ||
                                world.getBlock(x + x1, y + y1, z + z1) == Ura_ModMain.UraOre ||
                                world.getBlock(x + x1, y + y1, z + z1) == Ura_ModMain.ArgentOre ||
                                world.getBlock(x + x1, y + y1, z + z1) == Ura_ModMain.NeodymeOre ||
                                world.getBlock(x + x1, y + y1, z + z1) == Blocks.iron_ore ||
                                world.getBlock(x + x1, y + y1, z + z1) == Blocks.gold_ore ||
                                world.getBlock(x + x1, y + y1, z + z1) == Blocks.stone) {



                         /*   for (int i = 0; i < world.getBlock(x + x1, y + y1, z + z1).getDrops(world, x + x1, y + y1, z + z1, world.getBlockMetadata(x + x1, y + y1, z + z1), 0).size(); i++) {
                                ((EntityPlayer) living).inventory.addItemStackToInventory(world.getBlock(x + x1, y + y1, z + z1).getDrops(world, x + x1, y + y1, z + z1, world.getBlockMetadata(x + x1, y + y1, z + z1), 0).get(i));
                                world.getBlock(x + x1, y + y1, z + z1).getDrops(world, x + x1, y + y1, z + z1, world.getBlockMetadata(x + x1, y + y1, z + z1), 0).clear();
                            }*/
                            world.getBlock(x + x1, y + y1, z + z1).harvestBlock(world, (EntityPlayer) living, x + x1, y + y1, z + z1, world.getBlockMetadata(x + x1, y + y1, z + z1));
                            world.setBlockToAir(x + x1, y + y1, z + z1);
                        }
                    }
                }
            }
        }
        return super.onBlockDestroyed(stack, world, block, x, y, z, living);
    }
    public boolean hasEnchantment(ItemStack stack, int id) {
        boolean flag = false;
        if (stack.getEnchantmentTagList() != null) {
            for (int i = 0; i < stack.getEnchantmentTagList().tagCount(); i++) {
                if (stack.getEnchantmentTagList().getCompoundTagAt(i).getShort("id") == id) {
                    flag = true;
                } else {
                    flag = false;
                }
            }
        }
        return flag;
    }
}