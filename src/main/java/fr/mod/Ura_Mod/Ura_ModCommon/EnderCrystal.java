package fr.mod.Ura_Mod.Ura_ModCommon;

import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EnderCrystal extends Item {

    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        if(!world.isRemote)
        {
            if(world.getBlock( x, y, z) == Blocks.obsidian || world.getBlock( x, y, z) == Blocks.bedrock) {
                EntityEnderCrystal enderCrystal = new EntityEnderCrystal(world);
                enderCrystal.setPosition(x + 0.5, y + 1, z + 0.5);
                world.spawnEntityInWorld(enderCrystal);
                return true;
            }
        }
        return false;
    }
}
