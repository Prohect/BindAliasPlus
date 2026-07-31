# BindAlias

A Minecraft Fabric client mod that allows creating custom aliases and key bindings to automate complex in-game
actions with simple key presses. Also enables autonomous AI agent control via an in-game HTTP API — agents can see,
reason about, and act in Minecraft independently. Use with the
[BindAlias MCP tool](https://github.com/Prohect/BindAlias-mcp) to let AI play the game.

<!-- languages -->

- 🇺🇸 [English](README.md)
- 🇨🇳 [中文 (简体)](README_CN.md)

## Overview

BindAlias enhances your Minecraft gameplay by letting you define custom aliases for sequences of actions and bind
them to keys. Whether you need to quickly swap inventory slots, automate elytra flight, or chain multiple actions (like
using a bow or placing blocks that not even in your hotbars or second hand), this mod simplifies repetitive tasks through configurable aliases and key bindings.

## Features

- **Custom Aliases**: Create reusable aliases for single or multiple in-game actions (e.g., swap items, use abilities,
  move).
- **Key Bindings**: Bind aliases to keys, with support for separate actions on key press and release.
- **Built-in Aliases**: Predefined aliases for common actions (e.g., `swapSlot`, `wait`, `use`, `attack`).
- **Command System**: Intuitive commands to manage aliases and bindings (e.g., `/alias`, `/bind`, `/unbind`).
- **Config Persistence**: Saves aliases and bindings in a config file, loaded automatically when joining servers.
- **Chained Actions**: Combine aliases to create complex sequences (e.g., equip elytra → use firework → fly).
- **AI Agent Support**: Built-in HTTP API (`GET /state`, `GET /screenshot`, `POST /runAlias`, etc.) for AI agent
  control. Use with the [BindAlias MCP tool](https://github.com/Prohect/BindAlias-mcp) to let AI agents see,
  reason about, and act in your Minecraft world.

## Installation

1. Ensure you have [Fabric Loader](https://fabricmc.net/use/) installed for your Minecraft version.
2. Download the latest `bind-alias-*.*.*.jar` from
   the [releases page](https://modrinth.com/mod/bind-alias/versions).
3. Place the JAR file in your Minecraft `mods` folder.
4. Launch Minecraft with the Fabric loader.

## Usage

### Core Concepts

- **Alias**: A custom or built-in action (or sequence of actions) that can be executed.
- **Key Binding**: A link between a physical key (e.g., `mouse5`, `keyboard.g`) and an alias (or two aliases: one for
  press, one for release).

### Built-in Aliases

BindAlias includes prebuilt aliases for common actions. They are divided into **aliases with arguments** and *
_aliases without arguments_*.

#### Aliases with Arguments

_Note: Slots follow Minecraft's internal numbering:_

- 1-9 → Hotbar slots
- 10-36 → Inventory slots (10-19 = first row)
- 37-40 → Equipment slots (37 = feet, 38 = legs, 39 = chest, 40 = head)
- 41 → Offhand slot
-
- u can cover args with double quotes so that the white space inside will not be referred as split mark.
- **RECOMMENDED for nested definitions**: When using `alias`, `bind`, `unbind`, `say`, `sendCommand`, or `localSay` builtin aliases inside other alias definitions, use semicolon `;` instead of space ` ` as the divider between arguments. This allows you to use normal space dividers in the nested definition without conflict. Example: `alias +testAlias bind\v;+anotherAlias alias\+yetAnotherAlias;+anotherAlias;+jump alias\+nextAlias;wait\2;+yetAnotherAlias wait\1 bind\x;+testAlias` - here semicolons separate the arguments for these builtin aliases, while spaces work normally.

| Alias                  | Description                                                                                                                                                                                                                                                                                                                                                                  | Example                                                                                                                |
| ---------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------------------------- |
| `log`                  | Logs a message to the game console (for debugging).                                                                                                                                                                                                                                                                                                                          | `log\Hello World`                                                                                                      |
| `slot\slotNumber`      | Switches to a specific hotbar slot (1-9). Accepts variable names.                                                                                                                                                                                                                                                                                                            | `slot\3` (switches to hotbar slot 3), `slot\mySlot` (uses variable)                                                    |
| `swapSlot\slot1\slot2` | Swaps items between two inventory slots (1-9 hotbar, 10-36 inventory, 37-40 armor, 41 offhand). Works in any container screen; use `cN` for the Nth slot of the open menu (e.g. crafting table `c1` = result, furnace `c3` = output) — enables crafting/forging/enchanting. Accepts variable names (plain slots only).                                                       | `swapSlot\10\39` (swaps inventory slot 10 with chestplate slot), `swapSlot\c1\10` (takes crafting result into slot 10) |
| `swapSlot\slot1`       | Swaps items between the currently held hotbar slot (main hand) and the specified `slot1`. Accepts variable names.                                                                                                                                                                                                                                                            | `swapSlot\19` (swaps current hotbar slot with inventory slot 19)                                                       |
| `wait\ticks`           | Pauses execution for a specified number of ticks (20 ticks = 1 second). Accepts variable names.                                                                                                                                                                                                                                                                              | `wait\20` (waits 1 second), `wait\myTicks` (uses variable)                                                             |
| `yaw\degrees`          | Adjusts player yaw (horizontal rotation) by a relative degree value. Accepts variable names.                                                                                                                                                                                                                                                                                 | `yaw\90` (turns 90° right), `yaw\myVar` (uses variable)                                                                |
| `pitch\degrees`        | Adjusts player pitch (vertical rotation) by a relative degree value. Accepts variable names.                                                                                                                                                                                                                                                                                 | `pitch\-30` (looks 30° down), `pitch\myVar` (uses variable)                                                            |
| `setYaw\degrees`       | Sets player yaw to an absolute degree value (0 = north, 90 = east). Accepts variable names.                                                                                                                                                                                                                                                                                  | `setYaw\180` (faces south), `setYaw\myVar` (uses variable)                                                             |
| `setPitch\degrees`     | Sets player pitch to an absolute degree value (-90 = straight up, 90 = straight down). Accepts variable names.                                                                                                                                                                                                                                                               | `setPitch\0` (looks straight ahead), `setPitch\myVar` (uses variable)                                                  |
| `alias\args`           | almost same as command alias, except u need to cover args with double quotes.                                                                                                                                                                                                                                                                                                | `alias\"meow say\nya~"` (create or replace an alias)                                                                   |
| `bind\args`            | almost same as command bind, except u need to cover args with double quotes.                                                                                                                                                                                                                                                                                                 | `bind\"m meow wait\0 +fly"` (create or replace a bind)                                                                 |
| `unbind\keyName`       | almost same as command unbind.                                                                                                                                                                                                                                                                                                                                               | `unbind\m` (unbind binds on a key)                                                                                     |
| `say\string`           | say a chat message.                                                                                                                                                                                                                                                                                                                                                          | `say\"How old r u?"` (send a chat message that is "how old r u?")                                                      |
| `localSay\string`      | display a chat message on the local client only, without sending to the server. Useful for testing, notifications, and debug output.                                                                                                                                                                                                                                         | `localSay\"Debug: slot is \(mySlot)"` (local-only message)                                                             |
| `sendCommand\command`  | send a command.                                                                                                                                                                                                                                                                                                                                                              | `sendCommand\"gamemode creative"` (send a command that is "gamemode creative")                                         |
| `var\varName\source`   | Store a value into a variable. Sources: `hotbarSlot`, `itemsOfSlot0-9`, `pitch`, `yaw`, or a number.                                                                                                                                                                                                                                                                         | `var\mySlot\hotbarSlot` (store hotbar slot), `var\angle\pitch` (store pitch angle)                                     |
| `applyRecipe\query`    | Place an unlocked, craftable recipe into the crafting grid of the open recipe menu (inventory / crafting table / furnace), like clicking it in the recipe book. No crafting is performed. Query: result-item id (`minecraft:torch` / `torch`) or locale-name substring (`iron sword`). Errors (no menu open / not unlocked / missing ingredients) go to the local game chat. | `applyRecipe\torch`                                                                                                    |
| `reapply\action`       | Manually re-assert a held-down boolean alias (attack, use, forward, back, left, right, jump, sneak, sprint, drop, openInventory). Useful at the end of a UserAlias after screen transitions.                                                                                                                                                                                 | `reapply\forward` (re-presses forward key if held)                                                                     |
| `openInventory\state`  | Opens (1) or closes (0) the inventory screen.                                                                                                                                                                                                                                                                                                                                | `openInventory\1` (opens inventory), `openInventory\0` (closes inventory)                                              |

> **Numeric aliases support variable references:** `yaw`, `pitch`, `setYaw`, `setPitch`, `slot`, `swapSlot`, `wait`, and `setPerspective` all accept variable names (e.g., `yaw\myVar` or `slot\mySlot`) in place of raw numbers.

#### Aliases without Arguments

These are shorthand aliases that map to common `state=1` (start) and `state=0` (stop) actions for simpler usage:

| Alias               | Equivalent To             | Description                                                                                                                                                                                                               |
| ------------------- | ------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `+attack`           | `builtinAttack\1`         | Starts attacking (holds left-click).                                                                                                                                                                                      |
| `-attack`           | `builtinAttack\0`         | Stops attacking (releases left-click).                                                                                                                                                                                    |
| `+use`              | `builtinUse\1`            | Starts using held item (holds right-click).                                                                                                                                                                               |
| `-use`              | `builtinUse\0`            | Stops using held item (releases right-click).                                                                                                                                                                             |
| `+forward`          | `builtinForward\1`        | Starts moving forward.                                                                                                                                                                                                    |
| `-forward`          | `builtinForward\0`        | Stops moving forward.                                                                                                                                                                                                     |
| `+back`             | `builtinBack\1`           | Starts moving backward.                                                                                                                                                                                                   |
| `-back`             | `builtinBack\0`           | Stops moving backward.                                                                                                                                                                                                    |
| `+left`             | `builtinLeft\1`           | Starts moving left.                                                                                                                                                                                                       |
| `-left`             | `builtinLeft\0`           | Stops moving left.                                                                                                                                                                                                        |
| `+right`            | `builtinRight\1`          | Starts moving right.                                                                                                                                                                                                      |
| `-right`            | `builtinRight\0`          | Stops moving right.                                                                                                                                                                                                       |
| `+jump`             | `builtinJump\1`           | Starts jumping (holds jump key).                                                                                                                                                                                          |
| `-jump`             | `builtinJump\0`           | Stops jumping (releases jump key).                                                                                                                                                                                        |
| `+sneak`            | `builtinSneak\1`          | Starts sneaking (holds sneak key).                                                                                                                                                                                        |
| `-sneak`            | `builtinSneak\0`          | Stops sneaking (releases sneak key).                                                                                                                                                                                      |
| `+sprint`           | `builtinSprint\1`         | Starts sprinting (holds sprint key).                                                                                                                                                                                      |
| `-sprint`           | `builtinSprint\0`         | Stops sprinting (releases sprint key).                                                                                                                                                                                    |
| `+drop`             | `builtinDrop\1`           | Presses the drop key. **Holding continuously drops** items in both the 3D game and container/inventory screens (vanilla-compatible, with initial repeat-delay). Drops full stack when combined with keyboard control key. |
| `-drop`             | `builtinDrop\0`           | Releases the drop key.                                                                                                                                                                                                    |
| `+openInventory`    | `builtinOpenInventory\1`  | Opens the inventory screen.                                                                                                                                                                                               |
| `-openInventory`    | `builtinOpenInventory\0`  | Closes the inventory screen (if open).                                                                                                                                                                                    |
| `pickItem`          | —                         | Triggers vanilla pick-block on the targeted block/entity.                                                                                                                                                                 |
| `swapHand`          | _                         | Swaps items between main hand and offhand.                                                                                                                                                                                |
| `+silent`           | `builtinSilent\1`         | Enables silent mode (suppresses command feedback messages).                                                                                                                                                               |
| `-silent`           | `builtinSilent\0`         | Disables silent mode (re-enables command feedback messages).                                                                                                                                                              |
| `+lockKey\<target>` | `builtinLock\<target>\1`  | Locks a game key or custom alias. Use `gameKey:attack`, `gameKey:forward`, etc. for vanilla keys, or an alias name for custom aliases.                                                                                    |
| `-lockKey\<target>` | `builtinLock\<target>\0`  | Unlocks a previously locked game key or custom alias.                                                                                                                                                                     |
| `cyclePerspective`  | —                         | Cycles through camera perspectives (FPS → TPS → TPS2).                                                                                                                                                                    |
| `FPS`               | `builtinSetPerspective\0` | Switches to first-person view.                                                                                                                                                                                            |
| `TPS`               | `builtinSetPerspective\1` | Switches to third-person back view.                                                                                                                                                                                       |
| `TPS2`              | `builtinSetPerspective\2` | Switches to third-person front view.                                                                                                                                                                                      |
| `reloadCFG`         | —                         | Reloads the config file (applies changes without restarting).                                                                                                                                                             |
| `unloadCFGAliases`  | —                         | Removes all aliases that were loaded from the config file.                                                                                                                                                                |
| `unloadCFGBinds`    | —                         | Removes all keybindings that were loaded from the config file.                                                                                                                                                            |
| `unloadCFGVars`     | —                         | Removes all variables that were loaded from the config file.                                                                                                                                                              |
| `unloadCFGAll`      | —                         | Removes all aliases, keybindings, and variables loaded from config.                                                                                                                                                       |

### Variables

Variables let you capture and reuse in-game values (hotbar slot, pitch/yaw angles, item
counts, etc.).

**Sources** for `var\varName\source`:

| Source         | Description                                  | Example                  |
| -------------- | -------------------------------------------- | ------------------------ |
| `hotbarSlot`   | Current hotbar slot (1-9)                    | `var\mySlot\hotbarSlot`  |
| `itemsOfSlotN` | Item count in slot N (0=offhand, 1-9=hotbar) | `var\count\itemsOfSlot2` |
| `pitch`        | Player's current pitch angle (float)         | `var\myPitch\pitch`      |
| `yaw`          | Player's current yaw angle (float)           | `var\myYaw\yaw`          |
| `42` or `3.14` | A literal number (int or float)              | `var\backup\42`          |

Variables can then be used as arguments in any numeric alias (e.g., `yaw\myVar`,
`slot\mySlot`, `wait\myTicks`).

**Variable-related commands:**

| Command                | Purpose                             | Example                  |
| ---------------------- | ----------------------------------- | ------------------------ |
| `/var <name> <source>` | Create or update a variable.        | `/var mySlot hotbarSlot` |
| `/unloadCFGVars`       | Remove all config-loaded variables. | `/unloadCFGVars`         |

### AI Agent / MCP HTTP Server

BindAlias includes a built-in HTTP server that enables AI agents (such as Claude, ChatGPT, or custom
automation scripts) to observe and control your Minecraft client. This is the companion mod for the
[BindAlias MCP tool](https://github.com/Prohect/BindAlias-mcp).

The server listens on `http://localhost:25575` (falls back to the next free port, up to +9, when occupied — the chosen port is logged) and provides:

| Endpoint       | Method | Description                                                              |
| -------------- | ------ | ------------------------------------------------------------------------ |
| `/state`       | GET    | Full game-state snapshot + drained message channels (envelope)           |
| `/screenshot`  | GET    | In-memory PNG screenshot (base64) + envelope (no chat spam, no file I/O) |
| `/runAlias`    | POST   | Execute alias chains remotely (e.g., `swapSlot\1\2`)                     |
| `/defineAlias` | POST   | Define new aliases via API (feedback arrives in the chat channel)        |
| `/readCFG`     | GET    | Read the current config file contents (raw text, no envelope)            |
| `/writeCFG`    | POST   | Write to the config file (modify binds, aliases, variables)              |
| `/listRecipes` | GET    | List recipes unlocked in the recipe book (diff or per-query answers)     |

Every game-interacting endpoint answers with the same **envelope**:
`{"tick":N, "state":{...}, "chat":[...], "mod":[...], "sound":[...], "recipe":[...]}` — `state` is a full snapshot
for `/state` and a changed-members-only diff for everything else (omitted when nothing changed); `chat` (game chat),
`mod` (mod log), `sound` (subtitled sounds with compass direction + distance) and `recipe` (newly unlocked recipes)
are message channels drained exactly once per message and omitted when empty. All state info is also visible on the
vanilla HUD or the open screen.

**Example agent usage:**

```bash
# Check what the player sees
curl http://localhost:25575/state

# Execute an alias chain
curl -X POST http://localhost:25575/runAlias -d "swapSlot\1\2\wait\2\+attack"

# Take a screenshot
curl http://localhost:25575/screenshot -o screen.png
```

### Example Config

```cfg
/var offHand 41
/alias jumpOnce +jump wait\0 -jump
## 36->elytra; 27->firework
/alias +fly swapSlot\36\39 jumpOnce wait\0 jumpOnce swapSlot\27\41 +use -use TPS
/alias -fly swapSlot\36\39 swapSlot\27\41 wait\2 FPS
/alias +fastUse_Var swapSlot\varFastUse\offHand +use
/alias -fastUse_Var -use swapSlot\varFastUse\offHand
/alias +fastAttack_Var swapSlot\varFastAttack wait\1 +attack
/alias -fastAttack_Var -attack swapSlot\varFastAttack

# alias fly_on +silent bind\"mouse5 fly_off" -silent +fly
# alias fly_off +silent bind\"mouse5 fly_on" -silent -fly

/bind w +forward
/bind a +left
/bind s +back
/bind d +right
/bind space +jump
/bind left.shift +sneak
/bind left.control +sprint
/bind mouse1 +attack
/bind mouse2 +use
/bind mouse5 +fly
## water; powder_snow; food; ender_pearl; bow;... 3 fast use slots
/bind mouse4 var\varFastUse\19 +fastUse_Var
/bind b var\varFastUse\20 +fastUse_Var
/bind v var\varFastUse\28 +fastUse_Var
## fortune_pickaxe 1 fast mine slot
/bind n var\varFastAttack\29 +fastAttack_Var
```

## Configuration

- **Config File**: At `config/bind-alias.cfg`. Automatically created if there is not one.
- **Auto-Load**: Aliases and bindings in the config file are loaded automatically when the mod loads.
- **Manual Edit**: You can directly edit the config file to add/modify aliases/bindings (use the same syntax as in-game
  commands). See the [Example Config](#example-config) section above for a complete real-world config.

## Commands Reference

| Command                          | Purpose                                                                                                                                                                                                                                                                                                                     | Example                                               |
| -------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------- |
| `/alias <name> <definition>`     | Create a custom alias.                                                                                                                                                                                                                                                                                                      | `/alias myAlias +jump wait\1 -jump`                   |
| `/bind <key> <definition>`       | Bind a key to a sequence of aliases by definition of this command or an existing alias.For each definition split by divide mark(things inside double quotes would still be a same block) starts with + or -, it will create an opposite alias. For the 1st eg, it also bind -forward and +back to release of keyboard key g | `/bind g +forward wait\10 -back   OR   /bind n +drop` |
| `/bindByAliasName <key> <alias>` | Bind a key to an existing alias.                                                                                                                                                                                                                                                                                            | `/bindByAliasName mouse5 +fly`                        |
| `/unbind <key>`                  | Remove a key binding.                                                                                                                                                                                                                                                                                                       | `/unbind mouse5`                                      |
| `/reloadCFG`                     | Reload config from file.                                                                                                                                                                                                                                                                                                    | `/reloadCFG`                                          |
| `/var <name> <source>`           | Create/update a variable. Sources: `hotbarSlot`, `itemsOfSlot0-9`, `pitch`, `yaw`, or a number.                                                                                                                                                                                                                             | `/var mySlot hotbarSlot`, `/var angle pitch`          |
| `/unloadCFGAliases`              | Remove all aliases loaded from config.                                                                                                                                                                                                                                                                                      | `/unloadCFGAliases`                                   |
| `/unloadCFGBinds`                | Remove all keybindings loaded from config.                                                                                                                                                                                                                                                                                  | `/unloadCFGBinds`                                     |
| `/unloadCFGVars`                 | Remove all variables loaded from config.                                                                                                                                                                                                                                                                                    | `/unloadCFGVars`                                      |
| `/unloadCFGAll`                  | Remove all aliases, binds, and variables loaded from config.                                                                                                                                                                                                                                                                | `/unloadCFGAll`                                       |

## Notes

- **Compatibility**: Works with most Fabric mods; may conflict with mods that modify key handling or inventory
  mechanics.
- **Minecraft Version**: Requires Minecraft 1.21+ (Yarn mappings) or 26.x (Mojang mappings). Check the release page
  for version-specific builds (filenames include the MC version).
- **Variables**: Supports integer and floating-point values. Numeric aliases (`yaw`, `pitch`, `setYaw`, `setPitch`,
  `slot`, `swapSlot`, `wait`, `setPerspective`) accept variable names in place of raw numbers.
- **Safety**: Avoid excessive automation on servers with anti-cheat systems (some actions may be flagged).
- **MCP Server**: The built-in HTTP API listens on port `25575` by default (falls back to the next free port when
  occupied). Pair with the
  [BindAlias MCP tool](https://github.com/Prohect/BindAlias-mcp) for AI agent integration.

## Contributing

Contributions are welcome! Feel free to open issues for bugs/feature requests or submit pull requests with improvements.

## License

This mod is licensed under the [Creative Commons Zero v1.0 Universal](LICENSE).
