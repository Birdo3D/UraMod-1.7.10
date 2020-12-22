package fr.mod.Ura_Mod.iriduim_smithing_table;

import fr.mod.Ura_Mod.Ura_ModCommon.Ura_ModMain;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class IriduimSmithingTableRecipes {
    private Map experienceList = new HashMap();
    private static final IriduimSmithingTableRecipes smeltingBase = new IriduimSmithingTableRecipes(); //Permet d'instancier votre classe car vous le l'instancierez nul part ailleur
    private Map smeltingList = new HashMap(); //Ceci permet de mettre vos recettes




    public IriduimSmithingTableRecipes()
    {
        this.addRecipe(Ura_ModMain.ura_helmet,Ura_ModMain.iridium_ingot, new ItemStack(Ura_ModMain.iridium_helmet));
        this.addRecipe(Ura_ModMain.ura_chestPlate,Ura_ModMain.iridium_ingot, new ItemStack(Ura_ModMain.iridium_chestPlate));
        this.addRecipe(Ura_ModMain.ura_leggings,Ura_ModMain.iridium_ingot, new ItemStack(Ura_ModMain.iridium_leggings));
        this.addRecipe(Ura_ModMain.ura_boots,Ura_ModMain.iridium_ingot, new ItemStack(Ura_ModMain.iridium_boots));
        this.addRecipe(Ura_ModMain.ura_pickaxe,Ura_ModMain.iridium_ingot, new ItemStack(Ura_ModMain.iridium_pickaxe));
        this.addRecipe(Ura_ModMain.ura_sword,Ura_ModMain.iridium_ingot, new ItemStack(Ura_ModMain.iridium_sword));

    }


    public void addRecipe(ItemStack stack1, ItemStack stack2, ItemStack stack4) //Cette fonction de comprend que des ItemStack, c'est celle qui ajoute les recettes à la HashMap
    {
        ItemStack[] stackList = new ItemStack[]{stack1, stack2};
        this.smeltingList.put(stackList, stack4);
    }

    public void addRecipe(Item item1, Item item2, ItemStack stack) //1er cas
    {
        this.addRecipe(new ItemStack(item1), new ItemStack(item2), stack);
    }

    public void addRecipe(Block block1, Item item2, ItemStack stack) //2nd cas
    {
        this.addRecipe(Item.getItemFromBlock(block1), item2, stack);
    }

    public void addRecipe(Block block1, Block block2,  ItemStack stack) //3ème cas
    {
        this.addRecipe(Item.getItemFromBlock(block1), Item.getItemFromBlock(block2), stack);
    }


    public ItemStack getSmeltingResult(ItemStack[] stack) // En argument : un tableau avec le contenu des trois slots d'input
    {
        Iterator iterator = this.smeltingList.entrySet().iterator();
        Map.Entry entry;

        do
        {
            if (!iterator.hasNext()) // Si il n'y a plus de recettes dans la liste
            {
                return null; //Il n'y a pas de recette correspondante
            }
            entry = (Map.Entry)iterator.next(); //prend la recette suivante
        }
        while (!this.isSameKey(stack, (ItemStack[])entry.getKey())); //Check si le tableau passé en argument correspond à celui de la recette, vous avez une erreur ici, on crée la fonction tout de suite.

        return (ItemStack)entry.getValue(); //retourne l'itemstack : resultat de la recette
    }

    private boolean isSameKey(ItemStack[] stackList, ItemStack[] stackList2)
    {
        boolean isSame = false; //Au début ce n'est pas la même
        for(int i=0; i<=1; i++) // Pour les 3 items
        {
            if(stackList[i].getItem() == stackList2[i].getItem()) //On vérifie si ce sont les même
            {
                isSame = true; // Si c'est le cas alors isSame vaut true
            }
            else
            {
                return false; //Si un seul n'est pas bon, on cherche pas, c'est pas la bonne recette
            }
        }
        return isSame;
    }

    public Map getSmeltingList()
    {
        return this.smeltingList;
    }

    public static IriduimSmithingTableRecipes smelting()
    {
        return smeltingBase;
    }
}
