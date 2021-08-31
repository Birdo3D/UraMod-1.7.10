package fr.uracraft.uramod.Items.Armors;

import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public class PatchVanillaArmors {

    public static void armors(FMLPreInitializationEvent event) {

        //Diamond Armor Reforged
        ItemArmor.ArmorMaterial customDiam = EnumHelper.addArmorMaterial("CUSTOM_DIAMOND", 5, new int[]{2, 5, 4, 1}, 10);
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.diamond_helmet, customDiam, "material", "field_77878_bZ");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.diamond_chestplate, customDiam, "material", "field_77878_bZ");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.diamond_leggings, customDiam, "material", "field_77878_bZ");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.diamond_boots, customDiam, "material", "field_77878_bZ");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.diamond_helmet, 2, "damageReduceAmount", "field_77879_b");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.diamond_chestplate, 5, "damageReduceAmount", "field_77879_b");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.diamond_leggings, 4, "damageReduceAmount", "field_77879_b");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.diamond_boots, 1, "damageReduceAmount", "field_77879_b");

        //Iron Armor Reforged
        ItemArmor.ArmorMaterial customIron = EnumHelper.addArmorMaterial("CUSTOM_IRON", 5, new int[]{2, 5, 3, 1}, 9);
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.iron_helmet, customIron, "material", "field_77878_bZ");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.iron_chestplate, customIron, "material", "field_77878_bZ");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.iron_leggings, customIron, "material", "field_77878_bZ");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.iron_boots, customIron, "material", "field_77878_bZ");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.iron_helmet, 2, "damageReduceAmount", "field_77879_b");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.iron_chestplate, 5, "damageReduceAmount", "field_77879_b");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.iron_leggings, 3, "damageReduceAmount", "field_77879_b");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.iron_boots, 1, "damageReduceAmount", "field_77879_b");

        //Gold Armor Reforged
        ItemArmor.ArmorMaterial customGold = EnumHelper.addArmorMaterial("CUSTOM_GOLD", 5, new int[]{1, 2, 1, 1}, 25);
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.golden_helmet, customGold, "material", "field_77878_bZ");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.golden_chestplate, customGold, "material", "field_77878_bZ");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.golden_leggings, customGold, "material", "field_77878_bZ");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.golden_boots, customGold, "material", "field_77878_bZ");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.golden_helmet, 1, "damageReduceAmount", "field_77879_b");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.golden_chestplate, 2, "damageReduceAmount", "field_77879_b");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.golden_leggings, 1, "damageReduceAmount", "field_77879_b");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.golden_boots, 1, "damageReduceAmount", "field_77879_b");

        //Leather Armor Reforged
        ItemArmor.ArmorMaterial customLeather = EnumHelper.addArmorMaterial("CUSTOM_LEATHER", 5, new int[]{1, 1, 1, 1}, 15);
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.leather_helmet, customLeather, "material", "field_77878_bZ");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.leather_chestplate, customLeather, "material", "field_77878_bZ");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.leather_leggings, customLeather, "material", "field_77878_bZ");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.leather_boots, customLeather, "material", "field_77878_bZ");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.leather_helmet, 1, "damageReduceAmount", "field_77879_b");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.leather_chestplate, 1, "damageReduceAmount", "field_77879_b");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.leather_leggings, 1, "damageReduceAmount", "field_77879_b");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.leather_boots, 1, "damageReduceAmount", "field_77879_b");

        //Chain Armor Reforged
        ItemArmor.ArmorMaterial customChain = EnumHelper.addArmorMaterial("CUSTOM_CHAIN", 5, new int[]{1, 2, 2, 1}, 12);
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.chainmail_helmet, customChain, "material", "field_77878_bZ");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.chainmail_chestplate, customChain, "material", "field_77878_bZ");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.chainmail_leggings, customChain, "material", "field_77878_bZ");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.chainmail_boots, customChain, "material", "field_77878_bZ");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.chainmail_helmet, 1, "damageReduceAmount", "field_77879_b");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.chainmail_chestplate, 2, "damageReduceAmount", "field_77879_b");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.chainmail_leggings, 2, "damageReduceAmount", "field_77879_b");
        ObfuscationReflectionHelper.setPrivateValue(ItemArmor.class, Items.chainmail_boots, 1, "damageReduceAmount", "field_77879_b");
    }
}
