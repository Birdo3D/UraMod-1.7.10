package fr.uracraft.uramod.Items;

import fr.uracraft.uramod.common.UraBlocks;
import fr.uracraft.uramod.common.UraMod;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;

public class GlowstoneLiquideBucket extends ItemBucket {

    public GlowstoneLiquideBucket() {
        super(UraBlocks.glowstone_liquide_block);
        this.setUnlocalizedName("glowstone_liquide_bucket");
        this.setCreativeTab(UraMod.uramodcreativetab);
        this.setContainerItem(Items.bucket);
        this.setTextureName(UraMod.MODID + ":glowstone_liquide_bucket");
    }
}
