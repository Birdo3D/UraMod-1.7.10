package fr.mod.Ura_Mod.UraChest;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.mod.Ura_Mod.Ura_ModCommon.Ura_ModMain;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class UraChest extends BlockContainer {

    private IIcon top, bottom,side;
    public UraChest(Material p_i45397_1_) {
        super(p_i45397_1_);
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        if(p_149691_1_ == 3  && p_149691_2_ == 0) return this.top;
        if((p_149691_1_ == 4  && p_149691_2_ == 0)||(p_149691_1_ == 5  && p_149691_2_ == 0)) return this.side;
        if ((p_149691_1_ == 2 && p_149691_2_== 3)||(p_149691_1_ == 3 && p_149691_2_== 2)||(p_149691_1_ == 5 && p_149691_2_== 4)||(p_149691_1_ == 4 && p_149691_2_== 5)) return this.bottom;
        if ((p_149691_1_ == 2 && p_149691_2_== 5)||(p_149691_1_ == 5 && p_149691_2_== 2)||(p_149691_1_ == 5 && p_149691_2_== 3)||(p_149691_1_ == 3 && p_149691_2_== 5)||(p_149691_1_ == 2 && p_149691_2_== 4)||(p_149691_1_ == 4 && p_149691_2_== 2)||(p_149691_1_ == 4 && p_149691_2_== 3)||(p_149691_1_ == 3 && p_149691_2_== 4)) return this.side;
        if (p_149691_1_ == p_149691_2_) return this.top;
        return this.blockIcon;
    }
    public void registerBlockIcons(IIconRegister iiconRegister)
    {
        this.side = iiconRegister.registerIcon(Ura_ModMain.MODID + ":ura_barrel_side");
        this.blockIcon = iiconRegister.registerIcon(Ura_ModMain.MODID + ":ura_barrel");
        this.top = iiconRegister.registerIcon(Ura_ModMain.MODID + ":ura_barrel_top");
        this.bottom = iiconRegister.registerIcon(Ura_ModMain.MODID + ":ura_barrel_bottom");
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

    public TileEntity createNewTileEntity(World world, int p_149915_2_) {
        return new TileEntityUraChest();
    }
    @Override
    public boolean hasTileEntity(int metadata) //Permet de savoir si le bloc a un TileEntity
    {
        return true;
    }
    @SubscribeEvent
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitx, float hity, float hitz) {
        if (!world.isRemote) {
            player.openGui(Ura_ModMain.instance, 0, world, x, y, z);
        }
            return true;

    }
    @SubscribeEvent
    public void breakBlock(World world, int x, int y, int z, Block block, int metadata) {
        if (!world.isRemote) {
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
}
