package fr.mod.Ura_Mod.Ura_ModCommon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import scala.reflect.internal.Trees;

import java.util.Random;

public class BonesBlock extends Block {

    private IIcon top, bottom;

    protected BonesBlock(Material material) {
        super(material);
        this.setCreativeTab(Ura_ModMain.FururModCreativeTabs);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        if(p_149691_1_ == 3  && p_149691_2_ == 0) return this.top;
        if((p_149691_1_ == 4  && p_149691_2_ == 0)||(p_149691_1_ == 5  && p_149691_2_ == 0)) return this.blockIcon;
        if ((p_149691_1_ == 2 && p_149691_2_== 3)||(p_149691_1_ == 3 && p_149691_2_== 2)||(p_149691_1_ == 5 && p_149691_2_== 4)||(p_149691_1_ == 4 && p_149691_2_== 5)) return this.bottom;
        if ((p_149691_1_ == 2 && p_149691_2_== 5)||(p_149691_1_ == 5 && p_149691_2_== 2)||(p_149691_1_ == 5 && p_149691_2_== 3)||(p_149691_1_ == 3 && p_149691_2_== 5)||(p_149691_1_ == 2 && p_149691_2_== 4)||(p_149691_1_ == 4 && p_149691_2_== 2)||(p_149691_1_ == 4 && p_149691_2_== 3)||(p_149691_1_ == 3 && p_149691_2_== 4)) return this.blockIcon;
        if (p_149691_1_ == p_149691_2_) return this.top;
        return this.blockIcon;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.blockIcon = p_149651_1_.registerIcon(Ura_ModMain.MODID + ":bone_block");

        this.bottom = p_149651_1_.registerIcon(Ura_ModMain.MODID + ":bone_block_top");
        this.top = p_149651_1_.registerIcon(Ura_ModMain.MODID + ":bone_block_top");
    }

    public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
    {
        int l = MathHelper.floor_double((double)(p_149689_5_.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 2, 2);
        }

        if (l == 1)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 5, 2);
        }

        if (l == 2)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 3, 2);
        }

        if (l == 3)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 4, 2);
        }
    }
}
