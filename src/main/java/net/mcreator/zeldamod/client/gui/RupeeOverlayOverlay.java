
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
		
		Minecraft.getInstance().font.drawShadow(event.getPoseStack(),
				String.format("%.0f", ((Minecraft.getInstance().player.getCapability(ZeldaModModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new ZeldaModModVariables.PlayerVariables())).rupee_count)),
				20, 10, -1);
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
		
		Minecraft.getInstance().font.drawShadow(event.getMatrixStack(),
				String.format("%.0f", ((Minecraft.getInstance().player.getCapability(ZeldaModModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new ZeldaModModVariables.PlayerVariables())).rupee_count)),
				20, (int)_yForInGameOverlay + 5, -1);
		RenderSystem.setShaderTexture(0, new ResourceLocation("zelda_mod:textures/screens/rupee.png"));
		Minecraft.getInstance().gui.blit(event.getMatrixStack(), 5, (int)_yForInGameOverlay, 0, 0, 16, 16, 16, 16);

		RenderSystem.depthMask(true);
		RenderSystem.defaultBlendFunc();
		RenderSystem.enableDepthTest();
		RenderSystem.disableBlend();
		RenderSystem.setShaderColor(1, 1, 1, 1);
	}

	private enum OverlayState {
		HIDING,
		APPEARING,   // 0.1 seconds
		SHOWING,     // 2 seconds
		DISAPPEARING // 0.1 seconds
	}

	private static OverlayState _state = OverlayState.HIDING;

	private static double _yForInGameOverlay = -18.0;

	private static void beginStateMachine() {
		// Ignore calls to begin the state machine if we are in motion.
		if (!_firstCall && _deadline == 0) {
			nextState();
		}
	}

	private static void handleCurrentState(double delta) {
		switch (_state) {
			case HIDING:
				_yForInGameOverlay = -18.0;
				break;
			case APPEARING:
				_yForInGameOverlay += 23.0 / 0.25 * delta; // distance / duration in seconds * seconds elapsed since last frame
				break;
			case SHOWING:
				_yForInGameOverlay = 5.0;
				break;
			case DISAPPEARING:
				_yForInGameOverlay -= 23.0 / 0.25 * delta; // distance / duration in seconds * seconds elapsed since last frame
				break;
		}
	}

	private static void nextState() {
		switch (_state) {
			case HIDING:
				_deadline = _timer + 250;
				_state = OverlayState.APPEARING;
				break;
			case APPEARING:
				_deadline = _timer + 1500;
				_state = OverlayState.SHOWING;
				break;
			case SHOWING:
				_deadline = _timer + 250;
				_state = OverlayState.DISAPPEARING;
				break;
			case DISAPPEARING:
				_deadline = 0;
				_state = OverlayState.HIDING;
				break;
		}
	}
	
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void eventHandler(ScreenEvent.DrawScreenEvent.Post event) {
		if (event.getScreen() instanceof InventoryScreen || event.getScreen() instanceof CreativeModeInventoryScreen) {
			displayOverlay(event);
		}
	}

	private static boolean _firstCall = true;

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void eventHandler(RenderGameOverlayEvent.Pre event) {
		if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
			displayOverlay(event);
			
			// Shouldn't have to account for the Y2038 problem, since longs in Java are 64-bit.
			Date now = new Date();
			double delta = (double)(now.getTime() - _timer) / 1000.0;
			_timer = now.getTime();
			
			// Start the state machine if the rupee count changes.
			double newRupeeCount = ((Minecraft.getInstance().player.getCapability(ZeldaModModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new ZeldaModModVariables.PlayerVariables())).rupee_count);
			if (_oldRupeeCount != newRupeeCount) {
				beginStateMachine();
				_oldRupeeCount = newRupeeCount;
			}

			// Manage the state machine if it is in motion.
			if (!_firstCall && _deadline > 0) {
				if (_timer >= _deadline) {
					nextState();
				} else {
					handleCurrentState(delta);
				}
			}
			_firstCall = false;
		}
	}
}
