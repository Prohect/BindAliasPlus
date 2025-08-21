# BindAliasPlus

A Minecraft Fabric client mod that allows creating custom aliases and key bindings to automate complex in-game actions with simple key presses.

## Overview
BindAliasPlus enhances your Minecraft gameplay by letting you define custom aliases for sequences of actions and bind them to keys. Whether you need to quickly swap inventory slots, automate elytra flight, or chain multiple actions (like using a bow or placing blocks), this mod simplifies repetitive tasks through configurable aliases and key bindings.


## Features
- **Custom Aliases**: Create reusable aliases for single or multiple in-game actions (e.g., swap items, use abilities, move).
- **Key Bindings**: Bind aliases to keys, with support for separate actions on key press and release.
- **Built-in Aliases**: Predefined aliases for common actions (e.g., `swapSlot`, `wait`, `use`, `attack`).
- **Command System**: Intuitive commands to manage aliases and bindings (e.g., `/alias`, `/bind`, `/unbind`).
- **Config Persistence**: Saves aliases and bindings in a config file, loaded automatically when joining servers.
- **Chained Actions**: Combine aliases to create complex sequences (e.g., equip elytra → use firework → fly).


## Installation
1. Ensure you have [Fabric Loader](https://fabricmc.net/use/) installed for your Minecraft version.
2. Download the latest `bind-alias-plus-*.*.*.jar` from the [releases page](https://github.com/prohect/BindAliasPlus/releases).
3. Place the JAR file in your Minecraft `mods` folder.
4. Launch Minecraft with the Fabric loader.


## Usage

### Core Concepts
- **Alias**: A custom or built-in action (or sequence of actions) that can be executed.
- **Key Binding**: A link between a physical key (e.g., `mouse5`, `keyboard.g`) and an alias (or two aliases: one for press, one for release).



### Built-in Aliases
BindAliasPlus includes prebuilt aliases for common actions. They are divided into **aliases with arguments** and **aliases without arguments**.

#### Aliases with Arguments
    note:slots defined by mc:
    1-9 -> hotbarSlots,
    10-36 -> slots inside inventory,10-19 first row
    37-40 -> equipments, 37 is feet, 40 is head
    41 -> the second hand,
- `swapSlot\slot1\slot2`: Swaps items between two inventory slots (e.g., `swapSlot\10\19` swaps slot 10 and 19).
- `wait\ticks`: Pauses execution for `ticks` (20 ticks = 1 second; e.g., `wait\20` for 1 second).
- `use\0`/`use\1`: Stops/Starts using the held item (e.g., `use\1` to hold right-click).
- `attack\0`/`attack\1`: Stops/Starts attacking (e.g., `attack\1` to hold left-click).
- `slot\slotNumber`: Switches to a hotbar slot (1-9; e.g., `slot\3` for hotbar slot 3).
- `yaw\degrees`/`pitch\degrees`: Adjusts player rotation (e.g., `yaw\90` to turn 90° right).
- `setYaw\degrees`/`setPitch\degrees`: Sets player rotation to a specific value (e.g., `setYaw\0` for north).

#### Aliases without Arguments
- `+jump`/`-jump`: Starts/Stops jumping.
- `+forward`/`-forward`: Starts/Stops moving forward.
- `swapHand`: Swaps items between main and offhand.
- `drop`: Drops one item; `dropStack`: Drops the entire stack.
- `reloadCFG`: Reloads the config file.


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
- **Manual Edit**: You can directly edit the config file to add/modify aliases/bindings (use the same syntax as in-game commands).


## Commands Reference
| Command | Purpose | Example |
|---------|---------|---------|
| `/alias <name> <definition>` | Create a custom alias. | `/alias myAlias +jump wait\1 -jump` |
| `/bind <key> <definition>` | Bind a key to a sequence of aliases by definition of this command or an existing alias. | `/bind g +forward wait\10 -forward   OR   /bind n +attack +forward` |
| `/bindByAliasName <key> <alias>` | Bind a key to an existing alias. | `/bindByAliasName mouse5 +fly` |
| `/unbind <key>` | Remove a key binding. | `/unbind mouse5` |
| `/reloadCFG` | Reload config from file. | `/reloadCFG` |


## Notes
- **Compatibility**: Works with most Fabric mods; may conflict with mods that modify key handling or inventory mechanics.
- **Minecraft Version**: Requires Minecraft 1.19+ (check releases for version-specific builds).
- **Safety**: Avoid excessive automation on servers with anti-cheat systems (some actions may be flagged).


## Contributing
Contributions are welcome! Feel free to open issues for bugs/feature requests or submit pull requests with improvements.


## License
This mod is licensed under the [MIT License](LICENSE).
