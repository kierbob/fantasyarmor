package com.kierbob.fantasyarmor.client;

import com.mojang.blaze3d.vertex.PoseStack;
import org.jspecify.annotations.Nullable;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.LightCoordsUtil;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;

/**
 * Draws the armor like vanilla does, then draws the glow texture over it at full brightness,
 * so the lightning stays lit even in the dark.
 */
public record GlowingArmorRenderer(HumanoidModel<HumanoidRenderState> model, Identifier texture, @Nullable Identifier glowTexture) implements ArmorRenderer {
	public GlowingArmorRenderer(EntityRendererProvider.Context context, ModelLayerLocation layer, Identifier texture, @Nullable Identifier glowTexture) {
		this(new HumanoidModel<>(context.bakeLayer(layer)), texture, glowTexture);
	}

	@Override
	public void render(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, ItemStack stack, HumanoidRenderState state, EquipmentSlot slot, int light, HumanoidModel<HumanoidRenderState> contextModel) {
		// The armor itself, lit like the rest of the entity.
		ArmorRenderer.submitTransformCopyingModel(contextModel, state, this.model, state, false, submitNodeCollector.order(0),
				poseStack, RenderTypes.armorCutoutNoCull(this.texture), light, OverlayTexture.NO_OVERLAY, 0);

		if (stack.hasFoil()) {
			ArmorRenderer.submitTransformCopyingModel(contextModel, state, this.model, state, false, submitNodeCollector.order(0),
					poseStack, RenderTypes.armorCutoutNoCullGlint(this.texture), light, OverlayTexture.NO_OVERLAY, 0);
		}

		// The lightning, drawn on top at full brightness. Transparent pixels in the glow texture are skipped.
		if (this.glowTexture != null) {
			ArmorRenderer.submitTransformCopyingModel(contextModel, state, this.model, state, false, submitNodeCollector.order(1),
					poseStack, RenderTypes.armorCutoutNoCull(this.glowTexture), LightCoordsUtil.FULL_BRIGHT, OverlayTexture.NO_OVERLAY, 0);
		}
	}
}
