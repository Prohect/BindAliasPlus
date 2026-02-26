# BindAliasPlus

A Fabric **client-side** mod that lets you create **aliases** (macros) and **bind them to keys/mouse buttons**, so one key press can run a full sequence of actions — similar to a “bind/alias config” workflow.

<!-- languages -->
- 🇺🇸 [English](README.md)
- 🇨🇳 [中文 (简体)](README_CN.md)

> Client-only: servers do not need to install this mod.

---

## Installation

1. Install [Fabric Loader](https://fabricmc.net/use/) for your Minecraft version.
2. Download the mod from Modrinth: https://modrinth.com/mod/bind-alias-plus/versions
3. Put the `.jar` into your Minecraft `mods` folder.
4. Launch Minecraft.

---

## Core idea

You define an **alias** as a list of **steps**, then bind a key to run it.

- Steps are separated by **spaces**.
- A step can be:
  - an alias with no args: `jump`
  - an alias with args: `swapSlot\10\39` (args are separated by backslash `\`)

You can bind keys so that:
- on **press** it runs one alias
- on **release** it runs another alias (typical `+something` / `-something` pattern)

---

## Syntax

### A) Alias definitions are split by spaces

An alias definition is a sequence of steps **split by space ` `**:

- `equipElytra jump wait\1 jump swapSlot\19 +use -use`

### B) **Step arguments** are split by backslash `\`

If a step contains `\`, the part before the first `\` is the alias name, and the rest are its arguments:

- `swapSlot\10\39` → alias name `swapSlot`, args: `10`, `39`
- `wait\20` → alias name `wait`, args: `20`

### C) Spaces inside a single **step argument**

If a single **step argument** must contain spaces, wrap that argument in **double quotes**.

This matters mostly for the built-ins that send chat/commands, or for nested definitions (see next section).

---

## Nested definitions (important): `BuiltinAliasWithGreedyStringArgs` and `;`

Some built-in aliases take a **greedy string** payload so you can define / bind / unbind / chat / run commands from inside another alias:

- `alias`
- `bind`
- `unbind`
- `say`
- `sendCommand`

### The key trick: avoid quotes by using `;`

Outer parsing always splits alias steps by **spaces**.

So if you write a nested payload that contains spaces, you normally need quotes to keep it as a single argument.

However, for the greedy-string built-ins **you can also avoid quotes** by writing the nested payload as a single token and using **semicolon `;`** to separate pieces inside that token.

Examples (both work):

- With quotes (payload has spaces):
  - `/alias makeJumpAlias alias\"jump +jump wait\1 -jump"`

- Without quotes (payload is one token, use `;` as separators):
  - `/alias makeJumpAlias alias\jump;+jump;wait\1;-jump`

### How greedy-string built-ins process the payload

- `alias\...`, `bind\...`, `unbind\...`:
  - they **replace `;` with space ` `** before sending the final chat command to the game.
  - This is what makes `/alias makeJumpAlias alias\jump;+jump;wait\1;-jump` work: it sends a chat command `/alias jump +jump wait\1 -jump` when `makeJumpAlias` is called.

- `say\...` and `sendCommand\...`:
  - they **do NOT replace `;`**.
  - Whatever you put in their payload is sent as-is (so `;` stays as a literal character).

---

## Commands

### `/alias <name> <definition>`
Create or replace a **user alias**.

Notes:
- You **cannot overwrite** built-in aliases.
- User aliases run by expanding the definition into steps.

Example:
- `/alias pearl swapSlot\12 +use wait\1 -use swapSlot\12`

### `/bind <key> <definition-or-aliasName>`
Bind a key/mouse button.

Behavior:
1. If `<definition-or-aliasName>` matches an existing alias name (including `+name`/`-name` forms), it binds to that alias.
2. Otherwise it treats it as an inline definition and creates an internal alias for the bind.

Press/release behavior:
- If your bind definition contains `+something` and/or `-something`, the mod can derive an “opposite” release side automatically from those `+/-` steps.

Examples:
- `/bind g jump`
- `/bind g +forward wait\10 -forward`

### `/bindByAliasName <key> <aliasName>`
Bind a key directly to an existing alias name.

Examples:
- `/bindByAliasName mouse5 +fly`
- `/bindByAliasName g jump`

### `/unbind <key>`
Remove a bind.

Example:
- `/unbind mouse5`

### `/var <varName> <source>`
Create or update a **variable** that stores an integer value.

Sources:
- `hotbarSlot` or `selectedSlot` - Current hotbar slot (1-9)
- `itemsOfSlot0` to `itemsOfSlot9` - Item count in slot (0=offhand, 1-9=hotbar)
- A number (1-41) - Direct integer value

Variable naming rules:
- Variable names **cannot start with a number**

Examples:
- `/var mySlot hotbarSlot` - Store current hotbar slot into variable `mySlot`
- `/var backup 5` - Store the number 5 into variable `backup`
- `/var arrowCount itemsOfSlot2` - Store item count from hotbar slot 2
- `/var offhandItems itemsOfSlot0` - Store item count from offhand slot

Usage in aliases:
- Variables can be used in `slot` and `swapSlot` aliases
- `/alias saveSlot var\mySlot\hotbarSlot` - Save current slot
- `/alias restoreSlot slot\mySlot` - Switch to saved slot
- `/alias swapWithSaved swapSlot\mySlot\hotbarSlot` - Swap current with saved
- `/alias countArrows var\arrows\itemsOfSlot9` - Count items in slot 9

### `/reloadCFG`
Reload config file from disk.

### `/unloadCFGAliases`
Remove all user aliases that were loaded from the config file.

Notes:
- Only removes aliases with `fromAutoload=true` (loaded during startup)
- Runtime-created aliases (via `/alias` command) are preserved
- Useful for testing different configurations without restart

Example:
- `/unloadCFGAliases` - Remove all config file aliases

### `/unloadCFGBinds`
Remove all keybindings that were loaded from the config file.

Notes:
- Only removes bindings with `fromAutoload=true` (loaded during startup)
- Runtime-created bindings (via `/bind` or `/bindByAliasName` commands) are preserved
- Also cleans up associated internal aliases

Example:
- `/unloadCFGBinds` - Remove all config file keybindings

### `/unloadCFGVars`
Remove all variables that were loaded from the config file.

Notes:
- Only removes variables tracked in `AUTOLOADED_VARIABLES`
- Runtime-created variables (via `/var` command) are preserved

Example:
- `/unloadCFGVars` - Remove all config file variables

### `/unloadCFGAll`
Remove all aliases, keybindings, and variables that were loaded from the config file.

Notes:
- Convenience command that calls all three unload operations
- Preserves all runtime-created items
- Perfect for switching between different config profiles

Example:
- `/unloadCFGAll` - Remove all config file items at once

---

## Configuration file

Path:
- `config/bind-alias-plus.cfg`

Rules:
- One command per line
- Leading `/` is optional
- `#` starts a comment line

Reload after editing:
- `/reloadCFG`

---

## Built-in aliases

BindAliasPlus ships with built-in aliases you can call inside your alias definitions.

### 1) Built-ins with arguments (use `\` between args)

| Alias | Arguments | What it does | Example |
|---|---:|---|---|
| `log\text` | text | Log message to console (debug) | `log\Hello` |
| `slot\n` | `n=1..9` or variable | Select hotbar slot (supports variables) | `slot\3` or `slot\mySlot` |
| `swapSlot\a\b` | `a,b` (numbers or variables) | Swap two slots | `swapSlot\10\39` or `swapSlot\mySlot\5` |
| `swapSlot\a` | `a` (number or variable) | Swap slot `a` with the **currently selected hotbar slot** | `swapSlot\19` or `swapSlot\mySlot` |
| `var\name\source` | name, source | Store a value in a variable. Source can be `hotbarSlot`, `selectedSlot`, `itemsOfSlot0-9` (item count), or a number. Variable names cannot start with numbers. | `var\mySlot\hotbarSlot` or `var\backup\5` or `var\count\itemsOfSlot2` |
| `wait\ticks` | ticks | Delay execution (`20 ticks = 1 second`) | `wait\20` |
| `yaw\deg` | deg | Add to yaw (relative) | `yaw\90` |
| `pitch\deg` | deg | Add to pitch (relative) | `pitch\-30` |
| `setYaw\deg` | deg | Set yaw (absolute) | `setYaw\180` |
| `setPitch\deg` | deg | Set pitch (absolute) | `setPitch\0` |
| `alias\payload` / `alias\"payload"` | payload | Create/replace an alias. If you want to avoid quotes, you can write the payload as one token and use `;` (it will be converted to spaces). | `alias\jump;+jump;wait\1;-jump` |
| `bind\payload` / `bind\"payload"` | payload | Bind a key. If you want to avoid quotes, write the payload as one token and use `;` (it will be converted to spaces). | `bind\mouse4;+bow` |
| `unbind\key` | key | Unbind a key. (No special `;` handling needed.) | `unbind\g` |
| `say\text` / `say\"text"` | text | Send a chat message. `;` is NOT special here (it is sent literally). Quotes only needed if text has spaces. | `say\"hello world"` |
| `sendCommand\cmd` / `sendCommand\"cmd"` | cmd | Send a command (no leading `/`). `;` is NOT special here. Quotes only needed if command has spaces. | `sendCommand\"gamemode creative"` |

#### Slot numbering for `swapSlot`

Slots follow Minecraft’s internal indexing in this mod’s UI docs:

- `1-9` → hotbar
- `10-36` → inventory
- `37-40` → armor slots (37 feet … 40 head)
- `41` → offhand

### 2) Aliases without arguments (direct actions)

These are available by default and are convenient for press/release patterns:

| Alias | Equivalent to | What it does |
|---|---|---|
| `+attack` / `-attack` | `builtinAttack\1` / `builtinAttack\0` | Hold/release left-click |
| `+use` / `-use` | `builtinUse\1` / `builtinUse\0` | Hold/release right-click |
| `+forward` / `-forward` | `builtinForward\1` / `builtinForward\0` | Hold/release forward |
| `+back` / `-back` | `builtinBack\1` / `builtinBack\0` | Hold/release back |
| `+left` / `-left` | `builtinLeft\1` / `builtinLeft\0` | Hold/release left |
| `+right` / `-right` | `builtinRight\1` / `builtinRight\0` | Hold/release right |
| `+jump` / `-jump` | `builtinJump\1` / `builtinJump\0` | Hold/release jump |
| `+sneak` / `-sneak` | `builtinSneak\1` / `builtinSneak\0` | Hold/release sneak |
| `+sprint` / `-sprint` | `builtinSprint\1` / `builtinSprint\0` | Hold/release sprint |
| `drop` / `dropStack` | `builtinDrop\0` / `builtinDrop\1` | Drop one / whole stack |
| `swapHand` | — | Swap main-hand and offhand |
| `cyclePerspective` | — | Cycle camera perspective |
| `FPS` / `TPS` / `TPS2` | `builtinSetPerspective\0/1/2` | Set specific perspective |
| `+silent` / `-silent` | `builtinSilent\1` / `builtinSilent\0` | Suppress/restore bind/alias feedback messages |
| `reloadCFG` | — | Reload config file |
| `unloadCFGAliases` | — | Remove all aliases loaded from config file |
| `unloadCFGBinds` | — | Remove all keybindings loaded from config file |
| `unloadCFGVars` | — | Remove all variables loaded from config file |
| `unloadCFGAll` | — | Remove all config file items (aliases, bindings, variables) |

---

## Examples

### Elytra + firework (press-and-hold)

Put:
- Elytra in slot `10` (inventory first row, first slot)
- Fireworks in slot `19` (inventory second row, first slot)

Then:

- `/alias equipElytra swapSlot\10\39`
- `/alias jump +jump wait\1 -jump`
- `/alias +fly equipElytra jump wait\1 jump swapSlot\19 +use -use`
- `/alias -fly equipElytra swapSlot\19`
- `/bind mouse5 +fly`

### Quick bow without using a hotbar slot

Put your bow in slot `11`:

- `/alias +bow swapSlot\11 +use`
- `/alias -bow -use swapSlot\11`
- `/bind mouse4 +bow`

### Slot memory system using variables

Save your current hotbar slot and restore it later:

- `/alias saveSlot var\savedSlot\hotbarSlot`
- `/alias restoreSlot slot\savedSlot`
- `/bind f5 saveSlot`
- `/bind f6 restoreSlot`

Or swap between saved slot and current slot:

- `/alias saveAndSwap var\backup\hotbarSlot slot\3`
- `/alias swapBack slot\backup`
- `/bind f7 saveAndSwap`
- `/bind f8 swapBack`

Track item counts:

- `/alias checkArrows var\arrowCount\itemsOfSlot2 log\arrowCount`
- `/alias checkOffhand var\offhandCount\itemsOfSlot0 log\offhandCount`
- `/bind i checkArrows`

Advanced: Dynamic weapon switching:

```
# Save your combat slot (e.g., slot 1 with sword)
var combatSlot 1

# Save your building slot (e.g., slot 3 with blocks)
var buildSlot 3

# Quick switch aliases
alias toCombat slot\combatSlot
alias toBuild slot\buildSlot

# Bind to keys
bind q toCombat
bind e toBuild
```

### Toggle bind pattern (state switcher)

You can create toggle switches by rebinding a key to different aliases on each press. This is useful when you want state to persist after releasing the key (unlike press-and-hold pattern).

#### Complete elytra toggle example

Put:
- Elytra in slot `10` (inventory first row, first slot)
- Fireworks in slot `26` (inventory third row, first slot)

Config file setup:

```
# Define reusable equipment aliases
alias +equipElytra swapSlot\10\39
alias -equipElytra swapSlot\10\39
alias +holdFireworks swapSlot\26\41
alias -holdFireworks swapSlot\26\41

# Define jump helper
alias jump +jump wait\1 -jump

# Define the actual fly action
alias +fly +equipElytra jump wait\1 jump +holdFireworks +use -use
alias -fly -equipElytra -holdFireworks

# State switcher: creates two states that toggle back and forth
alias fly1 bind\"mouse5 fly2" +fly
alias fly2 bind\"mouse5 fly1" -fly

# Initial bind
bind mouse5 fly1
```

How it works:
1. Press `mouse5` → executes `fly1` → rebinds `mouse5` to `fly2` → runs `+fly` (equips elytra and activates)
2. Press `mouse5` again → executes `fly2` → rebinds `mouse5` back to `fly1` → runs `-fly` (unequips elytra)
3. State persists even after releasing the key (this is the key difference from press-and-hold pattern)

#### Using `+silent/-silent` to avoid chat spam

When rebinding keys frequently, you'll see "Bound key..." messages. Use silent mode to suppress them:

```
# Wrap bind commands in silent mode
alias fly1 +silent bind\"mouse5 fly2" -silent +fly
alias fly2 +silent bind\"mouse5 fly1" -silent -fly
bind mouse5 fly1
```

Or keep it cleaner - the `bind` built-in is already silent by default when called from inside an alias:

```
# The bind commands won't spam, but +fly/-fly will show normal feedback
alias fly1 bind\"mouse5 fly2" +fly
alias fly2 bind\"mouse5 fly1" -fly
bind mouse5 fly1
```

#### Toggle vs Press-and-hold comparison

**Toggle pattern** (state switcher):
- Use `bind mouse5 fly1` with rebinding logic
- State persists after releasing the key
- Press once to activate, press again to deactivate
- Good for: toggleable modes, equipment swapping

**Press-and-hold pattern**:
- Use `bind mouse5 +fly` directly
- Activates on press, deactivates on release
- Must hold the key to keep active
- Good for: temporary actions, charge-and-release mechanics

Both patterns can use the same `+fly/-fly` aliases!

### Config profile management

You can switch between different configuration "profiles" using the unload commands:

```
# Scenario: Testing a new config setup

# 1. Start with your main config loaded automatically
# Your config file has: aliases, bindings, variables

# 2. During gameplay, create some test items
/alias quickTest +forward wait\20 -forward
/bind h quickTest
/var testSlot hotbarSlot

# 3. Want to try a different config? Unload the old one
/unloadCFGAll

# 4. Your test items are still there!
# quickTest alias, h binding, and testSlot variable preserved

# 5. Manually create new config items or edit config file
/alias newAlias +jump wait\10 -jump
/bind g newAlias

# 6. Or reload from an edited config file
/reloadCFG

# 7. To remove only specific categories
/unloadCFGAliases  # Only aliases from config
/unloadCFGBinds    # Only bindings from config
/unloadCFGVars     # Only variables from config
```

**Use cases:**
- **Quick testing:** Try new configs without restarting Minecraft
- **Profile switching:** Unload PvP config, load building config
- **Safe experimentation:** Test changes, unload if bad, keep runtime items
- **Debugging:** Isolate which config items cause issues

---

## Notes / limitations

- Keybind triggers are ignored while you are typing in: chat, sign editor, book editor, command block screen.
- Automation can be suspicious on some servers (anti-cheat). Use responsibly.
- Variables persist only during the current game session - they are cleared when you quit.

---

## License

[CC0-1.0](LICENSE)
