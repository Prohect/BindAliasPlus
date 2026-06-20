# Changelog

All notable changes to BindAliasPlus will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added
- **Lock Aliases** - Temporarily lock game actions to prevent user input interference during alias sequences
  - `+lock:attack` / `-lock:attack` — lock/unlock the attack key
  - `+lock:use` / `-lock:use` — lock/unlock the use/right-click key
  - `+lock:forward`, `+lock:back`, `+lock:left`, `+lock:right` — lock/unlock movement keys
  - `+lock:jump`, `+lock:sneak`, `+lock:sprint` — lock/unlock other action keys
  - Locking temporarily saves the original key binding and sets it to an unused value, then restores on unlock

## [1.1.1] - 2025-01-26

### Added
- **Autoload Tracking System** - Distinguish between config-loaded and runtime-created items
  - All aliases, keybindings, and variables now track their source (autoload vs runtime)
  - Items created during `loadCFG()` are marked as `fromAutoload=true`
  - Items created via in-game commands are marked as `fromAutoload=false`

- **New Built-in Aliases:**
  - `unloadCFGAliases` - Remove all user aliases loaded from config file
  - `unloadCFGBinds` - Remove all keybindings loaded from config file
  - `unloadCFGVars` - Remove all variables loaded from config file
  - `unloadCFGAll` - Remove all autoloaded aliases, bindings, and variables at once

- **New Commands:**
  - `/unloadCFGAliases` - Unload config file aliases only
  - `/unloadCFGBinds` - Unload config file keybindings only
  - `/unloadCFGVars` - Unload config file variables only
  - `/unloadCFGAll` - Unload all config file items in one command

### Changed
- Enhanced `KeyBindingPlus` record with `fromAutoload` field
- Enhanced `UserAlias` class with `fromAutoload` tracking
- Enhanced `VarAlias` with `AUTOLOADED_VARIABLES` set for tracking
- Updated all command executors to support autoload parameter
- Convenience constructors added for backward compatibility

### Use Cases
- **Quick testing:** Unload config and test new setups without restart
- **Profile switching:** Unload current config, load different one
- **Safe experimentation:** Test config changes, unload if bad, keep runtime items
- **Debugging:** Isolate config issues by unloading and testing individually

### Examples
```
# Load config at startup (automatic)
# Config creates: aliases, bindings, variables

# During gameplay, create runtime items
/alias testAlias +forward wait\20 -forward
/bind g testAlias
/var mySlot hotbarSlot

# Unload only config file items
/unloadCFGAll

# Your runtime-created items are preserved!
# testAlias, g binding, and mySlot variable still exist

# Reload config for fresh start
/reloadCFG

# Or unload specific categories
/unloadCFGAliases  # Only remove config aliases
/unloadCFGBinds    # Only remove config bindings
/unloadCFGVars     # Only remove config variables
```

### Technical Details
- `KeyBindingPlus` now includes `boolean fromAutoload` field
- `UserAlias` tracks origin with `fromAutoload` field and getter/setter
- `VarAlias.AUTOLOADED_VARIABLES` HashSet tracks autoloaded variable names
- All unload operations preserve runtime-created items
- Unload commands work seamlessly with existing reload functionality

---

## Links

- **Modrinth**: https://modrinth.com/mod/bind-alias-plus
- **GitHub**: https://github.com/Prohect/BindAliasPlus
- **Issues**: https://github.com/Prohect/BindAliasPlus/issues

---

## License

[CC0-1.0](LICENSE)