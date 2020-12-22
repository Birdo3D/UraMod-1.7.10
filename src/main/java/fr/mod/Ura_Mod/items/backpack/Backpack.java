package fr.mod.Ura_Mod.items.backpack;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.mod.Ura_Mod.Ura_ModCommon.Ura_ModMain;
import fr.mod.Ura_Mod.client.model.ModelBackpack;
import fr.mod.Ura_Mod.client.model.ModelElytra;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;


public class Backpack extends ItemArmor {

    public Backpack() {
        super(EnumHelper.addArmorMaterial("backpack", 27, new int[] { 0, 0, 0, 0 }, 0), 0, 1);
        this.setMaxDamage(432);
        this.setMaxStackSize(1);
        this.setUnlocalizedName("backpack");
        this.setTextureName(Ura_ModMain.MODID + ":backpack");
        this.setCreativeTab(Ura_ModMain.UraModCreativeTabs);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
        return new ModelBackpack();
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return Ura_ModMain.MODID + ":textures/entity/backpack.png";
    }
}
