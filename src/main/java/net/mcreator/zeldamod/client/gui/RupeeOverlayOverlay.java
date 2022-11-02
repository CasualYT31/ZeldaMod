
package net.mcreator.zeldamod.client.gui;

import java.util.Date;

import org.checkerframework.checker.units.qual.h;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.Minecraft;

import net.mcreator.zeldamod.network.ZeldaModModVariables;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.platform.GlStateManager;

@Mod.EventBusSubscriber({Dist.CLIENT})
public class RupeeOverlayOverlay {
	/**
	 * Keeps track of the current entity's (i.e. the player's) rupee count.
	 */
	private static double _oldRupeeCount = 0.0;

	/**
	 * Timer used to display the overlay temporarily when not in the inventory screen.
	 */
	private static long _timer = 0;

	/**
	 * The deadline for the timer.
	 */
	private static long _deadline = 0;

	private static void displayOverlay(ScreenEvent.DrawScreenEvent.Post event) {
		RenderSystem.disableDepthTest();
		RenderSystem.depthMask(false);
		RenderSystem.enableBlend();
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
				GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		RenderSystem.setShaderColor(1, 1, 1, 1);
		
		Minecraft.getInstance().font.draw(event.getPoseStack(),
				"" + String.format("%.0f", ((Minecraft.getInstance().player.getCapability(ZeldaModModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new ZeldaModModVariables.PlayerVariables())).rupee_count)) + "",
				20, 10, -3355444);
		RenderSystem.setShaderTexture(0, new ResourceLocation("zelda_mod:textures/screens/rupee.png"));
		Minecraft.getInstance().gui.blit(event.getPoseStack(), 5, 5, 0, 0, 16, 16, 16, 16);

		RenderSystem.depthMask(true);
		RenderSystem.defaultBlendFunc();
		RenderSystem.enableDepthTest();
		RenderSystem.disableBlend();
		RenderSystem.setShaderColor(1, 1, 1, 1);
	}

	private static void displayOverlay(RenderGameOverlayEvent.Pre event) {
		RenderSystem.disableDepthTest();
		RenderSystem.depthMask(false);
		RenderSystem.enableBlend();
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
				GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		RenderSystem.setShaderColor(1, 1, 1, 1);
		
		Minecraft.getInstance().font.draw(event.getMatrixStack(),
				"" + String.format("%.0f", ((Minecraft.getInstance().player.getCapability(ZeldaModModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new ZeldaModModVariables.PlayerVariables())).rupee_count)) + "",
				20, 10, -3355444);
		RenderSystem.setShaderTexture(0, new ResourceLocation("zelda_mod:textures/screens/rupee.png"));
		Minecraft.getInstance().gui.blit(event.getMatrixStack(), 5, 5, 0, 0, 16, 16, 16, 16);

		RenderSystem.depthMask(true);
		RenderSystem.defaultBlendFunc();
		RenderSystem.enableDepthTest();
		RenderSystem.disableBlend();
		RenderSystem.setShaderColor(1, 1, 1, 1);
	}
	
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void eventHandler(ScreenEvent.DrawScreenEvent.Post event) {
		if (event.getScreen() instanceof InventoryScreen || event.getScreen() instanceof CreativeModeInventoryScreen) {
			displayOverlay(event);
		}
	}

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void eventHandler(RenderGameOverlayEvent.Pre event) {
		if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
			// Only display the rupee count for two seconds when the rupee count changes.
			double newRupeeCount = ((Minecraft.getInstance().player.getCapability(ZeldaModModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new ZeldaModModVariables.PlayerVariables())).rupee_count);

			// Shouldn't have to account for the Y2038 problem, since longs in Java are 64-bit.
			Date now = new Date();
			_timer = now.getTime();
			if (_oldRupeeCount != newRupeeCount) {
				_deadline = _timer + 2000;
				_oldRupeeCount = newRupeeCount;
			}
			
			if (_deadline > 0) {
				if (_timer < _deadline) {
					displayOverlay(event);
				} else {
					_deadline = 0;
				}
			}
		}
	}
}
