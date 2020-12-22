package fr.mod.Ura_Mod.Ura_ModCommon;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockWall;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class UraWall extends BlockWall
{
    public Block block;
    public UraWall(Block block)
    {
        super(block);
        this.block = block;
    }

    public IIcon getIcon(int side, int meta)
    {
        return block.getBlockTextureFromSide(side);
    }

    public void getSubBlocks(Item item, CreativeTabs tab, List list)
    {
        list.add(new ItemStack(item, 1, 0));
    }

}