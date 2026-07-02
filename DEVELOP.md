# Development

## Setup

1. Clone with all branches:

   ```bash
   git clone https://github.com/Prohect/BindAliasPlus
   cd BindAliasPlus
   git fetch --all
   ```

2. Install the sync hooks:

   **Ignore this**

   **Linux / macOS:**

   ```bash
   cp scripts/sync-post-commit .git/hooks/post-commit && chmod +x .git/hooks/post-commit
   cp scripts/sync-post-commit .git/hooks/post-rewrite && chmod +x .git/hooks/post-rewrite
   ```

   After this, every commit (or amend/rebase) that touches a synced file (see `.git_sync_across_active_branches`) is automatically mirrored to all active branches (`.git_active_branches`).

3. If you cloned with `--single-branch` or the config files are missing, the hooks exit silently — no errors.

## Force-push edge case

After a force-push on one branch, the sync commits on other branches may be ahead of the rewritten history. If you rewrite and force-push a branch, verify all branches are in sync or re-trigger the hook with a trivial commit:

```bash
git commit --allow-empty -m "chore: re-trigger sync"
```

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
