# BindAliasPlus

A Minecraft Fabric client mod that allows creating custom aliases and key bindings to automate complex in-game actions
with simple key presses.

<!-- languages -->
- 🇺🇸 [English](README.md)
- 🇨🇳 [中文 (简体)](README_CN.md)

## Overview

BindAliasPlus enhances your Minecraft gameplay by letting you define custom aliases for sequences of actions and bind
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

## Installation

1. Ensure you have [Fabric Loader](https://fabricmc.net/use/) installed for your Minecraft version.
2. Download the latest `bind-alias-plus-*.*.*.jar` from
   the [releases page](https://modrinth.com/mod/bind-alias-plus/versions).
3. Place the JAR file in your Minecraft `mods` folder.
4. Launch Minecraft with the Fabric loader.

## Usage

### Core Concepts

- **Alias**: A custom or built-in action (or sequence of actions) that can be executed.
- **Key Binding**: A link between a physical key (e.g., `mouse5`, `keyboard.g`) and an alias (or two aliases: one for
  press, one for release).

### Built-in Aliases

BindAliasPlus includes prebuilt aliases for common actions. They are divided into **aliases with arguments** and *
*aliases without arguments**.

#### Aliases with Arguments

*Note: Slots follow Minecraft's internal numbering:*

- 1-9 → Hotbar slots
- 10-36 → Inventory slots (10-19 = first row)
- 37-40 → Equipment slots (37 = feet, 38 = legs, 39 = chest, 40 = head)
- 41 → Offhand slot
-
- u can cover args with double quotes so that the white space inside will not be referred as split mark.
- **RECOMMENDED for nested definitions**: When using `alias`, `bind`, `unbind`, `say`, or `sendCommand` builtin aliases inside other alias definitions, use semicolon `;` instead of space ` ` as the divider between arguments. This allows you to use normal space dividers in the nested definition without conflict. Example: `alias +testAlias bind\v;+anotherAlias alias\+yetAnotherAlias;+anotherAlias;+jump alias\+nextAlias;wait\2;+yetAnotherAlias wait\1 bind\x;+testAlias` - here semicolons separate the arguments for these builtin aliases, while spaces work normally.

| Alias                  | Description                                                                               | Example                                                                        |
|------------------------|-------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------|
| `log`                  | Logs a message to the game console (for debugging).                                       | `log\Hello World`                                                              |
| `slot\slotNumber`      | Switches to a specific hotbar slot (1-9). Accepts variable names.                         | `slot\3` (switches to hotbar slot 3), `slot\mySlot` (uses variable)           |
| `swapSlot\slot1\slot2` | Swaps items between two inventory slots. Accepts variable names.                          | `swapSlot\10\39` (swaps inventory slot 10 with chestplate slot)                |
| `swapSlot\slot1`       | Swaps items between the currently held hotbar slot (main hand) and the specified `slot1`. Accepts variable names. | `swapSlot\19` (swaps current hotbar slot with inventory slot 19)               |
| `wait\ticks`           | Pauses execution for a specified number of ticks (20 ticks = 1 second). Accepts variable names. | `wait\20` (waits 1 second), `wait\myTicks` (uses variable)                     |
| `yaw\degrees`          | Adjusts player yaw (horizontal rotation) by a relative degree value. Accepts variable names. | `yaw\90` (turns 90° right), `yaw\myVar` (uses variable)                       |
| `pitch\degrees`        | Adjusts player pitch (vertical rotation) by a relative degree value. Accepts variable names. | `pitch\-30` (looks 30° down), `pitch\myVar` (uses variable)                   |
| `setYaw\degrees`       | Sets player yaw to an absolute degree value (0 = north, 90 = east). Accepts variable names. | `setYaw\180` (faces south), `setYaw\myVar` (uses variable)                    |
| `setPitch\degrees`     | Sets player pitch to an absolute degree value (-90 = straight up, 90 = straight down). Accepts variable names. | `setPitch\0` (looks straight ahead), `setPitch\myVar` (uses variable)         |
| `alias\args`           | almost same as command alias, except u need to cover args with double quotes.             | `alias\"meow say\nya~"` (create or replace an alias)                           |
| `bind\args`            | almost same as command bind, except u need to cover args with double quotes.              | `bind\"m meow wait\0 +fly"` (create or replace a bind)                         |
| `unbind\keyName`       | almost same as command unbind.                                                            | `unbind\m` (unbind binds on a key)                                             |
| `say\string`           | say a chat message.                                                                       | `say\"How old r u?"` (send a chat message that is "how old r u?")              |
| `sendCommand\command`  | send a command.                                                                           | `sendCommand\"gamemode creative"` (send a command that is "gamemode creative") |
| `var\varName\source`  | Store a value into a variable. Sources: `hotbarSlot`, `itemsOfSlot0-9`, `pitch`, `yaw`, or a number. | `var\mySlot\hotbarSlot` (store hotbar slot), `var\angle\pitch` (store pitch angle) |
| `runAlias\aliasName`   | Run a named alias from another alias (safer nesting).                                     | `runAlias\myAlias` (execute myAlias)                                                |

> **Numeric aliases support variable references:** `yaw`, `pitch`, `setYaw`, `setPitch`, `slot`, `swapSlot`, `wait`, and `setPerspective` all accept variable names (e.g., `yaw\myVar` or `slot\mySlot`) in place of raw numbers.

#### Aliases without Arguments

These are shorthand aliases that map to common `state=1` (start) and `state=0` (stop) actions for simpler usage:

| Alias       | Equivalent To      | Description                                                   |
|-------------|--------------------|---------------------------------------------------------------|
| `+attack`   | `builtinAttack\1`  | Starts attacking (holds left-click).                          |
| `-attack`   | `builtinAttack\0`  | Stops attacking (releases left-click).                        |
| `+use`      | `builtinUse\1`     | Starts using held item (holds right-click).                   |
| `-use`      | `builtinUse\0`     | Stops using held item (releases right-click).                 |
| `+forward`  | `builtinForward\1` | Starts moving forward.                                        |
| `-forward`  | `builtinForward\0` | Stops moving forward.                                         |
| `+back`     | `builtinBack\1`    | Starts moving backward.                                       |
| `-back`     | `builtinBack\0`    | Stops moving backward.                                        |
| `+left`     | `builtinLeft\1`    | Starts moving left.                                           |
| `-left`     | `builtinLeft\0`    | Stops moving left.                                            |
| `+right`    | `builtinRight\1`   | Starts moving right.                                          |
| `-right`    | `builtinRight\0`   | Stops moving right.                                           |
| `+jump`     | `builtinJump\1`    | Starts jumping (holds jump key).                              |
| `-jump`     | `builtinJump\0`    | Stops jumping (releases jump key).                            |
| `+sneak`    | `builtinSneak\1`   | Starts sneaking (holds sneak key).                            |
| `-sneak`    | `builtinSneak\0`   | Stops sneaking (releases sneak key).                          |
| `+sprint`   | `builtinSprint\1`  | Starts sprinting (holds sprint key).                          |
| `-sprint`   | `builtinSprint\0`  | Stops sprinting (releases sprint key).                        |
| `drop`      | `builtinDrop\0`    | Drops one item from the held stack.                           |
| `dropStack` | `builtinDrop\1`    | Drops the entire held stack.                                  |
| `swapHand`  | _                  | Swaps items between main hand and offhand.                    |
| `+silent`   | `builtinSilent\1`  | Enables silent mode (suppresses command feedback messages).   |
| `-silent`   | `builtinSilent\0`  | Disables silent mode (re-enables command feedback messages).  |
| `+lock:attack`  | `builtinLock\attack\1`  | Locks the attack key (prevents physical input).               |
| `-lock:attack`  | `builtinLock\attack\0`  | Unlocks the attack key.                                       |
| `+lock:use`     | `builtinLock\use\1`     | Locks the use/place key.                                      |
| `-lock:use`     | `builtinLock\use\0`     | Unlocks the use/place key.                                    |
| `+lock:forward` | `builtinLock\forward\1` | Locks the forward movement key.                               |
| `-lock:forward` | `builtinLock\forward\0` | Unlocks the forward movement key.                             |
| `+lock:back`    | `builtinLock\back\1`    | Locks the backward movement key.                              |
| `-lock:back`    | `builtinLock\back\0`    | Unlocks the backward movement key.                            |
| `+lock:left`    | `builtinLock\left\1`    | Locks the left movement key.                                  |
| `-lock:left`    | `builtinLock\left\0`    | Unlocks the left movement key.                                |
| `+lock:right`   | `builtinLock\right\1`   | Locks the right movement key.                                 |
| `-lock:right`   | `builtinLock\right\0`   | Unlocks the right movement key.                               |
| `+lock:jump`    | `builtinLock\jump\1`    | Locks the jump key.                                           |
| `-lock:jump`    | `builtinLock\jump\0`    | Unlocks the jump key.                                         |
| `+lock:sneak`   | `builtinLock\sneak\1`   | Locks the sneak key.                                          |
| `-lock:sneak`   | `builtinLock\sneak\0`   | Unlocks the sneak key.                                        |
| `+lock:sprint`  | `builtinLock\sprint\1`  | Locks the sprint key.                                         |
| `-lock:sprint`  | `builtinLock\sprint\0`  | Unlocks the sprint key.                                       |
| `cyclePerspective` | —                  | Cycles through camera perspectives (FPS → TPS → TPS2).       |
| `FPS`              | `builtinSetPerspective\0` | Switches to first-person view.                                |
| `TPS`              | `builtinSetPerspective\1` | Switches to third-person back view.                           |
| `TPS2`             | `builtinSetPerspective\2` | Switches to third-person front view.                          |
| `shutdown`         | —                  | Gracefully shuts down the game (useful for auto-test configs). |
| `reloadCFG`      | —                  | Reloads the config file (applies changes without restarting).                  |
| `unloadCFGAliases` | —                | Removes all aliases that were loaded from the config file.                    |
| `unloadCFGBinds`   | —                | Removes all keybindings that were loaded from the config file.                |
| `unloadCFGVars`    | —                | Removes all variables that were loaded from the config file.                  |
| `unloadCFGAll`     | —                | Removes all aliases, keybindings, and variables loaded from config.           |

### Variables

Variables let you capture and reuse in-game values (hotbar slot, pitch/yaw angles, item
counts, etc.).

**Sources** for `var\varName\source`:

| Source          | Description                                         | Example                       |
|-----------------|-----------------------------------------------------|-------------------------------|
| `hotbarSlot`    | Current hotbar slot (1-9)                           | `var\mySlot\hotbarSlot`     |
| `itemsOfSlotN`  | Item count in slot N (0=offhand, 1-9=hotbar)        | `var\count\itemsOfSlot2`   |
| `pitch`         | Player's current pitch angle (float)                | `var\myPitch\pitch`        |
| `yaw`           | Player's current yaw angle (float)                  | `var\myYaw\yaw`            |
| `42` or `3.14`  | A literal number (int or float)                     | `var\backup\42`            |

Variables can then be used as arguments in any numeric alias (e.g., `yaw\myVar`,
`slot\mySlot`, `wait\myTicks`).

**Variable-related commands:**

| Command                        | Purpose                                            | Example                               |
|--------------------------------|----------------------------------------------------|---------------------------------------|
| `/var <name> <source>`         | Create or update a variable.                       | `/var mySlot hotbarSlot`              |
| `/unloadCFGVars`               | Remove all config-loaded variables.                | `/unloadCFGVars`                      |

### Examples

Here are practical examples to get started:

#### 1. Elytra + Firework Automation

Automate elytra deployment and firework use with a single key:

```bash
# Define alias to equip elytra to slot 39(the chestplate slot
# put your elytra in slot 10 ( the first slot of the first row of your inventory
/alias equipElytra swapSlot\10\39

# Define alias to jump once
/alias jump +jump wait\1 -jump

# Define +fly (on key press): equip elytra → jump twice to open it → use firework
# put your firework in slot 19 ( the first slot of the second row of your inventory,
/alias +fly equipElytra jump wait\1 jump swapSlot\19 +use -use

# Define -fly (on key release): re-equip what u equipped before
/alias -fly equipElytra swapSlot\19

# Bind mouse button 5 to +fly/-fly
/bind mouse5 +fly
```

#### 2. Quick Bow Usage

Quickly swap to a bow, use it, and swap back:
(bow won't need a hotbar anymore, also try this for Fortune and SilkTouch pickaxe or enderPearl)

```bash
# Define +bow (on press): swap to bow (slot 11) → start using
/alias +bow swapSlot\11 +use

# Define -bow (on release): stop using → swap back
/alias -bow -use swapSlot\11

# Bind mouse button 4 to +bow/-bow
/bind mouse4 +bow
```

#### 3. Using Silent Mode to Prevent Chat Spam

When creating toggle binds (like fly1/fly2 scripts), you can use silent mode to suppress feedback messages and avoid cluttering the chat:

```bash
# Example 1: State switcher pattern (toggles state on each key press)
# This approach maintains state even after releasing the key
# Using silent mode to prevent "Bound key..." messages

# Define fly1 (state 1): enable silent, rebind mouse5 to fly2, activate elytra, disable silent
/alias fly1 +silent bind\"mouse5 fly2" +equipElytra -silent

# Define fly2 (state 2): enable silent, rebind mouse5 to fly1, deactivate elytra, disable silent
/alias fly2 +silent bind\"mouse5 fly1" -equipElytra -silent

# Initial bind to mouse5
/bind mouse5 fly1

# Example 2: State switcher without wrapping actions in silent mode
# The bind command itself will be silent, but +fly/-fly execute normally
# This is cleaner when you want the state change to be silent but actions to have feedback
/alias fly1 bind\"mouse5 fly2" +fly
/alias fly2 bind\"mouse5 fly1" -fly
/bind mouse5 fly1

# Example 3: Press-and-hold pattern (different from state switcher!)
# This approach uses +/- aliases: action on press, opposite action on release
# Note: Using "/bind mouse5 +silent" would only enable silent mode while holding the key
/alias quietFly +silent equipElytra jump wait\1 jump swapSlot\19 +use -use -silent
```

**Note**: 
- **State switcher** (`fly1`/`fly2` pattern): Toggles between two states on each key press, state persists after release
- **Press-and-hold** (`+alias`/`-alias` pattern): Executes on press, reverses on release (like `/bind mouse5 +fly`)
- Silent mode only suppresses command feedback messages in chat. Error/warning logs are not affected.

#### 4. Lock Aliases

Lock aliases temporarily block physical keyboard/mouse input for a specific action, preventing
user interference during alias sequences (e.g., hold attack while an alias auto-swaps items).

```bash
# Lock forward movement for 20 ticks, then unlock
/alias lockTest +lock:forward wait\20 -lock:forward

# Use lock to safely hold attack while swapping
/alias +safeAttack +lock:attack swapSlot\11 wait\1 -lock:attack
/alias -safeAttack -attack swapSlot\11
/bind mouse4 +safeAttack

# Available lock targets: attack, use, forward, back, left, right, jump, sneak, sprint
```

#### 5. Variable System

Capture and reuse in-game values to create context-aware automation:

```bash
# Store current pitch/yaw into variables
/alias saveAngles var\_yaw\yaw var\_pitch\pitch

# Restore saved angles using setYaw/setPitch with variable references
/alias restoreAngles setYaw\_yaw setPitch\_pitch

# Turn 80 degrees right and look 20 degrees down, wait, then restore previous view
/alias lookAround saveAngles yaw\80 pitch\-20 wait\15 restoreAngles wait\5

# Use variables for dynamic slot swapping
/var backupSlot hotbarSlot
/alias quickSwap swapSlot\backupSlot swapSlot\9
```

## Configuration

- **Config File**: At `config/bind-alias-plus.cfg`. Automatically created if there is not one.
- **Auto-Load**: Aliases and bindings in the config file are loaded automatically when the mod loads.
- **Manual Edit**: You can directly edit the config file to add/modify aliases/bindings (use the same syntax as in-game
  commands).  
  **Example Config Content**:
  ```
  # BindAliasPlus config example
  # Define aliases for elytra equipment
  alias +equipElytra swapSlot\10\39
  alias -equipElytra swapSlot\10\39
  # Define aliases for fireworks handling
  alias +holdFireworks swapSlot\26\41
  alias -holdFireworks swapSlot\26\41
  # Define a simple jump action
  alias jump +jump wait\1 -jump
  # Define fly action sequence (on press)
  alias +fly +equipElytra jump wait\1 jump +holdFireworks +use -use
  # Define fly action sequence (on release)
  alias -fly -equipElytra -holdFireworks
  
  # Two ways to bind the key:
  
  # Option 1: State switcher pattern (toggles state on each press, state persists)
  # This is cleaner - the bind commands are silent, but +fly/-fly execute normally
  alias fly1 bind\"mouse5 fly2" +fly
  alias fly2 bind\"mouse5 fly1" -fly
  bind mouse5 fly1
  
  # Option 2: Press-and-hold pattern (activates on press, reverses on release)
  # Use this when you want the action only while holding the key
  bind mouse5 +fly

  # Variables - store and reuse game values
  var backupSlot hotbarSlot
  var arrowCount itemsOfSlot2

  # Save and restore view angles
  alias saveAngles var\_yaw\yaw var\_pitch\pitch
  alias restoreAngles setYaw\_yaw setPitch\_pitch
  ```

## Commands Reference

| Command                          | Purpose                                                                                                                                                                                                                                                                                                                     | Example                                                   |
|----------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------|
| `/alias <name> <definition>`     | Create a custom alias.                                                                                                                                                                                                                                                                                                      | `/alias myAlias +jump wait\1 -jump`                       |
| `/bind <key> <definition>`       | Bind a key to a sequence of aliases by definition of this command or an existing alias.For each definition split by divide mark(things inside double quotes would still be a same block) starts with + or -, it will create an opposite alias. For the 1st eg, it also bind -forward and +back to release of keyboard key g | `/bind g +forward wait\10 -back   OR   /bind n dropStack` |
| `/bindByAliasName <key> <alias>` | Bind a key to an existing alias.                                                                                                                                                                                                                                                                                            | `/bindByAliasName mouse5 +fly`                            |
| `/unbind <key>`                  | Remove a key binding.                                                                                                                                                                                                                                                                                                       | `/unbind mouse5`                                          |
| `/reloadCFG`                     | Reload config from file.                                                                                                                                                                                                                                                                                                    | `/reloadCFG`                                              |
| `/var <name> <source>`           | Create/update a variable. Sources: `hotbarSlot`, `itemsOfSlot0-9`, `pitch`, `yaw`, or a number.                                                                                                                                                                                                                             | `/var mySlot hotbarSlot`, `/var angle pitch`              |
| `/unloadCFGAliases`              | Remove all aliases loaded from config.                                                                                                                                                                                                                                                                                      | `/unloadCFGAliases`                                       |
| `/unloadCFGBinds`                | Remove all keybindings loaded from config.                                                                                                                                                                                                                                                                                  | `/unloadCFGBinds`                                         |
| `/unloadCFGVars`                 | Remove all variables loaded from config.                                                                                                                                                                                                                                                                                    | `/unloadCFGVars`                                          |
| `/unloadCFGAll`                  | Remove all aliases, binds, and variables loaded from config.                                                                                                                                                                                                                                                                | `/unloadCFGAll`                                           |

## Notes

- **Compatibility**: Works with most Fabric mods; may conflict with mods that modify key handling or inventory
  mechanics.
- **Minecraft Version**: Requires Minecraft 1.21+ (Yarn mappings) or 26.x (Mojang mappings). Check the release page
  for version-specific builds (filenames include the MC version).
- **Variables**: Supports integer and floating-point values. Numeric aliases (`yaw`, `pitch`, `setYaw`, `setPitch`,
  `slot`, `swapSlot`, `wait`, `setPerspective`) accept variable names in place of raw numbers.
- **Safety**: Avoid excessive automation on servers with anti-cheat systems (some actions may be flagged).

## Contributing

Contributions are welcome! Feel free to open issues for bugs/feature requests or submit pull requests with improvements.

## License

This mod is licensed under the [Creative Commons Zero v1.0 Universal](LICENSE).
