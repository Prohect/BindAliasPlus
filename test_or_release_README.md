## test by gradle runTestClient

Harness the autoLoaded(loaded at `net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents.JOIN`) cfg(`run/config/bind-alias-plus.cfg` via `com.github.prohect.BindAliasPlusClient.loadCFG()`) for automatic crash/error detection.
**Add test aliases for new code paths, make sure the log for new test aliases is in the log**.
Functional correctness must be confirmed by the user.
**Never test with curl or raw HTTP — always use the MCP tools (getState, getLogDiff, runAlias, etc.).**

## release workflow

1. **Develop** → build + runTestClient → **Confirm by User** → commit
2. **Sync** → the post-commit hook auto-syncs the commit record and `.git_sync_across_active_branches` files to all active branches. **Everything else (source code, build scripts, etc.) must be manually merged** per branch — cherry-pick or re-apply changes, then build + runTestClient per branch.
3. **STOP** — wait for user confirmation before bumping
4. **Bump** → `mod_version` in `gradle.properties` + CHANGELOG → commit
5. **Collect** → build each branch, copy JAR to `release/`
6. **Verify** → `unzip -p <jar> fabric.mod.json` — check the full unzipped JSON, not just grep. Verify `version`, `depends.minecraft` range, and `entrypoints` match the target branch.
7. **Release** → `git push` all branches + `gh release create`

## active branches

| Branch           | MC      | Mappings |
| ---------------- | ------- | -------- |
| `26.1.2_26.2`    | 26.2    | Mojang   |
| `26.1_26.1.1`    | 26.1.1  | Mojang   |
| `1.21.9_1.21.11` | 1.21.11 | Yarn     |
| `1.21_1.21.8`    | 1.21.8  | Yarn     |

## per-branch mapping (Mojang → Yarn)

| Mojang (26.x)                           | Yarn (1.21.x)                                                    |
| --------------------------------------- | ---------------------------------------------------------------- |
| `Minecraft`                             | `MinecraftClient`                                                |
| `KeyMapping` / `setDown` / `clickCount` | `KeyBinding` / `setPressed` / `timesPressed`                     |
| `hasControlDown()`                      | `isCtrlPressed()` (1.21.9+) / `Screen.hasControlDown()` (1.21.8) |
| `AbstractContainerScreen`               | `HandledScreen`                                                  |
| `hoveredSlot` / `slotClicked`           | `focusedSlot` / `onMouseClick`                                   |
| `ContainerInput.THROW`                  | `SlotActionType.THROW`                                           |
| `onClose()`                             | `close()`                                                        |
| `KeyboardHandler` / `MouseHandler`      | `Keyboard` / `Mouse`                                             |
| `KeyEvent` / `MouseButtonInfo`          | `KeyInput` / `MouseInput` (1.21.9+), `int...` (1.21.8)           |
| accesswidener namespace `official`      | accesswidener namespace `named`                                  |
