# client

Mixin injection points that hook into Minecraft's input handling and client tick loop. These are the bridge between hardware input and the alias system. The key flow is: **KeyBoardMixin** and **MouseMixin** enqueue `KeyPressed` events into a shared `KEY_QUEUE` → **KeyboardInputMixin** drains the queue each tick and performs `BINDING_PLUS` lookup → matched aliases call `alias.run()`. **GuiMixin** tracks the current screen for alias screen-blacklist logic, and **MinecraftClientMixin** drives tick-based aliases (WaitAlias, DropAlias). **MouseMixin** also supports `FreeCursorAlias` via `cancelLockCursor` HEAD injection.

**Reading order:** **KeyboardInputMixin** (dispatcher) → **KeyBoardMixin** / **MouseMixin** (enqueuers) → **MinecraftClientMixin** (tick driver) → **GuiMixin** (screen tracking).

## Contents

| Name                                                             | Description                                                                                                                           |
| ---------------------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------- |
| [GuiMixin.java](GuiMixin.java/README.md)                         | Injects into `setScreen()` to track `currentScreen` for the alias screen-blacklist logic                                              |
| [KeyboardInputMixin.java](KeyboardInputMixin.java/README.md)     | **The dispatcher** — injects into `KeyboardInput.tick()`, drains `KEY_QUEUE`, looks up `BINDING_PLUS`, and dispatches matched aliases |
| [KeyBoardMixin.java](KeyBoardMixin.java/README.md)               | Injects into `KeyboardHandler.keyPress()`, enqueues `KeyPressed` events into `KEY_QUEUE`                                              |
| [MinecraftClientMixin.java](MinecraftClientMixin.java/README.md) | Injects into client tick to drive WaitAlias (decrementing delay counters) and DropAlias (hold-to-repeat)                              |
| [MouseMixin.java](MouseMixin.java/README.md)                     | Injects into `Mouse` for mouse button events, cursor-lock reapply, and `FreeCursorAlias` cancelLockCursor injection                   |

_Documented for Commit: [559546b](https://github.com/Prohect/BindAlias/tree/559546bace0e8a52a434e97e41e60e3a76c78989)_
