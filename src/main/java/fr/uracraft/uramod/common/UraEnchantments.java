package fr.uracraft.uramod.common;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.uracraft.uramod.Enchantments.EnchantmentCobbleBreaker;
import fr.uracraft.uramod.Enchantments.EnchantmentTelekinesis;
import net.minecraft.enchantment.Enchantment;

public class UraEnchantments {

    public static Enchantment enchantmentTelekinesis;
    public static Enchantment enchantmentCobbleBreaker;

    public static void enchantments(FMLPreInitializationEvent event) {
        enchantmentTelekinesis = new EnchantmentTelekinesis();
        enchantmentCobbleBreaker = new EnchantmentCobbleBreaker();
    }
}