package fr.mod.Ura_Mod.Ura_ModCommon;

import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class IronTrapdoor extends BlockTrapDoor {

    public IronTrapdoor() {
        super(Material.iron);
        setHardness(5.0F);
        setStepSound(soundTypeMetal);
        setBlockTextureName(Ura_ModMain.MODID + ":iron_trapdoor");
        setBlockName("iron_trapdoor");
        setCreativeTab(Ura_ModMain.FururModCreativeTabs);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        return false;
    }
}