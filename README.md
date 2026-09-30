# Fantasy Armor

A Fabric mod for Minecraft **26.3** that adds a Fantasy Armor set. For now the
set has the same stats as diamond armor, with midnight-blue textures and a glowing purple stripe.

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

The textures are diamond armor recolored to midnight blue, with a purple stripe down the middle of each piece.
The stripe glows: it's drawn a second time at full brightness, so it stays lit in the dark.

- Inventory icons: `src/main/resources/assets/fantasy_armor/textures/item/fantasy_*.png` (16x16)
- Worn helmet: a custom 3D great helm model (`src/client/java/com/kierbob/fantasyarmor/client/GreatHelmModel.java`)
  with its own 64x64 texture, `assets/fantasy_armor/textures/entity/great_helm.png`, and glow texture `great_helm_glow.png`
- Worn chestplate and boots: `assets/fantasy_armor/textures/entity/equipment/humanoid/fantasy.png` (64x32)
- Glow layer: `.../humanoid/fantasy_glow.png`, the same layout but containing only the stripe
  (everything else transparent). Whatever is drawn here glows.
- Worn leggings: `.../humanoid_leggings/fantasy.png` and `fantasy_glow.png`, the same idea for the leggings layer.

Worn armor is drawn by `src/client/java/com/kierbob/fantasyarmor/client/GlowingArmorRenderer.java`.
To change a texture, edit the PNG in place and keep the same size and layout.

Stats live in `src/main/java/com/kierbob/fantasyarmor/item/FantasyArmorMaterial.java`.
