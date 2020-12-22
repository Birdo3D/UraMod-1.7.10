package fr.mod.Ura_Mod.client;

import fr.mod.Ura_Mod.Ura_ModCommon.EntityMobHalloween;
import fr.mod.Ura_Mod.Ura_ModCommon.Ura_ModMain;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderMobHalloween extends RenderBiped
{
    public final ResourceLocation texture = new ResourceLocation(Ura_ModMain.MODID, "textures/entity/mobHalloween.png");
    public RenderMobHalloween(ModelBiped model, float shadow)
    {
        super(model, shadow);
    }
    protected ResourceLocation getEntityTexture(EntityLiving living)
    {
        return this.getMobTutorielTexture((EntityMobHalloween) living);
    }

    private ResourceLocation getMobTutorielTexture(EntityMobHalloween mobHallowen)
    {
        return texture;
    }
}