package com.kierbob.fantasyarmor.item;

import java.util.function.Function;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.equipment.ArmorType;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;

import com.kierbob.fantasyarmor.FantasyArmor;

public class ModItems {
	public static final Item FANTASY_HELMET = registerArmor("fantasy_helmet", ArmorType.HELMET);
	public static final Item FANTASY_CHESTPLATE = registerArmor("fantasy_chestplate", ArmorType.CHESTPLATE);
	public static final Item FANTASY_LEGGINGS = registerArmor("fantasy_leggings", ArmorType.LEGGINGS);
	public static final Item FANTASY_BOOTS = registerArmor("fantasy_boots", ArmorType.BOOTS);

	private static Item registerArmor(String name, ArmorType type) {
		return register(name, Item::new, new Item.Properties()
				.humanoidArmor(FantasyArmorMaterial.INSTANCE, type)
				.durability(type.getDurability(FantasyArmorMaterial.BASE_DURABILITY)));
	}

	private static Item register(String name, Function<Item.Properties, Item> factory, Item.Properties properties) {
		ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, FantasyArmor.id(name));
		Item item = factory.apply(properties.setId(key));
		Registry.register(BuiltInRegistries.ITEM, key, item);
		return item;
	}

	public static void initialize() {
		// Show the set in the Combat tab, right after diamond boots.
		CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT).register(output ->
				output.insertAfter(Items.DIAMOND_BOOTS, FANTASY_HELMET, FANTASY_CHESTPLATE, FANTASY_LEGGINGS, FANTASY_BOOTS));
	}
}
