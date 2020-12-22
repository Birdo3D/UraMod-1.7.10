package fr.mod.Ura_Mod.Ura_ModCommon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockCrops;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.Random;

public class Iridium_Croops extends BlockCrops {
    private IIcon[] field_149869_a;
    protected Item func_149865_P() {
        double p = Math.random() * ( 50 - 1 );
        int v = (int)p;
        System.out.println(v);
        if (v == 5) {
            return Ura_ModMain.UraIngot;
        }else {
            return Ura_ModMain.ura_carrot;
        }
    }
    protected Item func_149866_i(){double d = Math.random() * ( 14 - 1 );
        int value = (int)d;
        System.out.println("seed"+d);
        if (value == 5) {
            return Ura_ModMain.ura_seed;
        }else {
            return null;
        }
    }
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        if (p_149691_2_ < 7)
        {
            if (p_149691_2_ == 6)
            {
                p_149691_2_ = 5;
            }

            return this.field_149869_a[p_149691_2_ >> 1];
        }
        else
        {
            return this.field_149869_a[3];
        }
    }
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.field_149869_a = new IIcon[4];

        for (int i = 0; i < this.field_149869_a.length; ++i)
        {
            this.field_149869_a[i] = p_149651_1_.registerIcon(this.getTextureName() + "_stage_" + i);
        }
    }
    @Override
    public boolean func_149852_a(World p_149852_1_, Random p_149852_2_, int p_149852_3_, int p_149852_4_, int p_149852_5_) {
        return false;
    }

}
