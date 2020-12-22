package fr.mod.Ura_Mod.Ura_ModCommon;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishFood;
import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class UraFurnaceRecipe {
    private Map experienceList = new HashMap();
    private static final UraFurnaceRecipe smeltingBase = new UraFurnaceRecipe(); //Permet d'instancier votre classe car vous le l'instancierez nul part ailleur
    private Map smeltingList = new HashMap(); //Ceci permet de mettre vos recettes




    public UraFurnaceRecipe()
    {
        this.addRecipe(Ura_ModMain.ura_carrot, new ItemStack(Ura_ModMain.UraIngot), 1.0F);
        this.addRecipe(Ura_ModMain.iridium_potato, new ItemStack(Ura_ModMain.iridium_nugget), 1.0F);
        this.addRecipe(Blocks.iron_ore, new ItemStack(Items.iron_ingot), 0.7F);
        this.addRecipe(Blocks.gold_ore, new ItemStack(Items.gold_ingot), 1.0F);
        this.addRecipe(Blocks.diamond_ore, new ItemStack(Items.diamond), 1.0F);
        this.addRecipe(Blocks.sand, new ItemStack(Blocks.glass), 0.1F);
        this.addRecipe(Items.porkchop, new ItemStack(Items.cooked_porkchop), 0.35F);
        this.addRecipe(Items.beef, new ItemStack(Items.cooked_beef), 0.35F);
        this.addRecipe(Items.chicken, new ItemStack(Items.cooked_chicken), 0.35F);
        this.addRecipe(Blocks.cobblestone, new ItemStack(Blocks.stone), 0.1F);
        this.addRecipe(Items.clay_ball, new ItemStack(Items.brick), 0.3F);
        this.addRecipe(Blocks.clay, new ItemStack(Blocks.hardened_clay), 0.35F);
        this.addRecipe(Blocks.cactus, new ItemStack(Items.dye, 1, 2), 0.2F);
        this.addRecipe(Blocks.log, new ItemStack(Items.coal, 1, 1), 0.15F);
        this.addRecipe(Blocks.log2, new ItemStack(Items.coal, 1, 1), 0.15F);
        this.addRecipe(Blocks.emerald_ore, new ItemStack(Items.emerald), 1.0F);
        this.addRecipe(Items.potato, new ItemStack(Items.baked_potato), 0.35F);
        this.addRecipe(Blocks.netherrack, new ItemStack(Items.netherbrick), 0.1F);
        this.addRecipe(Blocks.coal_ore, new ItemStack(Items.coal), 0.1F);
        this.addRecipe(Blocks.redstone_ore, new ItemStack(Items.redstone), 0.7F);
        this.addRecipe(Blocks.lapis_ore, new ItemStack(Items.dye, 1, 4), 0.2F);
        this.addRecipe(Blocks.quartz_ore, new ItemStack(Items.quartz), 0.2F);
        this.addRecipe(Ura_ModMain.UraOre,new ItemStack(Ura_ModMain.UraIngot,1),1.0f);
        this.addRecipe(Ura_ModMain.ArgentOre,new ItemStack(Ura_ModMain.ArgentIngot,1),1.0f);
        this.addRecipe(Ura_ModMain.NeodymeOre,new ItemStack(Ura_ModMain.NeodymeIngot,1),1.0f);
        this.addRecipe(Ura_ModMain.halloween_ore,new ItemStack(Ura_ModMain.halloween_ingot,1),1.0f);
        this.addRecipe(Ura_ModMain.ancient_debris,new ItemStack(Ura_ModMain.netherite_scrap,1),1.0f);
        this.addRecipe(Ura_ModMain.chorus,new ItemStack(Ura_ModMain.chorus_eclate,1),1.0f);
        ItemFishFood.FishType[] afishtype = ItemFishFood.FishType.values();
        int i = afishtype.length;

        for (int j = 0; j < i; ++j)
        {
            ItemFishFood.FishType fishtype = afishtype[j];

            if (fishtype.func_150973_i())
            {
                this.addRecipe(new ItemStack(Items.fish, 1, fishtype.func_150976_a()), new ItemStack(Items.cooked_fished, 1, fishtype.func_150976_a()), 0.35F);
            }
        }
    }

    public void addRecipe(ItemStack stack2, ItemStack stack4,float f) //Cette fonction de comprend que des ItemStack, c'est celle qui ajoute les recettes à la HashMap
    {
        ItemStack[] stackList = new ItemStack[]{stack2};
        this.smeltingList.put(stackList, stack4);
    }

    public void addRecipe( Item item2, ItemStack stack,float f) //1er cas
    {
        this.addRecipe( new ItemStack(item2), stack,f);
    }


    public void addRecipe( Block block2, ItemStack stack,float f) //3ème cas
    {
        this.addRecipe( Item.getItemFromBlock(block2), stack, f);
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
            entry = (Map.Entry) iterator.next(); //prend la recette suivante
        }
        while (!this.isSameKey(stack, (ItemStack[])entry.getKey())); //Check si le tableau passé en argument correspond à celui de la recette, vous avez une erreur ici, on crée la fonction tout de suite.

        return (ItemStack)entry.getValue(); //retourne l'itemstack : resultat de la recette
    }

    private boolean isSameKey(ItemStack[] stackList, ItemStack[] stackList2)
    {
        boolean isSame = false; //Au début ce n'est pas la même

            if(stackList[0].getItem() == stackList2[0].getItem()) //On vérifie si ce sont les même
            {
                isSame = true; // Si c'est le cas alors isSame vaut true
            }
            else
            {
                return false; //Si un seul n'est pas bon, on cherche pas, c'est pas la bonne recette
            }

        return isSame;
    }

    public Map getSmeltingList()
    {
        return this.smeltingList;
    }

    public static UraFurnaceRecipe smelting()
    {
        return smeltingBase;
    }
}
