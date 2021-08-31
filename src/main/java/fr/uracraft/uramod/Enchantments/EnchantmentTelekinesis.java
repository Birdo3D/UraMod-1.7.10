package fr.uracraft.uramod.Enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class EnchantmentTelekinesis extends Enchantment {
    public static int id = 120;
    public EnchantmentTelekinesis() {

        super(120, 0, EnumEnchantmentType.digger);
        this.setName("telekinesis");
    }
}