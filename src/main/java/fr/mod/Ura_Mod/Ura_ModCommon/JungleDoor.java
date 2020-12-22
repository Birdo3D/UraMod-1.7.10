package fr.mod.Ura_Mod.Ura_ModCommon;

import java.util.Random;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class JungleDoor extends BlockDoor {

    public JungleDoor()
    {
        super(Material.anvil);
        this.setHardness(3.0F);
        this.setResistance(5.0F);
        this.disableStats();
    }

    @Override
    public Item getItemDropped(int par1, Random rand, int par3)
    {
        return Ura_ModMain.jungle_door_item;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z)
    {
        return Ura_ModMain.jungle_door_item;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
    {
        this.setBlockBoundsBasedOnState(world, x, y, z);
        return super.getSelectedBoundingBoxFromPool(world, x, y, z);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        this.setBlockBoundsBasedOnState(world, x, y, z);
        return super.getCollisionBoundingBoxFromPool(world, x, y, z);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess blockaccess, int x, int y, int z)
    {
        this.func_150011_b(this.func_150012_g(blockaccess, x, y, z));
    }

    @Override
    public int func_150013_e(IBlockAccess blockaccess, int x, int y, int z)
    {
        return this.func_150012_g(blockaccess, x, y, z) & 3;
    }

    @Override
    public boolean func_150015_f(IBlockAccess blockaccess, int x, int y, int z)
    {
        return (this.func_150012_g(blockaccess, x, y, z) & 4) != 0;
    }

    private void func_150011_b(int par1)
    {
        float f = 0.1875F;
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F);
        int j = par1 & 3;
        boolean flag = (par1 & 4) != 0;
        boolean flag1 = (par1 & 16) != 0;

        if(j == 0)
        {
            if(flag)
            {
                if(!flag1)
                {
                    this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
                }
                else
                {
                    this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
                }
            }
            else
            {
                this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
            }
        }
        else if(j == 1)
        {
            if(flag)
            {
                if(!flag1)
                {
                    this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                }
                else
                {
                    this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
                }
            }
            else
            {
                this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
            }
        }
        else if(j == 2)
        {
            if(flag)
            {
                if(!flag1)
                {
                    this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
                }
                else
                {
                    this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
                }
            }
            else
            {
                this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            }
        }
        else if(j == 3)
        {
            if(flag)
            {
                if(!flag1)
                {
                    this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
                }
                else
                {
                    this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                }
            }
            else
            {
                this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
            }
        }
    }
}