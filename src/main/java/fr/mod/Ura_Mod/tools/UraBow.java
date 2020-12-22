package fr.mod.Ura_Mod.tools;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class UraBow extends ItemBow {
    public static final String[] bowPullIconNameArray = new String[] {"1", "2", "3"}; // c'est pour les textures
    @SideOnly(Side.CLIENT)
    private IIcon[] iconArray;

    /*
     *Le constructeur de l'arc
     */
    public UraBow( )
    {

        this.maxStackSize = 1;
        this.setMaxDamage(2500);
        this.setCreativeTab(CreativeTabs.tabCombat);
    }

    /*
     * appelé lorsque le joueur relâche l'item, c'est aussi ici ou son géré les différent enchantement comme l'infinité, c'est la aussi ou l'on vas ajouté des amélioration pour notre arc
     */
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int par4)
    {
        int j = this.getMaxItemUseDuration(stack) - par4;

        ArrowLooseEvent event = new ArrowLooseEvent(player, stack, j);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled())
        {
            return;
        }
        j = event.charge;

        boolean flag = player.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, stack) > 0;

        if (flag || player.inventory.hasItem(Items.arrow))
        {
            float f = (float)j / 20.0F;
            f = (f * f + f * 2.0F) / 3.0F;

            if ((double)f < 0.1D)
            {
                return;
            }

            if (f > 1.0F)
            {
                f = 1.0F;
            }

            EntityArrow entityarrow = new EntityArrow(world, player, f * 2.0F);

            if (f == 1.0F)
            {
                entityarrow.setIsCritical(true);
            }

            int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, stack);

            if (k > 0)
            {
                entityarrow.setDamage(entityarrow.getDamage() + (double)k * 0.5D + 0.5D);
            }

            int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, stack);

            if (l > 0)
            {
                entityarrow.setKnockbackStrength(l);
            }

            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, stack) > 0)
            {
                entityarrow.setFire(100);
            }

            stack.damageItem(1, player);
            world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

            if (flag)
            {
                entityarrow.canBePickedUp = 2;
            }
            else
            {
                player.inventory.consumeInventoryItem(Items.arrow);
            }

            if (!world.isRemote)
            {
                world.spawnEntityInWorld(entityarrow);
            }
        }
    }

    /*
     * Combien de temps il faut pour utiliser l'item
     */
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 70000;
    }

    /*
     *renvoie l'action qui spécifie ce que l'animation à jouer lorsque l'item et utilisé
     */
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.bow;
    }

    /*
     * Appelé à chaque fois que cet objet est équipé et le bouton droit de la souris est enfoncé
     */
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        ArrowNockEvent event = new ArrowNockEvent(player, stack);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled())
        {
            return event.result;
        }

        if (player.capabilities.isCreativeMode || player.inventory.hasItem(Items.arrow))
        {
            player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
        }

        return stack;
    }
    /*
{1}
   * retourne le niveaux d'enchanbilité de l'arc.
   */
    public int getItemEnchantability()
    {
        return 1;
    }

    @SideOnly(Side.CLIENT)   //vus que le render doit étre sur le client
    public void registerIcons(IIconRegister IconRegister)
    {
        this.itemIcon = IconRegister.registerIcon(this.getIconString());
        this.iconArray = new IIcon[bowPullIconNameArray.length];

        for (int i = 0; i < this.iconArray.length; ++i)
        {
            this.iconArray[i] = IconRegister.registerIcon(this.getIconString() + "_" + bowPullIconNameArray[i]);
        }
    }

    /*
     * utilisé pour faire défiler les textures en fonction de leur durée utilisée on utilise le tableaux
     */
    @Override
    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
    {
        if(usingItem != null && usingItem.getItem().equals(this))
        {
            int k = usingItem.getMaxItemUseDuration() - useRemaining;
            if(k >= 18)
                return iconArray[2];
            if(k > 13)
                return iconArray[1];
            if(k > 0)
                return iconArray[0];
        }
        return getIconIndex(stack);
    }

}
