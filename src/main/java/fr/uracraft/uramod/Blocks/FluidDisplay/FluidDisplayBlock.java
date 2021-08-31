package fr.uracraft.uramod.Blocks.FluidDisplay;

import fr.uracraft.uramod.common.UraMod;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class FluidDisplayBlock extends BlockContainer {
    public FluidDisplayBlock() {
        super(Material.glass);
        this.setBlockName("fluid_display");
        this.setCreativeTab(UraMod.uramodcreativetab);
        this.setHardness(0.3F);
        this.setResistance(1.5F);
        this.setLightOpacity(255);
        this.setHarvestLevel("pickaxe", 2);
        this.useNeighborBrightness = true;
        this.setStepSound(soundTypeGlass);
    }

    public TileEntity createNewTileEntity(World world, int par2) {
        return (TileEntity)new FluidDisplayTileEntity();
    }

    public IIcon getIcon(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        FluidDisplayTileEntity te = (FluidDisplayTileEntity)par1IBlockAccess.getTileEntity(par2, par3, par4);
        if (te == null || te.getFluidName().equals("") || FluidRegistry.getFluid(te.getFluidName()) == null)
            return this.blockIcon;
        if (!te.flowing())
            return FluidRegistry.getFluid(te.getFluidName()).getIcon();
        return FluidRegistry.getFluid(te.getFluidName()).getFlowingIcon();
    }

    public void registerBlockIcons(IIconRegister par1IconRegister) {
        this.blockIcon = par1IconRegister.registerIcon(UraMod.MODID + ":fluid_display");
    }

    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int par6, float par7, float par8, float par9) {
        if (!world.isRemote) {
            FluidDisplayTileEntity te = (FluidDisplayTileEntity)world.getTileEntity(i, j, k);
            ItemStack currentItem = entityplayer.getCurrentEquippedItem();
            if (currentItem != null) {
                FluidStack liquid = FluidContainerRegistry.getFluidForFilledItem(currentItem);
                if (liquid != null) {
                    te.setFluidName(liquid.getFluid().getName());
                    te.markDirty();
                    world.markBlockForUpdate(i, j, k);
                    return true;
                }
            } else {
                te.toggleFlowing();
                return true;
            }
        }
        return false;
    }
}
