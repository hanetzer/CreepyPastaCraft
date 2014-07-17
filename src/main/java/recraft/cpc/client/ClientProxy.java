package recraft.cpc.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderSnowball;
import recraft.cpc.client.model.*;
import recraft.cpc.client.renderer.entity.*;
import recraft.cpc.client.renderer.tileentity.TileEntityLaptopRenderer;
import recraft.cpc.common.CommonProxy;
import recraft.cpc.common.entity.monster.*;
import recraft.cpc.common.entity.passive.EntityCry;
import recraft.cpc.common.entity.passive.EntityJane;
import recraft.cpc.common.entity.passive.EntityPewds;
import recraft.cpc.common.entity.passive.EntityStrider;
import recraft.cpc.common.entity.projectile.EntityStephano;
import recraft.cpc.common.tileentity.TileEntityLaptop;
import recraft.cpc.init.CPCItems;

public class ClientProxy extends CommonProxy {
	public void init() {
		render();
	}

	public void render() {
		RenderingRegistry.registerEntityRenderingHandler(EntityJeff.class, new RenderJeff(new ModelJeff(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityJane.class, new RenderJane(new ModelJane(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityPewds.class, new RenderPewds(new ModelBiped(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityCry.class, new RenderCry(new ModelBiped(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityJack.class, new RenderJack(new ModelBiped(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityRake.class, new RenderRake(new ModelRake(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntitySmile.class, new RenderSmileDog(new ModelSmileDog(), 0.5F));
		//RenderingRegistry.registerEntityRenderingHandler(EntityPage.class, new RenderPage());
		RenderingRegistry.registerEntityRenderingHandler(EntitySeed.class, new RenderSeed(new ModelSeedEater(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityMothman.class, new RenderMoth(new ModelMothman(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityStrider.class, new RenderStrider(new ModelStrider(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityStephano.class, new RenderSnowball(CPCItems.stephano, 0));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLaptop.class, new TileEntityLaptopRenderer());
	}
	
	public int addArmor(String armor) {
		return RenderingRegistry.addNewArmourRendererPrefix(armor);
	}
}
