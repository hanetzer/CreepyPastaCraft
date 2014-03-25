package recraft.cpc.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.settings.EnumOptions;
import net.minecraftforge.common.MinecraftForge;
import recraft.cpc.common.CommonProxy;
import recraft.cpc.client.model.ModelJane;
import recraft.cpc.client.model.ModelJeff;
import recraft.cpc.client.model.ModelMothman;
import recraft.cpc.client.model.ModelRake;
import recraft.cpc.client.model.ModelSeedEater;
import recraft.cpc.client.model.ModelSlender;
import recraft.cpc.client.model.ModelSmileDog;
import recraft.cpc.client.model.ModelStrider;
import recraft.cpc.client.renderer.entity.RenderCry;
import recraft.cpc.client.renderer.entity.RenderJack;
import recraft.cpc.client.renderer.entity.RenderJane;
import recraft.cpc.client.renderer.entity.RenderJeff;
import recraft.cpc.client.renderer.entity.RenderMoth;
import recraft.cpc.client.renderer.entity.RenderPewds;
import recraft.cpc.client.renderer.entity.RenderRake;
import recraft.cpc.client.renderer.entity.RenderSeed;
import recraft.cpc.client.renderer.entity.RenderSlender;
import recraft.cpc.client.renderer.entity.RenderSmileDog;
import recraft.cpc.client.renderer.entity.RenderStrider;
import recraft.cpc.client.renderer.tileentity.TileEntityLaptopRenderer;
import recraft.cpc.common.entity.monster.EntityJack;
import recraft.cpc.common.entity.monster.EntityJeff;
import recraft.cpc.common.entity.monster.EntityMothman;
import recraft.cpc.common.entity.monster.EntityRake;
import recraft.cpc.common.entity.monster.EntitySeed;
import recraft.cpc.common.entity.monster.EntitySlender;
import recraft.cpc.common.entity.monster.EntitySmile;
import recraft.cpc.common.entity.passive.EntityCry;
import recraft.cpc.common.entity.passive.EntityJane;
import recraft.cpc.common.entity.passive.EntityPewds;
import recraft.cpc.common.entity.passive.EntityStrider;
import recraft.cpc.common.entity.projectile.EntityStephano;
import recraft.cpc.common.handlers.SoundHandler;
import recraft.cpc.common.item.CPCItem;
import recraft.cpc.common.tileentity.TileEntityLaptop;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
	Minecraft mc = Minecraft.getMinecraft();

	public ClientProxy()
	{
		super();
	}
	
	public void init()
	{
		render();
		sound();
	}

	public void render()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityJeff.class, new RenderJeff(new ModelJeff(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityJane.class, new RenderJane(new ModelJane(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityPewds.class, new RenderPewds(new ModelBiped(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityCry.class, new RenderCry(new ModelBiped(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityJack.class, new RenderJack(new ModelBiped(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityRake.class, new RenderRake(new ModelRake(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntitySmile.class, new RenderSmileDog(new ModelSmileDog(), 0.5F));
		//RenderingRegistry.registerEntityRenderingHandler(EntityPage.class, new RenderPage());
		RenderingRegistry.registerEntityRenderingHandler(EntitySlender.class, new RenderSlender(new ModelSlender(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntitySeed.class, new RenderSeed(new ModelSeedEater(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityMothman.class, new RenderMoth(new ModelMothman(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityStrider.class, new RenderStrider(new ModelStrider(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityStephano.class, new RenderSnowball(CPCItem.stephano, 0));
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityLaptop.class, new TileEntityLaptopRenderer());
	}
	
	public void sound()
	{
		MinecraftForge.EVENT_BUS.register(new SoundHandler());;
	}

	public int addArmor(String armor)
	{
		return RenderingRegistry.addNewArmourRendererPrefix(armor);
	}

}
