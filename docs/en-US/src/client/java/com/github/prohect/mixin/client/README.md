# client

Mixin classes injecting into vanilla Minecraft client classes. These are the bridge between the game engine and the BindAlias mod's alias execution, MCP channel messaging, screenshot capture, and freeCursor support.

**Recommended reading order:** start with [`MinecraftClientMixin`](MinecraftClientMixin.java/README.md) (the central tick driver), then [`KeyBoardMixin`](KeyBoardMixin.java/README.md) and [`MouseMixin`](MouseMixin.java/README.md) (input routing), then [`KeyboardInputMixin`](KeyboardInputMixin.java/README.md) (alias dispatch).

## Contents

| Name | Description |
|------|-------------|
| [AbstractContainerScreenMixin.java](AbstractContainerScreenMixin.java/README.md) | Pins the hovered slot to the agent's slot 14 when freeCursor is active, so drop/swap operations target a deterministic slot regardless of host cursor position |
| [ChatComponentMixin.java](ChatComponentMixin.java/README.md) | Intercepts all three `ChatComponent` message-entry points to feed chat messages into the MCP `CHAT` channel |
| [ClientPacketListenerMixin.java](ClientPacketListenerMixin.java/README.md) | Intercepts recipe-book-add packets to feed newly unlocked recipe names into the MCP `RECIPE` channel |
| [KeyboardInputMixin.java](KeyboardInputMixin.java/README.md) | Drains the `KEY_QUEUE` each tick and dispatches queued key events to `AliasWithoutArgs` instances — bridges physical input to alias execution |
| [KeyBoardMixin.java](KeyBoardMixin.java/README.md) | Intercepts physical keyboard press/release events, filters by window/lock/binding, and enqueues `KeyPressed` records into `KEY_QUEUE` |
| [MinecraftClientMixin.java](MinecraftClientMixin.java/README.md) | The central per-tick integration point: screen tracking, WaitAlias deferred tasks, continuous drop, and MCP nap countdown |
| [MouseMixin.java](MouseMixin.java/README.md) | freeCursor support (OS grab suppression, camera-turn cancellation, isMouseGrabbed override), mouse button routing to `KEY_QUEUE`, and alias reapply on cursor grab |
| [NativeImageMixin.java](NativeImageMixin.java/README.md) | Intercepts screenshot PNG writes to capture bytes in memory for the MCP screenshot endpoint, cutting response time from ~500ms to <50ms |

