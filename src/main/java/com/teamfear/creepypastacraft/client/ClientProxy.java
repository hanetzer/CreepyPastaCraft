package com.teamfear.creepypastacraft.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderSnowball;
import com.teamfear.creepypastacraft.client.model.*;
import com.teamfear.creepypastacraft.client.renderer.entity.*;
import com.teamfear.creepypastacraft.client.renderer.tileentity.TileEntityLaptopRenderer;
import com.teamfear.creepypastacraft.common.CommonProxy;
import com.teamfear.creepypastacraft.common.entity.monster.*;
import com.teamfear.creepypastacraft.common.entity.passive.*;
import com.teamfear.creepypastacraft.common.entity.projectile.EntityStephano;
import com.teamfear.creepypastacraft.common.tileentity.TileEntityLaptop;
import com.teamfear.creepypastacraft.init.CPCItems;

public class ClientProxy extends CommonProxy {
    Minecraft mc = Minecraft.getMinecraft();

    public void init() {
        render();
    }

    private void render() {
        RenderingRegistry.registerEntityRenderingHandler(EntityJeff.class,
                new RenderJeff(new ModelJeff(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityJane.class,
                new RenderJane(new ModelJane(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityPewds.class,
                new RenderPewds(new ModelBiped(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityCry.class,
                new RenderCry(new ModelBiped(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityJack.class,
                new RenderJack(new ModelBiped(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityRake.class,
                new RenderRake(new ModelRake(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntitySmileDog.class,
                new RenderSmileDog(new ModelSmileDog(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntitySquidward.class,
                new RenderSquidward(new ModelSquidward(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntitySeed.class,
                new RenderSeed(new ModelSeedEater(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityMothman.class,
                new RenderMoth(new ModelMothman(), 0.5F));
        RenderingRegistry.registerEntityRenderingHandler(EntityStrider.class,
                new RenderStrider(new ModelStrider(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityVampireBat.class,
				new RenderVampireBat());
        RenderingRegistry.registerEntityRenderingHandler(EntityStephano.class,
                new RenderSnowball(CPCItems.stephano, 0));
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLaptop.class,
                new TileEntityLaptopRenderer());
        mc.gameSettings.gammaSetting = -0.3F;
    }

    public int addArmor(String armor) {
        return RenderingRegistry.addNewArmourRendererPrefix(armor);
    }
}
