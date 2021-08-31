package fr.uracraft.uramod.Items.Tools;

import fr.uracraft.uramod.common.UraMod;
import net.minecraft.item.Item;

public class ItemPickaxe extends net.minecraft.item.ItemPickaxe {
    public ItemPickaxe(Item.ToolMaterial material)
    {
        super(material);
        this.setCreativeTab(UraMod.uramodcreativetab);
    }
}
