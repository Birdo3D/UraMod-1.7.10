package fr.mod.Ura_Mod;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.mod.Ura_Mod.Ura_ModCommon.GuiHandler;
import fr.mod.Ura_Mod.Ura_ModCommon.Ura_ModMain;
import net.minecraft.block.BlockWorkbench;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CraftingTable extends Item {

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        openPortableCrafting(entityPlayer, itemStack);
        return itemStack;
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        openPortableCrafting(entityPlayer, itemStack);
        return true;
    }

    public void openPortableCrafting(EntityPlayer entityPlayer, ItemStack itemStack) {
        if(!entityPlayer.worldObj.isRemote) {

                entityPlayer.openGui(Ura_ModMain.instance, GuiHandler.GUI_PORTABLE_CRAFTING, entityPlayer.worldObj, (int) entityPlayer.posX, (int) entityPlayer.posY, (int) entityPlayer.posZ);

        }
    }
}
