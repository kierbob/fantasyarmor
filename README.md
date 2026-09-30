# Fantasy Armor

A Fabric mod for Minecraft **26.3** that adds a Fantasy Armor set. For now the
set is a straight copy of diamond armor: the same stats and the same textures.

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

## Adding custom textures

Right now everything points at vanilla diamond textures. To use your own:

1. **Inventory icons:** add 16x16 PNGs to
   `src/main/resources/assets/fantasy_armor/textures/item/` (e.g. `fantasy_helmet.png`).
   Then add item models in `assets/fantasy_armor/models/item/`, e.g. `fantasy_helmet.json`:
   ```json
   { "parent": "minecraft:item/generated", "textures": { "layer0": "fantasy_armor:item/fantasy_helmet" } }
   ```
   Finally, in `assets/fantasy_armor/items/fantasy_helmet.json`, change
   `minecraft:item/diamond_helmet` to `fantasy_armor:item/fantasy_helmet`.
2. **Worn armor:** add `fantasy.png` to
   `assets/fantasy_armor/textures/entity/equipment/humanoid/`, `humanoid_leggings/`
   and `humanoid_baby/`, then change `minecraft:diamond` to `fantasy_armor:fantasy`
   in `assets/fantasy_armor/equipment/fantasy.json`. (Copying the vanilla diamond
   PNGs is a good starting template.)

Stats live in `src/main/java/com/kierbob/fantasyarmor/item/FantasyArmorMaterial.java`.
