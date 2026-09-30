# Fantasy Armor

A Fabric mod for Minecraft **26.3** that adds a Fantasy Armor set: polished steel plate with brass trim,
a 3D great helm, and small icy-blue glowing accents. Protection and durability match diamond armor.

## Recipes

`I` = iron ingot, `C` = copper ingot, `D` = diamond

| Helmet | Chestplate | Leggings | Boots |
|--------|------------|----------|-------|
| `IDI`<br>`C C` | `C C`<br>`IDI`<br>`III` | `ICI`<br>`D D`<br>`I I` | `C C`<br>`I I` |

## Building

Requires JDK 25.

```
./gradlew build
```

The mod jar ends up in `build/libs/`. Put it in your `mods` folder along with
Fabric API. Every push also builds on GitHub Actions; the jar can be downloaded
from the run's **Artifacts**.

## Textures

Polished steel with brass trim. A few details glow icy blue: they are drawn a second time at full
brightness, so they stay lit in the dark.

- Worn helmet: a custom 3D great helm model (`src/client/java/com/kierbob/fantasyarmor/client/GreatHelmModel.java`)
  with its own 64x64 texture, `assets/fantasy_armor/textures/entity/great_helm.png`. The eye slit glows.
- Worn chestplate and boots: `assets/fantasy_armor/textures/entity/equipment/humanoid/fantasy.png` (64x32).
  The gem in the breastplate glows. Arms: shoulder pauldrons and forearm bracers; boots are ankle-high.
- Worn leggings: `.../humanoid_leggings/fantasy.png`. The knee gems glow.
- Each worn texture has a `_glow.png` twin with the same layout, containing only the glowing pixels
  (everything else transparent). Whatever is drawn there glows.
- Inventory icons: `assets/fantasy_armor/textures/item/fantasy_*.png` (16x16)

Worn armor is drawn by `src/client/java/com/kierbob/fantasyarmor/client/GlowingArmorRenderer.java`.
To change a texture, edit the PNG in place and keep the same size and layout.

Stats live in `src/main/java/com/kierbob/fantasyarmor/item/FantasyArmorMaterial.java`.
