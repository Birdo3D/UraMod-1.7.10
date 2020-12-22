package fr.mod.Ura_Mod.Ura_ModCommon;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockFluidGlowstoneliquide extends BlockFluidClassic
{
    private IIcon stillIcon, flowingIcon;

    public BlockFluidGlowstoneliquide(Fluid fluid, Material material)
    {
        super(fluid, material);
    }

    public IIcon getIcon(int side, int meta)
    {
        return (side == 0 || side == 1) ? stillIcon : flowingIcon;
    }

    public void registerBlockIcons(IIconRegister register)
    {
        stillIcon = register.registerIcon(Ura_ModMain.MODID + "glowstone_fluid_still");
        flowingIcon = register.registerIcon(Ura_ModMain.MODID + "glowstone_fluid_flow");
    }

    public boolean canDisplace(IBlockAccess world, int x, int y, int z)
    {
        if(world.getBlock(x, y, z).getMaterial().isLiquid())
        {
            return false;
        }
        return super.canDisplace(world, x, y, z);
    }

    public boolean displaceIfPossible(World world, int x, int y, int z)
    {
        if(world.getBlock(x, y, z).getMaterial().isLiquid())
        {
            return false;
        }
        return super.displaceIfPossible(world, x, y, z);
    }
}