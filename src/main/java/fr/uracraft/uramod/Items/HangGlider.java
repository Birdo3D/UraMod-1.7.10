package fr.uracraft.uramod.Items;

import fr.uracraft.uramod.common.UraItems;
import fr.uracraft.uramod.common.UraMod;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class HangGlider extends Item {

    public static List<EntityPlayer> usingHangGliderClient = new ArrayList<EntityPlayer>();
    public static List<EntityPlayer> usingHangGliderServer = new ArrayList<EntityPlayer>();

    public HangGlider() {
        this.setUnlocalizedName("hang_glider");
        this.setTextureName(UraMod.MODID + ":hang_glider");
        this.setCreativeTab(UraMod.uramodcreativetab);
        this.setMaxStackSize(1);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (world.isRemote) {
            if (!usingHangGliderClient.contains(player)) {
                usingHangGliderClient.add(player);
                return stack;
            }
            usingHangGliderClient.remove(player);
        }
        if (!world.isRemote) {
            if (!usingHangGliderServer.contains(player)) {
                usingHangGliderServer.add(player);
                return stack;
            }
            usingHangGliderServer.remove(player);
        }
        return stack;
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
        super.onUpdate(stack, world, entity, p_77663_4_, p_77663_5_);
        if (!(entity instanceof EntityPlayer)) return;
        EntityPlayer player = (EntityPlayer)entity;
        if(player.worldObj.isRemote) {
            if(player.isCollidedVertically || (player.inventory.getCurrentItem() == null || player.inventory.getCurrentItem().getItem() == null || player.inventory.getCurrentItem().getItem() != UraItems.hang_glider)) usingHangGliderClient.remove(player);
            if(usingHangGliderClient.contains(player) && player.motionY < 0.0D) {
                double horizontalSpeed, verticalSpeed;
                if (player.isSneaking()) {
                    horizontalSpeed = 0.1D;
                    verticalSpeed = 0.9D;
                } else {
                    horizontalSpeed = 0.03D;
                    verticalSpeed = 0.7D;
                }
                player.motionY *= verticalSpeed;
                double x = Math.cos(Math.toRadians((player.rotationYawHead + 90.0F))) * horizontalSpeed;
                double z = Math.sin(Math.toRadians((player.rotationYawHead + 90.0F))) * horizontalSpeed;
                player.motionX += x;
                player.motionZ += z;
                player.fallDistance = 0.1F;
            }
        }
        if (!player.worldObj.isRemote) {
            if (player.isCollidedVertically || (player.inventory.getCurrentItem() == null || player.inventory.getCurrentItem().getItem() == null || player.inventory.getCurrentItem().getItem() != UraItems.hang_glider)) usingHangGliderServer.remove(player);
            if (usingHangGliderServer.contains(player) && player.motionY < 0.0D) {
                double horizontalSpeed, verticalSpeed;
                if (player.isSneaking()) {
                    horizontalSpeed = 0.1D;
                    verticalSpeed = 0.7D;
                } else {
                    horizontalSpeed = 0.03D;
                    verticalSpeed = 0.4D;
                }
                player.motionY *= verticalSpeed;
                double x = Math.cos(Math.toRadians((player.rotationYawHead + 90.0F))) * horizontalSpeed;
                double z = Math.sin(Math.toRadians((player.rotationYawHead + 90.0F))) * horizontalSpeed;
                player.motionX += x;
                player.motionZ += z;
                player.fallDistance = 0.1F;
            }
        }
    }
}