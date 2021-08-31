package fr.uracraft.uramod.Enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class EnchantmentCobbleBreaker extends Enchantment {

    public static int id = 121;
    public EnchantmentCobbleBreaker() {

        super(121, 0, EnumEnchantmentType.digger);
        this.setName("cobblebreaker");
    }

    public int getMaxLevel() {
        return 3;
    }
}