package fr.mod.Ura_Mod.Ura_ModCommon;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBucket;

public class ItemBucketFakeWater extends ItemBucket
{
    public ItemBucketFakeWater(Block fluid)
    {
        super(fluid);
        this.setCreativeTab(Ura_ModMain.UraModCreativeTabs);
    }
}