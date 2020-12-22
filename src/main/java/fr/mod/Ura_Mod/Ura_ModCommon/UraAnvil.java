package fr.mod.Ura_Mod.Ura_ModCommon;



import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.mod.Ura_Mod.proxy.ClientProxy;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAnvil;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiRepair;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MinecraftError;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.MinecraftException;
import net.minecraft.world.World;

import java.util.List;

import static fr.mod.Ura_Mod.Ura_ModCommon.Ura_ModMain.MODID;

public class UraAnvil extends BlockFalling
{
    public static final String[] anvilDamageNames = new String[] {"intact", "slightlyDamaged", "veryDamaged"};
    private static final String[] anvilIconNames = new String[] {MODID+"anvil_top_damaged_0", MODID+"anvil_top_damaged_1",MODID+ "anvil_top_damaged_2"};
    @SideOnly(Side.CLIENT)
    public int anvilRenderSide;
    @SideOnly(Side.CLIENT)
    private IIcon[] anvilIcons;
    private static final String __OBFID = "CL_00000192";

    protected UraAnvil()
    {
        super(Material.anvil);
        this.setLightOpacity(0);
        this.setCreativeTab(CreativeTabs.tabDecorations);
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {
        if (this.anvilRenderSide == 3 && p_149691_1_ == 1)
        {
            int k = (p_149691_2_ >> 2) % this.anvilIcons.length;
            return this.anvilIcons[k];
        }
        else
        {
            return this.blockIcon;
        }
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.blockIcon = p_149651_1_.registerIcon("anvil_base");
        this.anvilIcons = new IIcon[anvilIconNames.length];

        for (int i = 0; i < this.anvilIcons.length; ++i)
        {
            this.anvilIcons[i] = p_149651_1_.registerIcon(anvilIconNames[i]);
        }
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
    {
        int l = MathHelper.floor_double((double)(p_149689_5_.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        int i1 = p_149689_1_.getBlockMetadata(p_149689_2_, p_149689_3_, p_149689_4_) >> 2;
        ++l;
        l %= 4;

        if (l == 0)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 2 | i1 << 2, 2);
        }

        if (l == 1)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 3 | i1 << 2, 2);
        }

        if (l == 2)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 0 | i1 << 2, 2);
        }

        if (l == 3)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 1 | i1 << 2, 2);
        }
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
        if (p_149727_1_.isRemote)
        {
            return true;
        }
        else
        {
            p_149727_5_.displayGUIAnvil(p_149727_2_, p_149727_3_, p_149727_4_);
            return true;
        }
    }

    /**
     * The type of render function that is called for this block
     */
    public int getRenderType()
    {
        return ClientProxy.renderTableId;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int p_149692_1_)
    {
        return p_149692_1_ >> 2;
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_)
    {
        int l = p_149719_1_.getBlockMetadata(p_149719_2_, p_149719_3_, p_149719_4_) & 3;

        if (l != 3 && l != 1)
        {
            this.setBlockBounds(0.125F, 0.0F, 0.0F, 0.875F, 1.0F, 1.0F);
        }
        else
        {
            this.setBlockBounds(0.0F, 0.0F, 0.125F, 1.0F, 1.0F, 0.875F);
        }
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_)
    {
        p_149666_3_.add(new ItemStack(p_149666_1_, 1, 0));
        p_149666_3_.add(new ItemStack(p_149666_1_, 1, 1));
        p_149666_3_.add(new ItemStack(p_149666_1_, 1, 2));
    }

    protected void func_149829_a(EntityFallingBlock p_149829_1_)
    {
        p_149829_1_.func_145806_a(true);
    }

    public void func_149828_a(World p_149828_1_, int p_149828_2_, int p_149828_3_, int p_149828_4_, int p_149828_5_)
    {
        p_149828_1_.playAuxSFX(1022, p_149828_2_, p_149828_3_, p_149828_4_, 0);
    }

    /**
     * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
     * coordinates.  Args: blockAccess, x, y, z, side
     */
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_)
    {
        return true;
    }



        /*extends BlockFalling {
   // public static final String[] anvilDamageNames = new String[] {"intact", "slightlyDamaged", "veryDamaged"};
    private static final String[] anvilIconNames = new String[] {MODID+"anvil_top_damaged_0", MODID+"anvil_top_damaged_1", MODID+"anvil_top_damaged_2"};
    @SideOnly(Side.CLIENT)
    public int uraanvilRenderSide;
    @SideOnly(Side.CLIENT)
    private IIcon[] anvilIcons;

    protected UraAnvil()
    {

        this.setLightOpacity(0);
    }




   public boolean renderAsNormalBlock()
    {
        return false;
    }




    public boolean isOpaqueCube()
    {
        return false;
    }



    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_)
    {


            return this.blockIcon;



    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.blockIcon = p_149651_1_.registerIcon(MODID+":anvil_base");
        this.anvilIcons = new IIcon[anvilIconNames.length];

        for (int i = 0; i < this.anvilIcons.length; ++i)
        {
            this.anvilIcons[i] = p_149651_1_.registerIcon(anvilIconNames[i]);
        }
    }

    @Override
    public boolean hasTileEntity(int metadata) //Permet de savoir si le bloc a un TileEntity
    {
        return false;
    }

    public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
    {
        int l = MathHelper.floor_double((double)(p_149689_5_.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        int i1 = p_149689_1_.getBlockMetadata(p_149689_2_, p_149689_3_, p_149689_4_) >> 2;
        ++l;
        l %= 4;

        if (l == 0)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 2 | i1 << 2, 2);
        }

        if (l == 1)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 3 | i1 << 2, 2);
        }

        if (l == 2)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 0 | i1 << 2, 2);
        }

        if (l == 3)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 1 | i1 << 2, 2);
        }
    }

    @Override
    public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if(!p_149727_1_.isRemote) {
            try {
                p_149727_5_.displayGUIAnvil(p_149727_2_, p_149727_3_, p_149727_4_);
            } catch (MinecraftError e) {
                e.printStackTrace();
            }
        }
        return true;    }
    /*
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitx, float hity, float hitz)
    {

        if (world.isRemote)
        {

            return true;
        }
        else
        {

            player.displayGUIWorkbench(x,y,z);
          //  player.displayGUIAnvil(x,y,z);

            return true;
        }
    }





  //  public int getRenderType()
  //  {
  //      return 35;
  //  }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side)
    {
        return true;
    }
    public int damageDropped(int p_149692_1_)
    {
        return p_149692_1_ >> 2;
    }
    @SideOnly(Side.CLIENT)
    public int getRenderType()
    {
        return ClientProxy.renderTableId;
    }


    public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_)
    {
        int l = p_149719_1_.getBlockMetadata(p_149719_2_, p_149719_3_, p_149719_4_) & 3;

        if (l != 3 && l != 1)
        {
            this.setBlockBounds(0.125F, 0.0F, 0.0F, 0.875F, 1.0F, 1.0F);
        }
        else
        {
            this.setBlockBounds(0.0F, 0.0F, 0.125F, 1.0F, 1.0F, 0.875F);
        }
    }



    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item p_149666_1_, CreativeTabs p_149666_2_, List p_149666_3_)
    {
        p_149666_3_.add(new ItemStack(p_149666_1_, 1, 0));
        p_149666_3_.add(new ItemStack(p_149666_1_, 1, 1));
        p_149666_3_.add(new ItemStack(p_149666_1_, 1, 2));
    }

    protected void func_149829_a(EntityFallingBlock p_149829_1_)
    {
        p_149829_1_.func_145806_a(true);
    }

    public void func_149828_a(World p_149828_1_, int p_149828_2_, int p_149828_3_, int p_149828_4_, int p_149828_5_)
    {
        p_149828_1_.playAuxSFX(1022, p_149828_2_, p_149828_3_, p_149828_4_, 0);
    }

*/
}
