package fr.mod.Ura_Mod.Ura_ModCommon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class Fence extends BlockFence {

    private final int meta;

    public Fence(int meta) {
        super(null, Material.wood);
        this.meta = meta;
        setHardness(2.0F);
        setResistance(5.0F);
        setStepSound(soundTypeWood);
        setCreativeTab(Ura_ModMain.FururModCreativeTabs);
    }

    @Override
    public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
        return side == ForgeDirection.UP;
    }

    @Override
    public boolean canConnectFenceTo(IBlockAccess world, int x, int y, int z) {
        Block block = world.getBlock(x, y, z);
        return super.canConnectFenceTo(world, x, y, z) || block instanceof BlockFence || block instanceof BlockFenceGate;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister reg) {
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return Blocks.planks.getIcon(side, this.meta);
    }
}