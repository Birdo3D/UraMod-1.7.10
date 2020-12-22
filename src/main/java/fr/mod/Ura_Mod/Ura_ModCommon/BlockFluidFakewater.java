package fr.mod.Ura_Mod.Ura_ModCommon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BlockFluidFakewater extends BlockFluidClassic {
    private IIcon stillIcon, flowingIcon;

    public BlockFluidFakewater(Fluid fluid, Material material)
    {
        super(fluid, material);
    }

    public IIcon getIcon(int side, int meta)
    {
        return (side == 0 || side == 1) ? stillIcon : flowingIcon;
    }

    public void registerBlockIcons(IIconRegister register)
    {
        stillIcon = register.registerIcon("uramod:fakewater_still");
        //stillIcon = Blocks;
        flowingIcon = register.registerIcon("uramod:fakewater_flow");
    }

    private boolean liquidCanDisplaceBlock(World world, int x, int y, int z){
        Material material = world.getBlock(x, y, z).getMaterial();
        return material == blockMaterial ? false : (material == Material.lava ? false : !blockedBy(world, x, y, z));
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

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity){
        if(!world.isRemote)
            if(entity instanceof EntityPlayer && !((EntityPlayer) entity).capabilities.isCreativeMode)
                ((EntityPlayer) entity).attackEntityFrom(CustomDamageSources.fakeWater, 5F);
            else
                entity.attackEntityFrom(CustomDamageSources.fakeWater, 5F);
    }

    private boolean blockedBy(World world, int x, int y, int z){
        Block block = world.getBlock(x, y, z);
        return block != Blocks.wooden_door && block != Blocks.iron_door && block != Blocks.standing_sign && block != Blocks.ladder && block != Blocks.reeds ? (block.getMaterial() == Material.portal ? true : block.getMaterial().blocksMovement()) : true;
    }
}