package fr.mod.Ura_Mod.smithing_table;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.mod.Ura_Mod.Ura_ModCommon.GuiHandler;
import fr.mod.Ura_Mod.Ura_ModCommon.Ura_ModMain;
import fr.mod.Ura_Mod.iriduim_smithing_table.IriduimSmithingTableTileEntity;
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

public class SmithingTable extends BlockContainer {

    private IIcon top, bottom, front;

    public SmithingTable(Material p_i45386_1_) {
        super(p_i45386_1_);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new SmithingTableTileEntity();
    }
    @Override
    public boolean hasTileEntity(int metadata) //Permet de savoir si le bloc a un TileEntity
    {
        return true;
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

    public void registerBlockIcons(IIconRegister iiconRegister)
    {
        this.blockIcon = iiconRegister.registerIcon(Ura_ModMain.MODID + ":blacksmith_table");
        this.top = iiconRegister.registerIcon(Ura_ModMain.MODID + ":blacksmith_table_top");
        this.bottom = iiconRegister.registerIcon(Ura_ModMain.MODID + ":blacksmith_table_bottom");
        this.front = iiconRegister.registerIcon(Ura_ModMain.MODID + ":blacksmith_table_front");
    }

    public IIcon getIcon(int side, int metadata)
    {
        if(side == 1)
        {
            return this.top;
        }
        else if(side == 0)
        {
            return this.bottom;
        }
        else if(side == 5)
        {
            return this.front;
        }
        else if(side == 4)
        {
            return this.front;
        }
        return this.blockIcon;
    }
}
