# BindAliasPlus

A Minecraft Fabric client mod that allows creating custom aliases and key bindings to automate complex in-game actions
with simple key presses.

## Overview

BindAliasPlus enhances your Minecraft gameplay by letting you define custom aliases for sequences of actions and bind
them to keys. Whether you need to quickly swap inventory slots, automate elytra flight, or chain multiple actions (like
using a bow or placing blocks), this mod simplifies repetitive tasks through configurable aliases and key bindings.

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
   the [releases page](https://github.com/prohect/BindAliasPlus/releases).
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

| Alias                  | Description                                                                               | Example                                                                        |
|------------------------|-------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------|
| `log`                  | Logs a message to the game console (for debugging).                                       | `log\Hello World`                                                              |
| `slot\slotNumber`      | Switches to a specific hotbar slot (1-9).                                                 | `slot\3` (switches to hotbar slot 3)                                           |
| `swapSlot\slot1\slot2` | Swaps items between two inventory slots.                                                  | `swapSlot\10\39` (swaps inventory slot 10 with chestplate slot)                |
| `swapSlot\slot1`       | Swaps items between the currently held hotbar slot (main hand) and the specified `slot1`. | `swapSlot\19` (swaps current hotbar slot with inventory slot 19)               |
| `wait\ticks`           | Pauses execution for a specified number of ticks (20 ticks = 1 second).                   | `wait\20` (waits 1 second)                                                     |
| `yaw\degrees`          | Adjusts player yaw (horizontal rotation) by a relative degree value.                      | `yaw\90` (turns 90° right)                                                     |
| `pitch\degrees`        | Adjusts player pitch (vertical rotation) by a relative degree value.                      | `pitch\-30` (looks 30° down)                                                   |
| `setYaw\degrees`       | Sets player yaw to an absolute degree value (0 = north, 90 = east).                       | `setYaw\180` (faces south)                                                     |
| `setPitch\degrees`     | Sets player pitch to an absolute degree value (-90 = straight up, 90 = straight down).    | `setPitch\0` (looks straight ahead)                                            |
| `alias\args`           | almost same as command alias, except u need to cover args with double quotes.             | `alias\"meow say\nya~"` (create of replace an alias)                           |
| `bind\args`            | almost same as command bind, except u need to cover args with double quotes.              | `bind\"m meow wait\0 +fly"` (create of replace a bind)                         |
| `unbind\keyName`       | almost same as command unbind.                                                            | `unbind\m` (unbind binds on a key)                                             |
| `say\string`           | say a chat message.                                                                       | `say\"How old r u?"` (send a chat message that is "how old r u?")              |
| `sendCommand\command`  | send a command.                                                                           | `sendCommand\"gamemode creative"` (send a command that is "gamemode creative") |

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
| `reloadCFG` | —                  | Reloads the config file (applies changes without restarting). |

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

# Define -fly (on key release): re-equip what u equiped before
/alias -fly equipElytra swapSlot\19

# Bind mouse button 5 to +fly/-fly
/bind mouse5 +fly
```

#### 2. Quick Bow Usage

Quickly swap to a bow, use it, and swap back:
(bow wont need a hotbar any more, also try this for Fortune and SilkTouch pickaxe or enderpearl)

```bash
# Define +bow (on press): swap to bow (slot 11) → start using
/alias +bow swapSlot\11 +use

# Define -bow (on release): stop using → swap back
/alias -bow -use swapSlot\11

# Bind mouse button 4 to +bow/-bow
/bind mouse4 +bow
```

## Configuration

- **Config File**: At `config/bind-alias-plus.cfg`. Automatically created if there is not one.
- **Auto-Load**: Aliases and bindings in the config file are loaded automatically when joining a server.
- **Manual Edit**: You can directly edit the config file to add/modify aliases/bindings (use the same syntax as in-game
  commands).  
  **Example Config Content**:
  ```
  # BindAliasPlus config example
  # Define aliases for elytra equipment
  alias +equipElytra swapSlot\10\39
  alias -equipElytra swapSlot\10\39
  # Define aliases for fireworks handling
  alias +holdFireworks swapSlot\26
  alias -holdFireworks swapSlot\26
  # Define a simple jump action
  alias jump +jump wait\1 -jump
  # Define fly action sequence (on press)
  alias +fly +equipElytra jump wait\1 jump +holdFireworks +use -use
  # Define fly action sequence (on release)
  alias -fly -equipElytra -holdFireworks
  # Bind mouse5 to the +fly/-fly aliases
  bind mouse5 +fly
  ```

## Commands Reference

| Command                          | Purpose                                                                                 | Example                                                      |
|----------------------------------|-----------------------------------------------------------------------------------------|--------------------------------------------------------------|
| `/alias <name> <definition>`     | Create a custom alias.                                                                  | `/alias myAlias +jump wait\1 -jump`                          |
| `/bind <key> <definition>`       | Bind a key to a sequence of aliases by definition of this command or an existing alias. | `/bind g +forward wait\10 -forward   OR   /bind n dropStack` |
| `/bindByAliasName <key> <alias>` | Bind a key to an existing alias.                                                        | `/bindByAliasName mouse5 +fly`                               |
| `/unbind <key>`                  | Remove a key binding.                                                                   | `/unbind mouse5`                                             |
| `/reloadCFG`                     | Reload config from file.                                                                | `/reloadCFG`                                                 |

## Notes

- **Compatibility**: Works with most Fabric mods; may conflict with mods that modify key handling or inventory
  mechanics.
- **Minecraft Version**: Requires Minecraft 1.19+ (check releases for version-specific builds).
- **Safety**: Avoid excessive automation on servers with anti-cheat systems (some actions may be flagged).

## Contributing

Contributions are welcome! Feel free to open issues for bugs/feature requests or submit pull requests with improvements.

## License

This mod is licensed under the [Creative Commons Zero v1.0 Universal](LICENSE).
