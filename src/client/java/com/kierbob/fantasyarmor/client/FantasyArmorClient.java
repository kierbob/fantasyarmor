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
	// Worn textures. The glow texture holds only the lightning, which is drawn again at full brightness.
	private static final Identifier ARMOR_TEXTURE = FantasyArmor.id("textures/entity/equipment/humanoid/fantasy.png");
	private static final Identifier ARMOR_GLOW_TEXTURE = FantasyArmor.id("textures/entity/equipment/humanoid/fantasy_glow.png");
	// No leggings texture yet, so the leggings use vanilla diamond and have nothing to glow.
	private static final Identifier LEGGINGS_TEXTURE = Identifier.withDefaultNamespace("textures/entity/equipment/humanoid_leggings/diamond.png");

	// Our own copy of the vanilla armor model, one layer per slot.
	private static final ModelLayerLocation HELMET_LAYER = layer("helmet");
	private static final ModelLayerLocation CHESTPLATE_LAYER = layer("chestplate");
	private static final ModelLayerLocation LEGGINGS_LAYER = layer("leggings");
	private static final ModelLayerLocation BOOTS_LAYER = layer("boots");

	@Override
	public void onInitializeClient() {
		// Same shapes and sizes vanilla uses for armor: 0.5 inflation for leggings, 1.0 for the rest.
		ModelLayerRegistry.registerArmorModelLayers(
				new ArmorModelSet<>(HELMET_LAYER, CHESTPLATE_LAYER, LEGGINGS_LAYER, BOOTS_LAYER),
				() -> HumanoidModel.createArmorMeshSet(new CubeDeformation(0.5F), new CubeDeformation(1.0F))
						.map(mesh -> LayerDefinition.create(mesh, 64, 32))
		);

		ArmorRenderer.register(context -> new GlowingArmorRenderer(context, HELMET_LAYER, ARMOR_TEXTURE, ARMOR_GLOW_TEXTURE), ModItems.FANTASY_HELMET);
		ArmorRenderer.register(context -> new GlowingArmorRenderer(context, CHESTPLATE_LAYER, ARMOR_TEXTURE, ARMOR_GLOW_TEXTURE), ModItems.FANTASY_CHESTPLATE);
		ArmorRenderer.register(context -> new GlowingArmorRenderer(context, LEGGINGS_LAYER, LEGGINGS_TEXTURE, null), ModItems.FANTASY_LEGGINGS);
		ArmorRenderer.register(context -> new GlowingArmorRenderer(context, BOOTS_LAYER, ARMOR_TEXTURE, ARMOR_GLOW_TEXTURE), ModItems.FANTASY_BOOTS);
	}

	private static ModelLayerLocation layer(String slot) {
		return new ModelLayerLocation(FantasyArmor.id("fantasy_armor"), slot);
	}
}
