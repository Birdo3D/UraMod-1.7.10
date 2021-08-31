package fr.uracraft.uramod.Items.Tools;

import fr.uracraft.uramod.common.UraMod;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;

public class ItemShovel extends ItemSpade {
    public ItemShovel(Item.ToolMaterial material)
    {
        super(material);
        this.setCreativeTab(UraMod.uramodcreativetab);
    }
}
