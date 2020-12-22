package fr.mod.Ura_Mod.Ura_ModCommon;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class UraStrippedLogs extends Block {

    private final String logtype;
    private final int orientation;
    private IIcon top, side;

    public UraStrippedLogs(String logtype, int orientation) {
        super(Material.wood);
        this.logtype = logtype;
        this.orientation = orientation;
        setHardness(2.0F);
        setResistance(5.0F);
        setStepSound(soundTypeWood);
        setCreativeTab(Ura_ModMain.FururModCreativeTabs);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iiconRegister) {

        this.blockIcon = iiconRegister.registerIcon(Ura_ModMain.MODID + ":stripped_" + logtype + "_log");
        this.top = iiconRegister.registerIcon(Ura_ModMain.MODID + ":stripped_" + logtype + "_log_top");
        this.side = iiconRegister.registerIcon(Ura_ModMain.MODID + ":stripped_" + logtype + "_log");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
        if(orientation == 1) {

            if (p_149691_1_ == 1) return this.top;
            if (p_149691_1_ == 0) return this.top;
            return this.side;
        }
        if(orientation == 2) {

            if (p_149691_1_ == 5) return this.top;
            if (p_149691_1_ == 4) return this.top;
            return this.side;
        }
        if(orientation == 3) {

            if (p_149691_1_ == 3) return this.top;
            if (p_149691_1_ == 2) return this.top;
            return this.side;
        }
        return this.blockIcon;
    }
}