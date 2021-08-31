package fr.uracraft.uramod.Items;

import fr.uracraft.uramod.common.UraBlocks;
import fr.uracraft.uramod.common.UraMod;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;

public class FakeWaterBucket extends ItemBucket {

    public FakeWaterBucket() {
        super(UraBlocks.fake_water_block);
        this.setUnlocalizedName("fake_water_bucket");
        this.setCreativeTab(UraMod.uramodcreativetab);
        this.setContainerItem(Items.bucket);
        this.setTextureName(UraMod.MODID + ":fake_water_bucket");
    }
}
