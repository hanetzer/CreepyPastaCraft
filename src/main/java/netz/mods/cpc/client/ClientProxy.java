package netz.mods.cpc.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.settings.EnumOptions;
import net.minecraft.client.settings.GameSettings;
import net.minecraftforge.common.MinecraftForge;
import netz.mods.cpc.CommonProxy;
import netz.mods.cpc.client.model.ModelJane;
import netz.mods.cpc.client.model.ModelJeff;
import netz.mods.cpc.client.model.ModelMothman;
import netz.mods.cpc.client.model.ModelRake;
import netz.mods.cpc.client.model.ModelSeedEater;
import netz.mods.cpc.client.model.ModelSlender;
import netz.mods.cpc.client.model.ModelSmileDog;
import netz.mods.cpc.client.model.ModelStrider;
import netz.mods.cpc.client.renderer.entity.RenderCry;
import netz.mods.cpc.client.renderer.entity.RenderJack;
import netz.mods.cpc.client.renderer.entity.RenderJane;
import netz.mods.cpc.client.renderer.entity.RenderJeff;
import netz.mods.cpc.client.renderer.entity.RenderMoth;
import netz.mods.cpc.client.renderer.entity.RenderPewds;
import netz.mods.cpc.client.renderer.entity.RenderRake;
import netz.mods.cpc.client.renderer.entity.RenderSeed;
import netz.mods.cpc.client.renderer.entity.RenderSlender;
import netz.mods.cpc.client.renderer.entity.RenderSmileDog;
import netz.mods.cpc.client.renderer.entity.RenderStrider;
import netz.mods.cpc.client.renderer.tileentity.TileEntityLaptopRenderer;
import netz.mods.cpc.entity.monster.EntityJack;
import netz.mods.cpc.entity.monster.EntityJeff;
import netz.mods.cpc.entity.monster.EntityMothman;
import netz.mods.cpc.entity.monster.EntityRake;
import netz.mods.cpc.entity.monster.EntitySeed;
import netz.mods.cpc.entity.monster.EntitySlender;
import netz.mods.cpc.entity.monster.EntitySmile;
import netz.mods.cpc.entity.passive.EntityCry;
import netz.mods.cpc.entity.passive.EntityJane;
import netz.mods.cpc.entity.passive.EntityPewds;
import netz.mods.cpc.entity.passive.EntityStrider;
import netz.mods.cpc.entity.projectile.EntityStephano;
import netz.mods.cpc.handlers.SoundHandler;
import netz.mods.cpc.item.CPCItem;
import netz.mods.cpc.tileentity.TileEntityLaptop;
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
