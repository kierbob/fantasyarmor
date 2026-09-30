package com.kierbob.fantasyarmor.item;

import java.util.Map;

import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

import com.kierbob.fantasyarmor.FantasyArmor;

/**
 * Stats are a straight copy of vanilla diamond armor for now.
 */
public class FantasyArmorMaterial {
	// Diamond's durability multiplier. Each piece's durability is this times a per-slot factor.
	public static final int BASE_DURABILITY = 33;

	// Points at assets/fantasy_armor/equipment/fantasy.json, which controls the worn texture.
	public static final ResourceKey<EquipmentAsset> FANTASY_ASSET = ResourceKey.create(EquipmentAssets.ROOT_ID, FantasyArmor.id("fantasy"));

	public static final ArmorMaterial INSTANCE = new ArmorMaterial(
			BASE_DURABILITY,
			Map.of(
					ArmorType.HELMET, 3,
					ArmorType.CHESTPLATE, 8,
					ArmorType.LEGGINGS, 6,
					ArmorType.BOOTS, 3
			),
			10, // enchantability
			SoundEvents.ARMOR_EQUIP_DIAMOND,
			2.0F, // toughness
			0.0F, // knockback resistance
			ItemTags.REPAIRS_DIAMOND_ARMOR, // repaired with diamonds in an anvil
			FANTASY_ASSET
	);
}
