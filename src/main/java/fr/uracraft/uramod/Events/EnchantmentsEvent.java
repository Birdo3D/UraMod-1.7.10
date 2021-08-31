package fr.uracraft.uramod.Events;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.uracraft.uramod.Enchantments.EnchantmentCobbleBreaker;
import fr.uracraft.uramod.Enchantments.EnchantmentTelekinesis;
import fr.uracraft.uramod.Utils.Random;
import fr.uracraft.uramod.common.UraItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
public class EnchantmentsEvent {


    public boolean hasEnchantment(ItemStack stack, int id) {
        if (stack.getEnchantmentTagList() != null) {
            for (int i = 0; i < stack.getEnchantmentTagList().tagCount(); i++) {
                if (stack.getEnchantmentTagList().getCompoundTagAt(i).getShort("id") == id) {
                    return true;
                }
            }
        }
        return false;
    }

    @SubscribeEvent
    public void onHarvestDrops(HarvestDropsEvent e) {
        if ((e.harvester instanceof EntityPlayer) && (e.harvester.getHeldItem() != null) && (e.drops != null)) {

            EntityPlayer player = e.harvester;
            ItemStack held_item = player.getHeldItem();
            InventoryPlayer inventory = player.inventory;
            if (hasEnchantment(held_item, EnchantmentCobbleBreaker.id) ) {
                if (e.block == Blocks.stone) {

                    int lvl = EnchantmentHelper.getEnchantmentLevel(EnchantmentCobbleBreaker.id, held_item);
                    e.drops.clear();
                    if (new Random().roll(5) == 1)
                        e.drops.add(new ItemStack(UraItems.silver_particle));
                    if (new Random().roll(10) == 1 && lvl >= 2)
                        e.drops.add(new ItemStack(UraItems.ura_particle));
                    if (new Random().roll(15) == 1 && lvl == 3)
                        e.drops.add(new ItemStack(UraItems.neodymium_particle));

                } else {
                    for (int i = 0; i < e.drops.size(); i++) {
                        if (inventory.addItemStackToInventory(e.drops.get(i))) {
                            e.drops.clear();
                        }
                    }
                }
            }

            if(hasEnchantment(held_item, EnchantmentTelekinesis.id)){
                for (int i = 0; i < e.drops.size(); i++) {
                    if (inventory.addItemStackToInventory(e.drops.get(i))) {
                        e.drops.clear();
                    }
                }
            }
        }
    }
}
