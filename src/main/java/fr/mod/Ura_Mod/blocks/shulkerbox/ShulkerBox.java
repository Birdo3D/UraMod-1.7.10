package fr.mod.Ura_Mod.blocks.shulkerbox;

import java.util.ArrayList;

import fr.mod.Ura_Mod.Ura_ModCommon.Ura_ModMain;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class ShulkerBox extends BlockRotatedPillar
{
    private IIcon topBottom;

    public ShulkerBox(Material mat)
    {
        super(mat);
        this.setBlockName("shulker_box");
        this.setCreativeTab(Ura_ModMain.FururModCreativeTabs);
        this.setResistance(1.0F);
        this.setHardness(1.0F);
    }

    public void registerBlockIcons(IIconRegister register) {
        this.blockIcon = register.registerIcon(Ura_ModMain.MODID+":shulkerbox_side");
        this.topBottom = register.registerIcon(Ura_ModMain.MODID+":shulkerbox");
    }
    
    public IIcon getIcon(int side, int metadata) {
        int k = metadata & 12; 
        return k == 0 && (side == 1 || side == 0) ? this.topBottom : (k == 4 && (side == 5 || side == 4) ? this.topBottom : (k == 8 && (side == 2 || side == 3) ? this.topBottom : this.blockIcon));
    }
    
    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
    {
        return new ArrayList<ItemStack>();
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata) {
        return new TileEntityShulkerBox();
    }
    
    @Override
    public boolean hasTileEntity(int metadata) {
        return true;
    }
    
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if(world.isRemote)
            return true;
        else {
            player.openGui(Ura_ModMain.instance, 0, world, x, y, z);
            return true;
        }
    }
    
    private void createEntityItem(ItemStack itemstack, World world, int x, int y, int z) {
        if(itemstack != null) {
            float f = world.rand.nextFloat() * 0.8F + 0.1F;
            float f1 = world.rand.nextFloat() * 0.8F + 0.1F;
            EntityItem entityitem;
            for(float f2 = world.rand.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; world.spawnEntityInWorld(entityitem))
            {
                int j1 = world.rand.nextInt(21) + 10;

                if(j1 > itemstack.stackSize)
                {
                    j1 = itemstack.stackSize;
                }

                itemstack.stackSize -= j1;
                entityitem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
                float f3 = 0.05F;
                entityitem.motionX = (double)((float)world.rand.nextGaussian() * f3);
                entityitem.motionY = (double)((float)world.rand.nextGaussian() * f3 + 0.2F);
                entityitem.motionZ = (double)((float)world.rand.nextGaussian() * f3);

                if(itemstack.hasTagCompound())
                {
                    entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                }
            }
        }
    }
    
    public void breakBlock(World world, int x, int y, int z, Block block, int metadata) {
        TileEntity tileentity = world.getTileEntity(x, y, z);
        if(tileentity instanceof IInventory) {
            ItemStack stack = new ItemStack(this.getItemDropped(metadata, world.rand, 0), 1);
            TileEntityShulkerBox tile1 = ((TileEntityShulkerBox) tileentity);
            if(tile1.hasCustomInventoryName())
                stack.setStackDisplayName(tile1.getInventoryName());
            if(!stack.hasTagCompound())
                stack.setTagCompound(new NBTTagCompound());
            stack.getTagCompound().setTag("Tile", new NBTTagCompound());
            tile1.writeToNBTWithoutPos(stack.getTagCompound().getCompoundTag("Tile"));
            this.createEntityItem(stack, world, x, y, z);
            
            world.func_147453_f(x, y, z, block);
        }
        super.breakBlock(world, x, y, z, block, metadata);
    }
    
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack) {        
        TileEntity tile = world.getTileEntity(x, y, z);
        if(tile instanceof TileEntityShulkerBox) {
            if(stack.hasDisplayName())
                ((TileEntityShulkerBox)tile).setCustomName(stack.getDisplayName());
            if(stack.hasTagCompound() && stack.getTagCompound().hasKey("Tile", Constants.NBT.TAG_COMPOUND))
                ((TileEntityShulkerBox)tile).readFromNBTWithoutPos(stack.getTagCompound().getCompoundTag("Tile"));
        }
    }

    @Override
    protected IIcon getSideIcon(int p_150163_1_)
    {
        // TODO Auto-generated method stub
        return this.blockIcon;
    }
}
