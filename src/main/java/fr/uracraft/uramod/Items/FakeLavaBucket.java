package fr.uracraft.uramod.Items;

import fr.uracraft.uramod.common.UraBlocks;
import fr.uracraft.uramod.common.UraMod;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;

public class FakeLavaBucket extends ItemBucket {

    public FakeLavaBucket() {
        super(UraBlocks.fake_lava_block);
        this.setUnlocalizedName("fake_lava_bucket");
        this.setCreativeTab(UraMod.uramodcreativetab);
        this.setContainerItem(Items.bucket);
        this.setTextureName(UraMod.MODID + ":fake_lava_bucket");
    }
}
