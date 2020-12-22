package fr.mod.Ura_Mod.Ura_ModCommon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.mod.Ura_Mod.client.model.ModelElytra;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.EnumHelper;

public class Elytra extends ItemArmor {

    @SideOnly(Side.CLIENT)
    private IIcon broken;

    public Elytra() {
        super(EnumHelper.addArmorMaterial("elytra", 27, new int[] { 0, 0, 0, 0 }, 0), 0, 1);
        this.setMaxDamage(432);
        this.setMaxStackSize(1);
        this.setTextureName(Ura_ModMain.MODID + ":elytres");
        this.setUnlocalizedName("elytres");
        this.setCreativeTab(Ura_ModMain.FururModCreativeTabs);
    }

    @Override
    public boolean getIsRepairable(ItemStack stack, ItemStack material) {
        return ArmorMaterial.CLOTH.func_151685_b() == material.getItem();
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
        return Ura_ModMain.MODID + ":textures/entity/elytra.png";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
        return new ModelElytra();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) {
        return meta >= getMaxDamage() ? broken : super.getIconFromDamage(meta);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister reg) {
        super.registerIcons(reg);
        broken = reg.registerIcon(Ura_ModMain.MODID + ":broken_elytra");
    }
}
