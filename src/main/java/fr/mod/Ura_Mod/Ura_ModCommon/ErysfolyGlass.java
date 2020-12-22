package fr.mod.Ura_Mod.Ura_ModCommon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class ErysfolyGlass extends Block {

    private IIcon glass01, glass02, glass03, glass04, glass05, glass06, glass07, glass08, glass09, glass10;

    public ErysfolyGlass() {
        super(Material.rock);
        this.setResistance(1.5F);
        this.setBlockName("erysfoly_glass");
        this.setStepSound(Block.soundTypeStone);
        this.setHardness(0.3F);
        this.setCreativeTab(Ura_ModMain.UraModCreativeTabs);
    }

    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side){
        if(world.getBlock(x + 1, y, z) == Ura_ModMain.erysfoly_glass && world.getBlock(x - 1, y, z) == Ura_ModMain.erysfoly_glass) {
            return this.glass01;
        }
        else if(world.getBlock(x + 1, y, z) == Ura_ModMain.erysfoly_glass) {
            return this.glass02;
        }
        else if(world.getBlock(x - 1, y, z) == Ura_ModMain.erysfoly_glass) {
            return this.glass08;
        }
        else if(world.getBlock(x, y + 1, z) == Ura_ModMain.erysfoly_glass) {
            return this.glass03;
        }
        else if(world.getBlock(x, y - 1, z) == Ura_ModMain.erysfoly_glass) {
            return this.glass03;
        }
        else if(world.getBlock(x, y, z + 1) == Ura_ModMain.erysfoly_glass) {
            return this.glass05;
        }
        else if(world.getBlock(x, y, z - 1) == Ura_ModMain.erysfoly_glass) {
            return this.glass06;
        }
        else {
            return this.blockIcon;
        }
        /*if(world.getBlock(x + 1, y, z) == Ura_ModMain.erysfoly_glass) return this.glass07;
        if(world.getBlock(x + 1, y, z) == Ura_ModMain.erysfoly_glass) return this.glass08;
        if(world.getBlock(x + 1, y, z) == Ura_ModMain.erysfoly_glass) return this.glass09;
        if(world.getBlock(x + 1, y, z) == Ura_ModMain.erysfoly_glass) return this.glass10;*/

    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_){
        this.blockIcon = p_149651_1_.registerIcon(Ura_ModMain.MODID + ":glass00");
        this.glass01 = p_149651_1_.registerIcon(Ura_ModMain.MODID + ":glass01");
        this.glass02 = p_149651_1_.registerIcon(Ura_ModMain.MODID + ":glass02");
        this.glass03 = p_149651_1_.registerIcon(Ura_ModMain.MODID + ":glass03");
        this.glass04 = p_149651_1_.registerIcon(Ura_ModMain.MODID + ":glass04");
        this.glass05 = p_149651_1_.registerIcon(Ura_ModMain.MODID + ":glass05");
        this.glass06 = p_149651_1_.registerIcon(Ura_ModMain.MODID + ":glass06");
        this.glass07 = p_149651_1_.registerIcon(Ura_ModMain.MODID + ":glass07");
        this.glass08 = p_149651_1_.registerIcon(Ura_ModMain.MODID + ":glass08");
        this.glass09 = p_149651_1_.registerIcon(Ura_ModMain.MODID + ":glass09");
        this.glass10 = p_149651_1_.registerIcon(Ura_ModMain.MODID + ":glass10");
    }

    public boolean isOpaqueCube()
    {
        return false;
    }
}