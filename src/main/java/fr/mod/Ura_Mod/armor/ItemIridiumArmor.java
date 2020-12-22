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

public class ItemIridiumArmor extends ItemArmor {
    public ItemIridiumArmor(ArmorMaterial material, int type)
    {
        super(material, 0, type);
    }
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
        if(slot == 2)
        {
            return Ura_ModMain.MODID + ":textures/models/armor/iridium_armor_layer_2.png";
        }
        return Ura_ModMain.MODID + ":textures/models/armor/iridium_armor_layer_1.png";
    }
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {


        if (this.armorType == 0) {
            player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 220, 1));
        }

        if (this.armorType == 1) {
            player.addPotionEffect(new PotionEffect(Potion.damageBoost.id, 220, 0));
        }

        if (this.armorType == 2) {
            player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 220, 0));
        }

        if (this.armorType == 3) {
            player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 220, 0));
        }
    }
}
