# Fantasy Armor

A Fabric mod for Minecraft **26.3** that adds a Fantasy Armor set. For now the
set has the same stats as diamond armor, with diamond textures and glowing purple lightning bolts.

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

The textures are vanilla diamond with purple Lichtenberg ("lightning tattoo") bolts drawn in.
The purple bolts glow: they're drawn a second time at full brightness, so they stay lit in the dark.

- Inventory icons: `src/main/resources/assets/fantasy_armor/textures/item/fantasy_*.png` (16x16)
- Worn helmet, chestplate and boots: `assets/fantasy_armor/textures/entity/equipment/humanoid/fantasy.png` (64x32)
- Glow layer: `.../humanoid/fantasy_glow.png`, the same layout but containing only the bolts
  (everything else transparent). Whatever is drawn here glows.
- Worn leggings still use the vanilla diamond texture and don't glow yet.

Worn armor is drawn by `src/client/java/com/kierbob/fantasyarmor/client/GlowingArmorRenderer.java`.
To change a texture, edit the PNG in place and keep the same size and layout.

Stats live in `src/main/java/com/kierbob/fantasyarmor/item/FantasyArmorMaterial.java`.
