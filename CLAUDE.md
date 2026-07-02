BindAliasPlus\mc-decompile-sources is in .git\info\exclude,
search inside:
```bash
cd mc-decompile-sources/<branch>/ && grep <args>
# cd to a more specific path if you understand the file tree structure
```

## test by gradle runTestClient

Harness the autoLoaded cfg file for automatic crash detection.
Add test aliases for new/changed code paths — agent's call.
Functional correctness must be confirmed by the user.

## release workflow

1. **Develop** → build + runTestClient → commit
2. **Sync** → cherry-pick to each active branch → build + runTestClient per branch
3. **⏸ STOP** — wait for user confirmation before bumping
4. **Bump** → `mod_version` in `gradle.properties` + CHANGELOG → commit
5. **Collect** → build each branch, copy JAR to `release/`
6. **Verify** → `unzip -p <jar> fabric.mod.json`
7. **Release** → `git push` all branches + `gh release create`

## active branches

| Branch | MC | Mappings |
|---|---|---|
| `26.1.2_26.2` | 26.2 | Mojang |
| `26.1_26.1.1` | 26.1.1 | Mojang |
| `1.21.9_1.21.11` | 1.21.11 | Yarn |
| `1.21_1.21.8` | 1.21.8 | Yarn |

## per-branch mapping (Mojang → Yarn)

| Mojang (26.x) | Yarn (1.21.x) |
|---|---|
| `Minecraft` | `MinecraftClient` |
| `KeyMapping` / `setDown` / `clickCount` | `KeyBinding` / `setPressed` / `timesPressed` |
| `hasControlDown()` | `isCtrlPressed()` (1.21.9+) / `Screen.hasControlDown()` (1.21.8) |
| `AbstractContainerScreen` | `HandledScreen` |
| `hoveredSlot` / `slotClicked` | `focusedSlot` / `onMouseClick` |
| `ContainerInput.THROW` | `SlotActionType.THROW` |
| `onClose()` | `close()` |
| `KeyboardHandler` / `MouseHandler` | `Keyboard` / `Mouse` |
| `KeyEvent` / `MouseButtonInfo` | `KeyInput` / `MouseInput` (1.21.9+), `int...` (1.21.8) |
| accesswidener namespace `official` | accesswidener namespace `named` |
