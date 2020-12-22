package fr.mod.Ura_Mod.Ura_ModCommon;

import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import java.util.Calendar;

public class EntityMobHalloween extends EntityMob
{
    public EntityMobHalloween(World world)
    {
        super(world);
        this.experienceValue = 0;

        double p = Math.floor(Math.random() * Math.floor(3));
        int v = (int)p;
        double p1 = Math.floor(Math.random() * Math.floor(4));
        int v1 = (int)p1;
        if (v == 0){
            this.setCurrentItemOrArmor(0, new ItemStack(Ura_ModMain.halloween_sword));
        }else if(v == 1){
            if(v1 == 0 || v1 == 4 || v1 == 5 || v1 == 6 || v1 == 7){
                this.setCurrentItemOrArmor(1, new ItemStack(Ura_ModMain.halloween_helmet));
            }else if(v1 == 1 || v1 == 4 || v1 == 8|| v1 == 9 || v1 == 7){
                this.setCurrentItemOrArmor(2, new ItemStack(Ura_ModMain.halloween_chestplate));
            }else if(v1 == 2 || v1 == 5 || v1 == 8|| v1 == 10 || v1 == 7){
                this.setCurrentItemOrArmor(3, new ItemStack(Ura_ModMain.halloween_leggings));
            }else if(v1 == 3 || v1 == 6 || v1 == 9|| v1 == 10 || v1 == 7){
                this.setCurrentItemOrArmor(4, new ItemStack(Ura_ModMain.halloween_boots));
            }
        }

        this.equipmentDropChances[0] = 0.0F;
        this.equipmentDropChances[1] = 0.0F;
        this.equipmentDropChances[2] = 0.0F;
        this.equipmentDropChances[3] = 0.0F;
        this.equipmentDropChances[4] = 0.0F;
    }

    public void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(6D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8D);
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 2;
    }

    public boolean getCanSpawnHere()
    {
        return this.worldObj.difficultySetting != EnumDifficulty.PEACEFUL;
    }

    public void onUpdate()
    {
        super.onUpdate();

        if (!this.worldObj.isRemote && this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL)
        {
            this.setDead();
        }
    }

    public Item getDropItem()
    {
        return Ura_ModMain.candy;
    }

    protected String getHurtSound()
    {
        return "mob.zombie.hurt";
    }

    protected String getDeathSound()
    {
        return "mob.zombie.death";
    }

    protected void dropRareDrop(int p_70600_1_)
    {
        this.dropItem(Ura_ModMain.halloween_ingot, 1);
    }
}