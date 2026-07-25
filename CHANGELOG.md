# Changelog

All notable changes to BindAliasPlus will be documented in this file.

## [1.5.3] - 2026-07-25

### Fixed

- **`freeCursor` mining survives screen opens and focus loss** — overrides `isMouseGrabbed()` / `isCursorLocked()` to always return `true` while `freeCursor` is active, so the vanilla hold-to-mine guard (`continueAttack` / `handleBlockBreaking`) no longer resets block breaking progress when a screen opens or the game window loses focus.

## [1.5.2] - 2026-07-25

### Fixed

- **`freeCursor` no longer breaks hold-to-mine** — the game now keeps its logical "cursor grabbed" state while only the OS-level grab is skipped, so continuous block breaking (`continueAttack`) works with a free host cursor. Physical mouse movement still never rotates the camera while active, and disabling `freeCursor` cleanly restores the real grab.

## [1.5.1] - 2026-07-25

### Added

- **Player position in `getScreenshot`** — screenshot response now includes `x`, `y`, `z`, `yaw`, `pitch` fields (2 decimal places), captured on the same main-thread roundtrip as the screenshot trigger for zero extra latency

## [1.5.0] - 2026-07-25

### Added

- **`getLogDiff` MCP tool** — game log diff for AI agents:
  - Captures mod log output (`log\...` alias, CFG autoload, variable setup) via scoped Log4j appender on `"bind-alias-plus"` logger
  - Captures chat messages (player chat, system messages, command feedback) via `ChatComponentMixin`
  - Persistent ring buffer (200 msg) with diff cursor tracking — returns only new messages since last call
  - Log capture starts at world join, diff resets on each join to skip startup noise
  - `GET /logDiff` endpoint in `McpHttpServer`

## [1.4.0] - 2026-07-25

### Added

- **MCP HTTP server** (`McpHttpServer.java`) — in-game HTTP API for AI agent control:
  - `GET /state` — player position, health, held item, dimension, open container contents (compressed)
  - `GET /screenshot` — in-memory PNG screenshot via native `Screenshot.grab()` (no chat spam)
  - `POST /runAlias` — execute alias chains remotely (pre-checks alias existence, returns error for unknowns)
  - `POST /defineAlias` — define new aliases via API
  - `GET /readCFG` / `POST /writeCFG` — read/write `bind-alias-plus.cfg` remotely
- **`swapSlot` expanded to any container screen** — works in chests, crafting tables, furnaces, anvils, enchanting tables, and all other `AbstractContainerScreen` subtypes.
  - New `cN` slot syntax (1-based index into the open menu's slot list) alongside existing player slots 1–41 and variables.
  - Hotbar/offhand-addressable slots use a single SWAP click; other pairs use a guarded PICKUP sequence.
  - Take-only result slots (crafting output, furnace output, anvil output) behave as "take result", enabling craft/forge/enchant automation from alias chains.
- **MCP `/state` container compression** — open container view with occupied slots as `{index,item,count}`, empty inventory slots as `1-41` ranges, and container slots as an ASCII grid map with per-cell `c`-indices. Sections over 6000 chars are truncated with a screenshot hint.
- **MCP chat capture** — `ChatComponentMixin` records incoming chat messages for agent consumption.
- **MCP screenshot capture** — `NativeImageMixin` enables in-memory PNG screenshots without file I/O.
- **Predefined (protected) user aliases** — bundled aliases that ship with the mod and cannot be overwritten by users.

### Fixed

- **`toggleInventory`** — corrected builtin name casing (was registered as `toggleinventory`).

### Changed

- **MCP `/state`** — compressed output format for reduced token usage by AI agents.

### Notes

- **`swapSlot` container slots (`cN`)**: When swapping a container slot with a hotbar/offhand slot, vanilla's `SWAP` click is used. If the container slot is input-restricted (furnace fuel only accepts fuel, result slots accept nothing, etc.) and the hotbar item is incompatible, the entire swap is silently rejected by the server — neither item moves. Use an empty hotbar slot or the PICKUP fallback (swap with a non-hotbar inventory slot `10`-`36`) to take items from restricted slots. Swapping with an empty slot always works.

## [1.3.5] - 2026-07-24

### Added

- **`+screenshot` / `-screenshot` alias** — captures screenshots via the vanilla screenshot key.
- **`+playerList` / `-playerList` alias** — holds/releases the Tab key to show the online-player overlay.
- **`+freeCursor` / `-freeCursor` alias** — cancels mouse grab/lock for unrestricted cursor movement (hidden from suggestions).
- **`esc` / `closeScreen` alias** — closes the current screen (`\0`) or toggles pause (`\1`).
- **`+advancements` / `-advancements` alias** — holds/releases the advancements screen key.
- **`+debugOverlay` / `-debugOverlay` alias** — toggles the F3 debug overlay.

### Changed

- **Screen tracking** moved from `GuiMixin` to `MinecraftClientMixin.tick` for cross-version compatibility.
- **`fabric.mod.json`** version ranges widened for better compatibility.

### Fixed

- **`SwapSlotAlias`** `getSelectedSlot()` → `selectedSlot` field on 1.21–1.21.8 for compatibility.

## [1.3.4] - 2026-07-03

### Fixed

- **Text input guards** — `swapHand`, `use`, and `pickItem` aliases now
  cancel when the player is typing in chat, signs, books, or command
  blocks, preventing accidental game actions while typing.

## [1.3.3] - 2026-07-03

### Added

- **`toggleinventory` alias** — toggles the player inventory open/closed.
  Opens the inventory screen if no screen is active; closes any container
  screen (chest, furnace, etc.) if one is open.

## [1.3.2] - 2026-07-02

### Fixed

- **`-openInventory`** now closes any container screen (chests, furnaces,
  etc.), not just the player inventory or creative inventory screens.

### Changed

- **CI** — added GitHub Actions workflow to auto-publish releases to Modrinth.
- Removed TODO.md from the repository.

## [1.3.1] - 2026-07-01

### Added

- **Continuous container drop** — `+drop` now continuously drops items in
  inventory/container screens while held (matching vanilla GLFW
  key-repeat → `keyPressed` → `slotClicked` behavior).
- **Continuous 3D-game drop** — held `+drop` continuously drops in the
  3D game via tick-driven `clickCount` increments.
- **Initial delay before continuous drops** (3 ticks) to match the OS
  key-repeat gap vanilla relies on, preventing accidental double-drops
  from quick taps.
- **Cached screen reference** — `BindAliasPlusClient.currentScreen` is
  now updated by `GuiMixin` on every screen change, replacing expensive
  `McScreenHelper.getCurrentScreen()` reflection calls everywhere.
- **Screen-type helper methods** on `Alias` — `isUnderTextInputScreen()`,
  `isUnderAnyScreen()`, `isInContainerScreen()`, `isInInventoryScreen()`,
  `isInCreativeInventoryScreen()`.

### Removed

- **`lockCursorBlackList`** — removed entirely (only contained `dropAlias`;
  `DropAlias.reapplyToGameKeyMapping()` override handles the concern now).
- **`AtomicBoolean isUnderTextInputScreen` / `isUnderAnyScreen`** — replaced
  with static methods derived from the cached screen reference.

### Changed

- **`GuiMixin` simplified** to a single line (`BindAliasPlusClient.currentScreen = screen`).
- **Access widener** extended with `AbstractContainerScreen.hoveredSlot`
  and `slotClicked()` for container drop support.

## [1.3.0] - 2026-06-30

### Added

- **`reapply` alias** — new `reapply\action` builtin to manually re-assert a
  held-down boolean alias at the end of a UserAlias sequence (e.g., after
  a screen transition).
- **`pickItem` alias** — triggers vanilla pick-block behavior via key mapping.
- **`openInventory` alias** — opens/closes the inventory screen via
  `+openInventory`/`-openInventory` (boolean alias pattern).

### Changed

- **`drop`/`dropStack` → `+drop`/`-drop`** — drop alias now uses the
  vanilla key-mapping system (`KeyMapping.setDown`) instead of directly
  calling `player.drop()`. Renamed to follow the `+`/`-` press/release
  convention used by other boolean aliases.

### Removed

- **`KeyMappingMixin`** — the automatic reapply-after-`releaseAll` mixin
  is removed. Use the explicit `reapply\action` alias instead for more
  predictable behavior.

---

## [1.2.8] - 2026-06-29

### Added

- **Immediate reapply after `releaseAll`** — `SneakAlias` (and any
  `BuiltinAliasWithBooleanArgs` that opts in) now reapplies its key state
  instantly when the game calls `KeyBinding.unpressAll()`, preventing a
  1-tick gap that could cause the player to fall when opening inventory
  while sneaking on a ledge.

### Fixed

- **Lock alias GL error** — `LockAlias` no longer uses `Integer.MIN_VALUE`
  as the placeholder key code, which was outside GLFW's valid range and
  spammed `GLFW_INVALID_VALUE (65539): Invalid key -2147483648` to the
  render log. Now uses `InputUtil.UNKNOWN_KEY` (GLFW_KEY_UNKNOWN = -1)
  which is handled gracefully by both GLFW and Minecraft.

### Changed

- **Proxy settings** moved from project `gradle.properties` to
  `~/.gradle/gradle.properties` so they are not committed to the repository.

---

## [1.2.7] - 2026-06-27

### Changed

- **Lock aliases renamed** — `+lock`/`-lock` → `+lockKey`/`-lockKey` for clarity.
- **Action suggestions prefixed** — vanilla game keys now suggested as
  `gameKey:attack`, `gameKey:forward`, etc. to avoid naming conflicts
  with custom UserAliases.

### Added

- **Custom alias locking** — `+lockKey\myAlias` now locks all physical
  keys bound to any UserAlias, preventing keyboard/mouse input from
  triggering it while still allowing `/runAlias` to invoke it.
- `+lockKey`/`-lockKey` unified: if the argument is a known `gameKey:*`
  action, it locks the vanilla key; otherwise it locks by alias name.

---

## [1.2.6] - 2026-06-27

### Added

- `localSay` alias — displays chat message on the local client only,
  without sending it to the server. Useful for testing, notifications,
  and debug output.

### Fixed

- `SwapSlotAlias`: guard against opening the inventory screen when
  another screen is already active, preventing unexpected behavior.

---

## [1.2.5] - 2026-06-27

### Screen state tracking

- Added `isUnderTextInputScreen` and `isUnderAnyScreen` atomic flags synced
  via `GuiMixin` (or `MinecraftClientMixin` on 1.21.x) hooking into
  `setScreen()`. Eliminates scattered `instanceof` checks across mixins.

### RP — Release-Press Reapply

- When the game releases all keys (opening/closing a screen, losing focus),
  held builtin aliases are automatically re-applied so your key state stays
  in sync. Driven by `KeyMappingMixin` hooking `KeyBinding.unpressAll()`.

### Auto-naming

- Builtin aliases now derive their registration name from constructors.
  `putToAliasesWithArgs()` no longer requires an explicit name string.

### Backported to 1.21.x

- All features (screen atomics, RP, auto-naming) synced to 1.21.0 through
  1.21.11. Six branches now cover the full MC version range:
  1.21–1.21.3, 1.21.4, 1.21.5–1.21.8, 1.21.9–1.21.11, 26.1–26.1.1, 26.1.2–26.2.

### Bug fixes

- `SwapSlotAlias`: fixed slot index lookup using `getContainerSlot()` / `getIndex()`
  instead of raw `slot.index` field.
- `UnloadCFGBindsAlias`: cleaned up unused imports and formatting.

---

## [1.2.4] - 2026-06-23

### MC 26.x Mojang mappings migration

- Migrated to Mojang official mappings for MC 26.1+. All class, method,
  and field names updated (`sendChatCommand` → `sendCommand`,
  `networkHandler` → `connection`, etc.).
- Added `McScreenHelper` for cross-version screen API compatibility
  between MC 26.1.x (field-based) and MC 26.2+ (Gui-based).
- New branch `26.1_26.1.1` for MC 26.1.x, `26.1.2_26.2` for MC 26.2.

---

## [1.2.3] - 2026-06-23

### Lock aliases — flat → arg-based

- Replaced 18 individual `+lock:attack`, `-lock:attack`, … aliases with
  compact `+lock\<action>` / `-lock\<action>` arg-based form.
- Command suggestions offer action types (`attack`, `use`, `forward`, …)
  after `+lock\` or `-lock\`.

### Variable name suggestions

- Aliases that accept numeric args (`slot`, `swapSlot`, `wait`, `yaw`,
  `pitch`, `setYaw`, `setPitch`) now suggest variable names from `VarAlias`
  during command completion.
- Integer-only aliases filter out `Double`-typed variables.

### Internal

- `SlotAlias` and `SwapSlotAlias` extend `BuiltinAliasWithIntegerArgs`.
- `LockAlias_OnLock` / `LockAlias_Unlock` split from `LockAlias`.

---

## [1.2.2] - 2026-06-22

### Lock aliases (new)

- Lock/unlock game actions to prevent physical key input from interfering
  with alias sequences. Supported actions: attack, use, forward, back,
  left, right, jump, sneak, sprint.
- Usage: `+lock:attack` / `-lock:attack` (flat aliases).

### Cycle / set perspective (new)

- `cyclePerspective` alias toggles through camera perspectives.
- `FPS`, `TPS`, `TPS2` aliases for direct perspective switching.

### Shutdown (new)

- `shutdown` alias to cleanly stop the game client from within an alias
  sequence.

### Var system enhancements

- `var\varName\pitch` and `var\varName\yaw` sources.
- Variable references supported in all numeric-arg aliases (`slot`,
  `swapSlot`, `yaw`, `pitch`, `setYaw`, `setPitch`, `wait`).

### Command suggestions

- Builtin aliases now use `builtin` prefix internally, hidden from
  command suggestions to keep the suggestion list clean.
- `runAlias` and `shutdown` also hidden from suggestions.

---

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

### Changed

- Enhanced `KeyBindingPlus` record with `fromAutoload` field
- Enhanced `UserAlias` class with `fromAutoload` tracking
- Enhanced `VarAlias` with `AUTOLOADED_VARIABLES` set for tracking

---

## Links

- **Modrinth**: https://modrinth.com/mod/bind-alias-plus
- **GitHub**: https://github.com/Prohect/BindAliasPlus
- **Issues**: https://github.com/Prohect/BindAliasPlus/issues

---

## License

[CC0-1.0](LICENSE)
