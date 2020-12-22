package fr.mod.Ura_Mod.Ura_ModCommon;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RandomOreItem extends Item {

    public RandomOreItem()
    {
        this.setMaxStackSize(1);
        this.setCreativeTab(Ura_ModMain.UraModCreativeTabs);
        this.setUnlocalizedName("randomoreitem");
        this.setTextureName(Ura_ModMain.MODID + ":randomoreitem");
    }
}
