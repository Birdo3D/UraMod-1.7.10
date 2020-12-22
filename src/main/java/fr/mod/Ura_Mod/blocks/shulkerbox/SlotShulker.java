package fr.mod.Ura_Mod.blocks.shulkerbox;

import fr.mod.Ura_Mod.Ura_ModCommon.Ura_ModMain;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotShulker extends Slot
{

    public SlotShulker(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_)
    {
        super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
        // TODO Auto-generated constructor stub
    }

    /**
     * Method used to prevent backpack-ception (backpacks inside backpacks)
     */
    @Override
    public boolean isItemValid(ItemStack stack)
    {
        return stack.getItem() != Item.getItemFromBlock(Ura_ModMain.shulker_box);
    }

}
