## test by gradle runTestClient

Harness the autoLoaded(loaded at `net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents.JOIN`) cfg(`run/config/bind-alias.cfg` via `com.github.prohect.BindAliasClient.loadCFG()`) for automatic crash/error detection.
**Add test aliases for new code paths, make sure the log for new test aliases is in the log**.
**Never test MCP api with curl or raw HTTP — always use the MCP tools (getState, runAlias, etc.).**

### launch (detached, survives the caller)

```bash
cmd //c start \"\" '.\gradlew.bat' runTestClient --no-daemon
```

Only one game client can occupy the default MCP port (25575) at a time — shut down the previous client (builtinShutdown via the MCP tools) before launching a new one, the game client should be ready in 35 seconds.

### MCP bridge reload

Zed spawns MCP servers only at startup, so edits to `MCP/mcp_server.js` (tool descriptions, HTTP bridge logic) take effect only after a Zed reload. Use the global `zed-reload` skill:

```bash
zed-reload --settle 15 --wait 30 \
  "[zed-reload] Zed reloaded to restart the BindAlias MCP bridge. <context and next step>"
```

- `--wait 30` — time for the agent's final chat message to flush before Zed closes.
- `--settle 15` — time after Zed's window appears before injecting the revival message (MCP servers may still be starting; verify readiness by retrying `getState` for up to ~2 min).
- The agent thread continues unattended — the revived instance reads the whole history plus the injected message and picks up where it left off.

## gradlew build

`./gradlew build` is integrated with Spotless formatting, so build before committing.

## release workflow

1. **Develop** → build + runTestClient → **Confirm by User or by MCP tools** → commit
2. **Sync** → the post-commit hook auto-syncs the commit record and `.git_sync_across_active_branches` files to all active branches. **Everything else (source code, build scripts, etc.) must be manually merged** per branch — cherry-pick changes, solve conflicts, then build + runTestClient + **Confirm by User or by MCP tools** + commit per branch.
3. **STOP** — wait for user confirmation before bumping
4. **Bump** → `mod_version` in `gradle.properties` + CHANGELOG → commit
5. **Collect** → build each branch, copy **only the main mod JAR** (NOT `-sources.jar` nor `-dev.jar`) to `release/`. Name each JAR with the MC range suffix: `bind-alias-<$version>-mc<$branch_name>.jar` (e.g. `bind-alias-1.5.8-mc26.1.2-26.2.jar`).
6. **Verify** → `unzip -p <jar> fabric.mod.json` — read and check the **full unzipped JSON** (do NOT use `grep` — you must see every field). Verify `version`, `depends.minecraft` range, and `entrypoints` match the target branch.
7. **Release** → first `git push` **all** branches. Then `gh release create` with **only the main JARs** (no sources, no dev JARs) as assets. Do NOT create the release until EVERY branch is built, verified, release jar collected and pushed.

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
