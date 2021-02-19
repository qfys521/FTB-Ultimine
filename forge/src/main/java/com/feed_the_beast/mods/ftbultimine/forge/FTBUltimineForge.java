package com.feed_the_beast.mods.ftbultimine.forge;

import com.feed_the_beast.mods.ftbultimine.FTBUltimine;
import com.feed_the_beast.mods.ftbultimine.config.FTBUltimineConfig;
import com.feed_the_beast.mods.ftbultimine.event.LevelRenderLastEvent;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;

@Mod(FTBUltimine.MOD_ID)
public class FTBUltimineForge
{

	public FTBUltimineForge()
	{
		new FTBUltimine();

		MinecraftForge.EVENT_BUS.<RenderWorldLastEvent>addListener(event -> {
			LevelRenderLastEvent.EVENT.invoker().onRenderLast(event.getMatrixStack());
		});

		DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> FTBUltimineForgeConfig::registerConfigScreen);
	}

	private static class FTBUltimineForgeConfig
	{
		static void registerConfigScreen()
		{
			ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () -> (client, parent) -> {
				return AutoConfig.getConfigScreen(FTBUltimineConfig.class, parent).get();
			});
		}
	}

}