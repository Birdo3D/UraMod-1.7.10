package fr.mod.Ura_Mod.Ura_ModCommon;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import java.util.Random;

public class Iriduim_Food extends ItemFood {

    public Iriduim_Food(int p_i45339_1_, float p_i45339_2_, boolean p_i45339_3_) {
        super(p_i45339_1_, p_i45339_2_, p_i45339_3_);

    }
    @Override
    public void onFoodEaten(ItemStack itemStack, World world, EntityPlayer player)
    {
        player.addPotionEffect(new PotionEffect(Random(),300,0));

    }
    static int Random() {
        Random randomGen = new Random();
        return randomGen.nextInt(22)+1;
    }

}
