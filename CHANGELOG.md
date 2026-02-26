# Changelog

All notable changes to BindAliasPlus will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.1.0] - 2025-01-26

### Added
- **Variable System** - Store and retrieve integer values dynamically during gameplay
  - New `/var <varName> <source>` command to create variables
  - New `var\name\source` builtin alias for use in alias definitions
  - Variable names cannot start with numbers (validation enforced)
  - Config file support for `var` commands

- **Variable Sources:**
  - `hotbarSlot` / `selectedSlot` - Capture current hotbar slot (1-9)
  - `itemsOfSlot0` - Read item count from offhand slot
  - `itemsOfSlot1-9` - Read item count from hotbar slots
  - Direct integers (1-41) - Store literal values

- **Enhanced Aliases:**
  - `slot\n` now accepts variable names in addition to numbers
  - `swapSlot\a\b` now accepts variable names for both arguments
  - Variables automatically resolved to integers when used

- **Documentation:**
  - Comprehensive README updates with variable examples
  - Developer documentation in DEVELOP.md
  - Quick start guide (VARIABLE_QUICKSTART.md)
  - Example configuration with variables (example-with-variables.cfg)
  - Complete feature summaries and technical documentation

### Use Cases
- Save and restore hotbar slots
- Dynamic weapon/tool switching
- Track consumable item counts (arrows, food, potions)
- Monitor offhand inventory
- Create resource-aware macros
- Build state-switching systems

### Examples
```
# Save current slot
/var savedSlot hotbarSlot
/alias restore slot\savedSlot

# Track arrow count
/var arrowCount itemsOfSlot2
/alias checkArrows var\arrowCount\itemsOfSlot2 log\arrowCount

# Dynamic weapon switching
/var sword 1
/var bow 2
/alias toSword slot\sword
/alias toBow slot\bow
```

## [1.0.4] - Previous Release

### Features
- Alias system with user-defined macros
- Key/mouse button binding with press/release behavior
- Built-in aliases for movement, combat, inventory management
- Slot swapping and hotbar selection
- Wait/delay functionality with tick-based scheduling
- Camera angle manipulation (yaw/pitch)
- Configuration file support
- Silent mode for suppressing feedback messages
- Toggle and press-and-hold binding patterns
- Nested alias definitions with greedy string support
- Elytra and equipment management helpers

### Built-in Aliases
- Movement: `+forward`, `+back`, `+left`, `+right`, `+jump`, `+sneak`, `+sprint`
- Combat: `+attack`, `+use`, `drop`, `dropStack`
- Inventory: `slot`, `swapSlot`, `swapHand`
- Camera: `yaw`, `pitch`, `setYaw`, `setPitch`, `cyclePerspective`
- Utility: `wait`, `log`, `say`, `sendCommand`
- Meta: `alias`, `bind`, `unbind`, `reloadCFG`, `+silent`

### Commands
- `/alias <name> <definition>` - Create user aliases
- `/bind <key> <definition>` - Bind keys to aliases
- `/bindByAliasName <key> <aliasName>` - Direct alias binding
- `/unbind <key>` - Remove key bindings
- `/reloadCFG` - Reload configuration file

---

## Version History Summary

- **1.1.0** - Variable system with dynamic value storage and item count tracking
- **1.0.4** - Initial public release with full alias and binding system

---

## Upgrade Notes

### Upgrading to 1.1.0
- Fully backward compatible - no breaking changes
- All existing configs and aliases work unchanged
- New variable features are opt-in
- Variables persist only during game session (cleared on quit)
- To use variables on startup, add `var` commands to config file

---

## Future Roadmap

### Potential Features
- Extended slot range (itemsOfSlot10-41 for full inventory)
- Item type detection and conditional logic
- Durability tracking for tools/armor
- Mathematical operations on variables (add, multiply, etc)
- Variable persistence between sessions
- Conditional alias execution (if/else logic)
- Additional game state sources (health, hunger, coordinates)

---

## Links

- **Modrinth**: https://modrinth.com/mod/bind-alias-plus
- **GitHub**: https://github.com/Prohect/BindAliasPlus
- **Issues**: https://github.com/Prohect/BindAliasPlus/issues

---

## License

[CC0-1.0](LICENSE)