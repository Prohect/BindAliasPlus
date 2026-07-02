# Development

## Setup

1. Clone with all branches:

   ```bash
   git clone https://github.com/Prohect/BindAliasPlus
   cd BindAliasPlus
   git fetch --all
   ```

2. Install the sync hook:

   **test2**

   **Windows (cmd / PowerShell):**

   ```cmd
   copy scripts\sync-post-commit .git\hooks\post-commit
   ```

   **Linux / macOS:**

   ```bash
   cp scripts/sync-post-commit .git/hooks/post-commit && chmod +x .git/hooks/post-commit
   ```

   After this, every commit that touches a synced file (see `.git_sync_across_active_branches`) is automatically mirrored to all active branches (`.git_active_branches`).

3. If you cloned with `--single-branch` or the config files are missing, the hook exits silently — no errors.

## Active Branches

| Branch           | MC      | Mappings |
| ---------------- | ------- | -------- |
| `26.1.2_26.2`    | 26.2    | Mojang   |
| `26.1_26.1.1`    | 26.1.1  | Mojang   |
| `1.21.9_1.21.11` | 1.21.11 | Yarn     |
| `1.21_1.21.8`    | 1.21.8  | Yarn     |

## Per-Branch Mapping (Mojang → Yarn)

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
