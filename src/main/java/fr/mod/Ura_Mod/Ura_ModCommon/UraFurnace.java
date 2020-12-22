package fr.mod.Ura_Mod.Ura_ModCommon;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import scala.reflect.internal.Trees;

import java.util.Random;

public class UraFurnace extends BlockContainer {

    private IIcon top, side;
    private final Random field_149933_a = new Random();
    private TileEntityUraFurnace tileMachineTuto;
    private boolean liturafurnace = false;

    private static boolean field_149934_M;


    protected UraFurnace(Material material, boolean lit) {
        super(material);
        this.setCreativeTab(CreativeTabs.tabBlock);
        this.liturafurnace = lit;

    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        if(p_149691_1_ == 3  && p_149691_2_ == 0) return this.side;
        if (p_149691_1_ == 1) return this.top;
        if (p_149691_1_ == 0) return this.top;
        if (p_149691_1_ == p_149691_2_) return this.side;
        return this.blockIcon;
    }

    /*
@SideOnly(Side.CLIENT)
public IIcon getIcon(int side, int metadata)
{
    if(side == 2)
    {
        return this.top;
    }
    if(side == 1)
    {
        return this.top;
    }
    if(side == 3)
    {
        return this.side;
    }
    if(side == 4)
    {
        return this.top;
    }
    if(side == 5)
    {
        return this.top;
    }
    if(side == 6)
    {
        return this.top;
    }

    return this.blockIcon;
}
*/





    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.blockIcon = p_149651_1_.registerIcon(Ura_ModMain.MODID + ":ura_furnace");

        if (!this.liturafurnace) this.side = p_149651_1_.registerIcon(Ura_ModMain.MODID + ":ura_furnace_front");
        else this.side = p_149651_1_.registerIcon(Ura_ModMain.MODID + ":ura_furnace_front_on");
        this.top = p_149651_1_.registerIcon(Ura_ModMain.MODID + ":ura_furnace");
    }
  /*  public void registerBlockIcons(IIconRegister iiconRegister)
    {
        this.blockIcon = iiconRegister.registerIcon(Ura_ModMain.MODID + ":ura_furnace");
        this.top = iiconRegister.registerIcon(Ura_ModMain.MODID + ":ura_furnace_front");
        this.bottom = iiconRegister.registerIcon(Ura_ModMain.MODID + ":ura_furnace_front_on");
    }

    public IIcon getIcon(int side, int metadata)
    {
        if(side == 3)
        {
            return this.top;
        }
        return this.blockIcon;
    }*/

    public TileEntity createNewTileEntity(World world, int metadata) //Instancie le TileEntity
    {
        return new TileEntityUraFurnace();
    }

    @Override
    public boolean hasTileEntity(int metadata) //Permet de savoir si le bloc a un TileEntity
    {
        return true;
    }
    @SubscribeEvent
    /*public void breakBlock(World world, int x, int y, int z, Block block, int metadata)
    {

        TileEntity tileentity = world.getTileEntity(x, y, z);

        if (tileentity instanceof IInventory)
        {
            IInventory inv = (IInventory)tileentity;
            for (int i1 = 0; i1 < inv.getSizeInventory(); ++i1)
            {
                ItemStack itemstack = inv.getStackInSlot(i1);

                if (itemstack != null)
                {
                    float f = world.rand.nextFloat() * 0.8F + 0.1F;
                    float f1 = world.rand.nextFloat() * 0.8F + 0.1F;
                    EntityItem entityitem;

                    for (float f2 = world.rand.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; world.spawnEntityInWorld(entityitem))
                    {
                        int j1 = world.rand.nextInt(21) + 10;

                        if (j1 > itemstack.stackSize)
                        {
                            j1 = itemstack.stackSize;
                        }

                        itemstack.stackSize -= j1;
                        entityitem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
                        float f3 = 0.05F;
                        entityitem.motionX = (double)((float)world.rand.nextGaussian() * f3);
                        entityitem.motionY = (double)((float)world.rand.nextGaussian() * f3 + 0.2F);
                        entityitem.motionZ = (double)((float)world.rand.nextGaussian() * f3);

                        if (itemstack.hasTagCompound())
                        {
                            entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
                        }
                    }
                }
            }

            world.func_147453_f(x, y, z, block);
        }

        super.breakBlock(world, x, y, z, block, metadata);
    }*/

    public void breakBlock(World world, int x, int y, int z, Block block, int metadata) {
        if (!field_149934_M) {
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

    @SubscribeEvent
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitx, float hity, float hitz)
    {
        if (world.isRemote)
        {
            return true;
        }
        else
        {
            player.openGui(Ura_ModMain.instance, 0, world, x, y, z);
            return true;
        }
    }
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
    {
        return Item.getItemFromBlock(Ura_ModMain.urafurnace);
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

        if (p_149689_6_.hasDisplayName())
        {
            ((TileEntityFurnace)p_149689_1_.getTileEntity(p_149689_2_, p_149689_3_, p_149689_4_)).func_145951_a(p_149689_6_.getDisplayName());
        }
    }

    public static void updateFurnaceBlockState(boolean p_149931_0_, World p_149931_1_, int p_149931_2_, int p_149931_3_, int p_149931_4_)
    {
        int l = p_149931_1_.getBlockMetadata(p_149931_2_, p_149931_3_, p_149931_4_);
        TileEntity tileentity = p_149931_1_.getTileEntity(p_149931_2_, p_149931_3_, p_149931_4_);
        field_149934_M = true;

        if (p_149931_0_)
        {
            p_149931_1_.setBlock(p_149931_2_, p_149931_3_, p_149931_4_, Ura_ModMain.lit_urafurnace);
        }
        else
        {
            p_149931_1_.setBlock(p_149931_2_, p_149931_3_, p_149931_4_, Ura_ModMain.urafurnace);
        }

        field_149934_M = false;
        p_149931_1_.setBlockMetadataWithNotify(p_149931_2_, p_149931_3_, p_149931_4_, l, 2);

        if (tileentity != null)
        {
            tileentity.validate();
            p_149931_1_.setTileEntity(p_149931_2_, p_149931_3_, p_149931_4_, tileentity);
        }
    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World p_149734_1_, int p_149734_2_, int p_149734_3_, int p_149734_4_, Random p_149734_5_) {

            if (this.liturafurnace) {
                int l = p_149734_1_.getBlockMetadata(p_149734_2_, p_149734_3_, p_149734_4_);
                float f = (float) p_149734_2_ + 0.5F;
                float f1 = (float) p_149734_3_ + 0.0F + p_149734_5_.nextFloat() * 6.0F / 16.0F;
                float f2 = (float) p_149734_4_ + 0.5F;
                float f3 = 0.52F;
                float f4 = p_149734_5_.nextFloat() * 0.6F - 0.3F;

                if (l == 4) {
                    p_149734_1_.spawnParticle("smoke", (double) (f - f3), (double) f1, (double) (f2 + f4), 0.0D, 0.0D, 0.0D);
                    p_149734_1_.spawnParticle("flame", (double) (f - f3), (double) f1, (double) (f2 + f4), 0.0D, 0.0D, 0.0D);
                } else if (l == 5) {
                    p_149734_1_.spawnParticle("smoke", (double) (f + f3), (double) f1, (double) (f2 + f4), 0.0D, 0.0D, 0.0D);
                    p_149734_1_.spawnParticle("flame", (double) (f + f3), (double) f1, (double) (f2 + f4), 0.0D, 0.0D, 0.0D);
                } else if (l == 2) {
                    p_149734_1_.spawnParticle("smoke", (double) (f + f4), (double) f1, (double) (f2 - f3), 0.0D, 0.0D, 0.0D);
                    p_149734_1_.spawnParticle("flame", (double) (f + f4), (double) f1, (double) (f2 - f3), 0.0D, 0.0D, 0.0D);
                } else if (l == 3) {
                    p_149734_1_.spawnParticle("smoke", (double) (f + f4), (double) f1, (double) (f2 + f3), 0.0D, 0.0D, 0.0D);
                    p_149734_1_.spawnParticle("flame", (double) (f + f4), (double) f1, (double) (f2 + f3), 0.0D, 0.0D, 0.0D);
                }
            }

    }


}
