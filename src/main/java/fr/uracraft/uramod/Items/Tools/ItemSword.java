package fr.uracraft.uramod.Items.Tools;

import fr.uracraft.uramod.common.UraMod;
import net.minecraft.item.Item;

public class ItemSword extends net.minecraft.item.ItemSword {
    public ItemSword(Item.ToolMaterial material)
    {
        super(material);
        this.setCreativeTab(UraMod.uramodcreativetab);
    }
}
