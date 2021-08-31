package fr.uracraft.uramod.Blocks;

import fr.uracraft.uramod.Utils.CustomDamageSources;
import fr.uracraft.uramod.common.UraFluids;
import fr.uracraft.uramod.common.UraMod;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;

public class FakeWaterBlock extends BlockFluidClassic {

    private IIcon stillIcon, flowingIcon;

    public FakeWaterBlock()
    {
        super(UraFluids.fake_water, Material.water);
    }

    public IIcon getIcon(int side, int meta)
    {
        return (side == 0 || side == 1) ? stillIcon : flowingIcon;
    }

    public void registerBlockIcons(IIconRegister register)
    {
        stillIcon = register.registerIcon(UraMod.MODID + ":fake_water_still");
        flowingIcon = register.registerIcon(UraMod.MODID + ":fake_water_flow");
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

    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity){
        if(!world.isRemote)
            if(entity instanceof EntityPlayer && !((EntityPlayer) entity).capabilities.isCreativeMode)
                ((EntityPlayer) entity).attackEntityFrom(CustomDamageSources.fakeWater, 5F);
            else
                entity.attackEntityFrom(CustomDamageSources.fakeWater, 5F);
    }
}
