package fr.mod.Ura_Mod.Ura_ModCommon;

import net.minecraft.util.DamageSource;

public class CustomDamageSources extends DamageSource{

    public static CustomDamageSources fakeWater = (CustomDamageSources) new CustomDamageSources("uramod.fakeWater").setDamageBypassesArmor();

    public CustomDamageSources(String customType) {
        super(customType);
    }
}
