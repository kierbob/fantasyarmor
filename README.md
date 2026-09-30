# Fantasy Armor

A Fabric mod for Minecraft **26.3** that adds a Fantasy Armor set. For now the
set has the same stats as diamond armor, with diamond textures cracked by glowing amethyst.

## Recipes

`D` = diamond, `A` = amethyst shard

| Helmet | Chestplate | Leggings | Boots |
|--------|------------|----------|-------|
| `DAD`<br>`D D` | `D D`<br>`DAD`<br>`DAD` | `DAD`<br>`D D`<br>`D D` | `A A`<br>`D D` |

## Building

Requires JDK 25.

```
./gradlew build
```

The mod jar ends up in `build/libs/`. Put it in your `mods` folder along with
Fabric API. Every push also builds on GitHub Actions; the jar can be downloaded
from the run's **Artifacts**.

## Textures

The textures are vanilla diamond with glowing amethyst cracks drawn in:

- Inventory icons: `src/main/resources/assets/fantasy_armor/textures/item/fantasy_*.png` (16x16)
- Worn helmet, chestplate and boots: `assets/fantasy_armor/textures/entity/equipment/humanoid/fantasy.png` (64x32)
- Worn leggings and armor on baby mobs still use the vanilla diamond texture. To replace them, add
  `fantasy.png` under `textures/entity/equipment/humanoid_leggings/` (and `humanoid_baby/`), then change
  that layer's `minecraft:diamond` to `fantasy_armor:fantasy` in `assets/fantasy_armor/equipment/fantasy.json`.

To change a texture, edit the PNG in place and keep the same size and layout.

Stats live in `src/main/java/com/kierbob/fantasyarmor/item/FantasyArmorMaterial.java`.
