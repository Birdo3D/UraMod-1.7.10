package fr.uracraft.uramod.Utils;

import fr.uracraft.uramod.common.UraMod;
import net.minecraft.util.DamageSource;

public class CustomDamageSources extends DamageSource {

    public static CustomDamageSources fakeWater = (CustomDamageSources) new CustomDamageSources(UraMod.MODID + ".fakeWater").setDamageBypassesArmor();

    public CustomDamageSources(String customType) {
        super(customType);
    }
}