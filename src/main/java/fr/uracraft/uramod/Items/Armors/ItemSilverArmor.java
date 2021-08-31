package fr.uracraft.uramod.Items.Armors;

import fr.uracraft.uramod.common.UraMod;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemSilverArmor extends ItemArmor {

    public ItemSilverArmor(ArmorMaterial material, int type)
    {
        super(material, 0, type);
        this.setCreativeTab(UraMod.uramodcreativetab);
    }
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
        if(slot == 2)
        {
            return UraMod.MODID + ":textures/models/armor/silver_armor_layer_2.png";
        }
        return UraMod.MODID + ":textures/models/armor/silver_armor_layer_1.png";
    }
}
