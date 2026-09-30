package com.kierbob.fantasyarmor.client;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.entity.ArmorModelSet;
import net.minecraft.resources.Identifier;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;

import com.kierbob.fantasyarmor.FantasyArmor;
import com.kierbob.fantasyarmor.item.ModItems;

public class FantasyArmorClient implements ClientModInitializer {
	// Worn textures. Each glow texture holds only the glowing details (gems, eye slit), which are drawn again at full brightness.
	private static final Identifier ARMOR_TEXTURE = FantasyArmor.id("textures/entity/equipment/humanoid/fantasy.png");
	private static final Identifier ARMOR_GLOW_TEXTURE = FantasyArmor.id("textures/entity/equipment/humanoid/fantasy_glow.png");
	private static final Identifier GREAT_HELM_TEXTURE = FantasyArmor.id("textures/entity/great_helm.png");
	private static final Identifier GREAT_HELM_GLOW_TEXTURE = FantasyArmor.id("textures/entity/great_helm_glow.png");
	private static final Identifier LEGGINGS_TEXTURE = FantasyArmor.id("textures/entity/equipment/humanoid_leggings/fantasy.png");
	private static final Identifier LEGGINGS_GLOW_TEXTURE = FantasyArmor.id("textures/entity/equipment/humanoid_leggings/fantasy_glow.png");

	// Our own copy of the vanilla armor model, one layer per slot.
	private static final ModelLayerLocation HELMET_LAYER = layer("helmet");
	private static final ModelLayerLocation CHESTPLATE_LAYER = layer("chestplate");
	private static final ModelLayerLocation LEGGINGS_LAYER = layer("leggings");
	private static final ModelLayerLocation BOOTS_LAYER = layer("boots");
	// A second set used only for the boots: ankle-high, a little bigger than the leggings like vanilla boots.
	private static final ArmorModelSet<ModelLayerLocation> SLIM_LAYERS = new ArmorModelSet<>("helmet", "chestplate", "leggings", "boots")
			.map(slot -> new ModelLayerLocation(FantasyArmor.id("fantasy_armor_slim"), slot));
	private static final ModelLayerLocation SLIM_BOOTS_LAYER = new ModelLayerLocation(FantasyArmor.id("fantasy_armor_slim"), "boots");

	@Override
	public void onInitializeClient() {
		// Vanilla armor shapes, but closer to the body than vanilla's 0.5 (leggings) / 1.0 (everything else).
		// Kept above the skin's outer layer (0.25 on the body, arms and legs) so the skin doesn't poke through.
		ModelLayerRegistry.registerArmorModelLayers(
				new ArmorModelSet<>(HELMET_LAYER, CHESTPLATE_LAYER, LEGGINGS_LAYER, BOOTS_LAYER),
				() -> HumanoidModel.createArmorMeshSet(new CubeDeformation(0.3F), new CubeDeformation(0.6F))
						.map(mesh -> LayerDefinition.create(mesh, 64, 32))
		);
		// The leggings stop above the boots, so the boots can sit just outside them (0.45) without overlapping.
		ModelLayerRegistry.registerArmorModelLayers(
				SLIM_LAYERS,
				() -> HumanoidModel.createArmorMeshSet(new CubeDeformation(0.3F), new CubeDeformation(0.45F))
						.map(mesh -> LayerDefinition.create(mesh, 64, 32))
		);
		ModelLayerRegistry.registerModelLayer(GreatHelmModel.LAYER, GreatHelmModel::createLayer);

		// The helmet is a custom great helm model instead of the vanilla helmet shape.
		ArmorRenderer.register(context -> new GlowingArmorRenderer(context, GreatHelmModel.LAYER, GREAT_HELM_TEXTURE, GREAT_HELM_GLOW_TEXTURE), ModItems.FANTASY_HELMET);
		ArmorRenderer.register(context -> new GlowingArmorRenderer(context, CHESTPLATE_LAYER, ARMOR_TEXTURE, ARMOR_GLOW_TEXTURE), ModItems.FANTASY_CHESTPLATE);
		ArmorRenderer.register(context -> new GlowingArmorRenderer(context, LEGGINGS_LAYER, LEGGINGS_TEXTURE, LEGGINGS_GLOW_TEXTURE), ModItems.FANTASY_LEGGINGS);
		ArmorRenderer.register(context -> new GlowingArmorRenderer(context, SLIM_BOOTS_LAYER, ARMOR_TEXTURE, ARMOR_GLOW_TEXTURE), ModItems.FANTASY_BOOTS);
	}

	private static ModelLayerLocation layer(String slot) {
		return new ModelLayerLocation(FantasyArmor.id("fantasy_armor"), slot);
	}
}
