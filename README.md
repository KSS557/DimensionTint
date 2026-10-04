# Dimension Tint

A server-side Fabric mod that colors player nicknames in the TAB list based on their current dimension.

## About

**DimensionTint** is a lightweight server-side mod that dynamically changes the color of player nicknames in the TAB list according to the dimension the local player is in. No more guessing which world you're in — just open TAB and look at the color.

The mod is fully configurable through a simple `.properties` file and supports both vanilla and modded dimensions.

### Dependencies
[<img src="https://cdn.modrinth.com/data/cached_images/4bfd169f59d83eab2b21122b98f3d74447b394b0.png"
alt="Fabric API"
width="250"
height="50">](https://modrinth.com/mod/fabric-api/versions)
[<img src="https://cdn.modrinth.com/data/cached_images/da04d419a091f9eaf23694f4b8ddb6bcb774fb86.png"
alt="Fabric Language Kotlin"
width="250"
height="50">](https://modrinth.com/mod/fabric-language-kotlin/versions)

## Features

- **Dynamic nickname coloring** in the TAB list
- **Support for all dimensions** — Overworld, Nether, End, and any modded ones
- **Flexible configuration** via a `.properties` file
- **Minecraft-style colors** (`&a`, `&c`, `&5`)
- **Automatic config generation** on first launch


## ⚙️ Configuration

The config file is located at:
```
.minecraft/config/dimensiontintconfig.properties
```

### Format

```properties
<dimension_id>=<color>
```

- `<dimension_id>` — full dimension ID, e.g. `minecraft:overworld`
- `&<color_code>` — standard Minecraft color code (`&0`–`&f`)

### Example config

```properties
minecraft:overworld=&a
minecraft:the_nether=&c
minecraft:the_end=&5

twilightforest:twilight_forest=&9
magnatour:ore_continent=&f
```

### Adding a modded dimension

If you have a mod installed that adds a new dimension:

1. Open the `dimensiontintconfig.properties` file
2. Add a new line with the dimension ID and the desired color
3. Save the file and restart the game

**How to find a dimension ID?**
- Open TAB or F3 and look at the dimension name
- Or check the mod's source code / documentation

## Available colors

### Named colors (Minecraft)

| Code | Color |
|---|---|
| `&0` | <font color="#000000">Black</font> |
| `&1` | <font color="#0000AA">Dark Blue</font> |
| `&2` | <font color="#00AA00">Dark Green</font> |
| `&3` | <font color="#00AAAA">Dark Aqua (dark cyan)</font> |
| `&4` | <font color="#AA0000">Dark Red</font> |
| `&5` | <font color="#AA00AA">Dark Purple</font> |
| `&6` | <font color="#FFAA00">Gold</font> |
| `&7` | <font color="#AAAAAA">Light Gray</font> |
| `&8` | <font color="#555555">Dark Gray</font> |
| `&9` | <font color="#5555FF">Blue</font> |
| `&a` | <font color="#55FF55">Green</font> |
| `&b` | <font color="#55FFFF">Aqua (cyan)</font> |
| `&c` | <font color="#FF5555">Red</font> |
| `&d` | <font color="#FF55FF">Pink (light purple)</font> |
| `&e` | <font color="#FFFF55">Yellow</font> |
| `&f` | <font color="#FFFFFF">White</font> |

