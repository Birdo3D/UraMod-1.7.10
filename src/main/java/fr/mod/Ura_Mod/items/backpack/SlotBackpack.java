package fr.mod.Ura_Mod.items.backpack;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;


    /**
     * @author SCAREX
     *
     */
    public class SlotBackpack extends Slot
    {
        public SlotBackpack(IInventory inv, int index, int x, int y) {
            super(inv, index, x, y);
        }

        /**
         * Method used to prevent backpack-ception (backpacks inside backpacks)
         */
        @Override
        public boolean isItemValid(ItemStack stack) {
            return !(stack.getItem() instanceof Backpack);
        }
    }

