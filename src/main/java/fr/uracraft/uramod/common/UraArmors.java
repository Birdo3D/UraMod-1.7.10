package fr.uracraft.uramod.common;

import net.minecraft.item.ItemArmor;
import net.minecraftforge.common.util.EnumHelper;

public class UraArmors {

    public static ItemArmor.ArmorMaterial ura_armor = EnumHelper.addArmorMaterial("ura_armor", 45, new int[]{3, 8, 6, 3}, 10);
    public static ItemArmor.ArmorMaterial silver_armor = EnumHelper.addArmorMaterial("silver_armor", 30, new int[]{2, 6, 5, 2}, 9);
    public static ItemArmor.ArmorMaterial iridium_armor = EnumHelper.addArmorMaterial("iridium_armor", 60, new int[] {3, 8, 6, 3}, 25);
    public static ItemArmor.ArmorMaterial travel_armor = EnumHelper.addArmorMaterial("travel_armor", 45, new int[] {1, 3, 2, 1}, 15);
}
