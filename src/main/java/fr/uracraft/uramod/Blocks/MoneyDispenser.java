package fr.uracraft.uramod.Blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.uracraft.uramod.common.UraMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class MoneyDispenser extends Block {

    private IIcon Front, Top;

    public MoneyDispenser() {
        super(Material.rock);
        this.setHardness(-1F);
        this.setResistance(3600000F);
        this.setLightOpacity(255);
        this.useNeighborBrightness = true;
        this.setStepSound(soundTypeStone);
        this.setBlockName("money_dispenser");
        this.setCreativeTab(UraMod.uramodcreativetab);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        if(p_149691_1_ == 3  && p_149691_2_ == 0) return this.Front;
        if (p_149691_1_ == 1) return this.Top;
        if (p_149691_1_ == 0) return this.Top;
        if (p_149691_1_ == p_149691_2_) return this.Front;
        return this.blockIcon;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.blockIcon = p_149651_1_.registerIcon(UraMod.MODID + ":money_dispenser");
        this.Front = p_149651_1_.registerIcon(UraMod.MODID + ":money_dispenser_front");
        this.Top = p_149651_1_.registerIcon(UraMod.MODID + ":money_dispenser_top");
    }

    public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_) {
        int l = MathHelper.floor_double((double) (p_149689_5_.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0) {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 2, 2);
        }

        if (l == 1) {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 5, 2);
        }

        if (l == 2) {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 3, 2);
        }

        if (l == 3) {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 4, 2);
        }
    }
}
