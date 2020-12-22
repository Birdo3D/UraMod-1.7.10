package fr.mod.Ura_Mod.armor;

import fr.mod.Ura_Mod.Ura_ModCommon.Ura_ModMain;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemZombieArmor extends ItemArmor {
    public ItemZombieArmor(ArmorMaterial material, int type)
    {
        super(material, 0, type);
    }
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
        return Ura_ModMain.MODID + ":textures/models/armor/zombie_armor_layer.png";
    }
}
