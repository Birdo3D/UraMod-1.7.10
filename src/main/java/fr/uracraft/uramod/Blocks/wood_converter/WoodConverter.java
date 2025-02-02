package fr.uracraft.uramod.Blocks.wood_converter;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.uracraft.uramod.common.UraMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import org.lwjgl.Sys;

public class WoodConverter extends BlockContainer {

    private IIcon top, bottom;

    public WoodConverter() {
        super(Material.wood);
        this.setHardness(2F);
        this.setResistance(2F);
        this.setLightOpacity(255);
        this.setHarvestLevel("axe", 0);
        this.useNeighborBrightness = true;
        this.setStepSound(soundTypeStone);
        this.setBlockTextureName(UraMod.MODID + ":wood_converter");
        this.setBlockName("wood_converter");
        this.setCreativeTab(UraMod.uramodcreativetab);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int id) {
        return new TileEntityWoodConverter();
    }

    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }

    @SubscribeEvent
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitx, float hity, float hitz) {
        if (!world.isRemote) {
            player.openGui(UraMod.instance, 0, world, x, y, z);
        }
        System.out.println("clikkkkk");
        return true;
    }

    public void registerBlockIcons(IIconRegister iiconRegister) {
        this.blockIcon = iiconRegister.registerIcon(UraMod.MODID + ":woodconv");
        this.top = iiconRegister.registerIcon(UraMod.MODID + ":woodconv_top");
        this.bottom = iiconRegister.registerIcon(UraMod.MODID + ":woodconv_bottom");
    }

    public IIcon getIcon(int side, int metadata) {
        if (side == 0) {
            return this.bottom;
        } else if (side == 1) {
            return this.top;
        }
        return this.blockIcon;
    }

    public void breakBlock(World world, int x, int y, int z, Block block, int metadata) {

        TileEntity tileentity = world.getTileEntity(x, y, z);

        if (tileentity instanceof IInventory) {
            IInventory inv = (IInventory) tileentity;
            for (int i1 = 0; i1 < inv.getSizeInventory(); ++i1) {
                ItemStack itemstack = inv.getStackInSlot(i1);

                if (itemstack != null) {
                    float f = world.rand.nextFloat() * 0.8F + 0.1F;
                    float f1 = world.rand.nextFloat() * 0.8F + 0.1F;
                    EntityItem entityitem;

                    for (float f2 = world.rand.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; world.spawnEntityInWorld(entityitem)) {
                        int j1 = world.rand.nextInt(21) + 10;

                        if (j1 > itemstack.stackSize) {
                            j1 = itemstack.stackSize;
                        }

                        itemstack.stackSize -= j1;
                        entityitem = new EntityItem(world, (double) ((float) x + f), (double) ((float) y + f1), (double) ((float) z + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
                        float f3 = 0.05F;
                        entityitem.motionX = (double) ((float) world.rand.nextGaussian() * f3);
                        entityitem.motionY = (double) ((float) world.rand.nextGaussian() * f3 + 0.2F);
                        entityitem.motionZ = (double) ((float) world.rand.nextGaussian() * f3);

                        if (itemstack.hasTagCompound()) {
                            entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
                        }
                    }
                }
            }

            world.func_147453_f(x, y, z, block);
        }


        super.breakBlock(world, x, y, z, block, metadata);
    }
}

