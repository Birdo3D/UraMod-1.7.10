package fr.uracraft.uramod.Items.Tools;

import fr.uracraft.uramod.common.UraMod;
import net.minecraft.item.Item;

public class ItemHoe extends net.minecraft.item.ItemHoe {
    public ItemHoe(Item.ToolMaterial material)
    {
        super(material);
        this.setCreativeTab(UraMod.uramodcreativetab);
    }
}
