package fr.mod.Ura_Mod.Ura_ModCommon;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class GrassPath extends Block {

    @SideOnly(Side.CLIENT)
    private IIcon sideIcon;

    public GrassPath() {
        super(Material.grass);
        this.setHardness(0.6F);
        this.setLightOpacity(255);
        this.setHarvestLevel("shovel", 0);
        this.useNeighborBrightness = true;
        this.setStepSound(soundTypeGrass);
        this.slipperiness = 1.5F; //vitesse
        this.setBlockTextureName(Ura_ModMain.MODID + ":grass_path");
        this.setBlockName("terre_labouree");
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.9375F, 1.0F);
        this.setCreativeTab(Ura_ModMain.FururModCreativeTabs);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + 1, z + 1);
    }

    @Override
    public Item getItemDropped(int meta, Random rand, int fortune) {
        return Blocks.dirt.getItemDropped(meta, rand, fortune);
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
        if (world.getBlock(x, y + 1, z).getMaterial().isSolid())
            world.setBlock(x, y, z, Blocks.dirt);
    }

    @Override
    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        return side == ForgeDirection.DOWN;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return side == 0 ? Blocks.dirt.getIcon(side, 0) : side == 1 ? blockIcon : sideIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
        blockIcon = reg.registerIcon(getTextureName() + "_top");
        sideIcon = reg.registerIcon(getTextureName() + "_side");
    }
}