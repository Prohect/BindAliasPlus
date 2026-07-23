# builtinAlias

All 49 concrete alias implementations. **Movement aliases** (Attack, Forward, Back, Left, Right, Jump, Sneak, Sprint) are ~730 bytes each and share an identical pattern: they extend `BuiltinAliasWithBooleanArgs` and toggle a `KeyMapping`. **Read one to understand them all.** The most complex and important aliases are **LockAlias** (cursor lock/unlock, 13KB), **VarAlias** (variable system, 12KB), **SwapSlotAlias** (inventory slot swap, 12KB), **WaitAlias** (delayed execution via tick-driven queue), and **DropAlias** (hold-to-repeat). Config management aliases handle runtime reload operations, meta aliases let you define/bind/run aliases at runtime, and chat/command aliases send messages and execute commands.

**Reading order:** Start with a movement alias (e.g., ForwardAlias) to understand the base pattern → **LockAlias**, **VarAlias**, **SwapSlotAlias** for complex logic → **WaitAlias** / DropAlias for tick-driven features → **ScreenshotAlias**, **EscAlias**, **FreeCursorAlias** for agent-tooling patterns → then explore remaining groups as needed.

## Contents

| Name                                                               | Description                                                                                                                              |
| ------------------------------------------------------------------ | ---------------------------------------------------------------------------------------------------------------------------------------- |
| [AdvancementsAlias.java](AdvancementsAlias.java/README.md)         | **Screen** — holds/releases the advancements screen key (`+advancements` / `-advancements`)                                              |
| [AliasAlias.java](AliasAlias.java/README.md)                       | **Meta** — define aliases at runtime via in-game command                                                                                 |
| [AttackAlias.java](AttackAlias.java/README.md)                     | **Movement** — toggles attack/break key. Identical pattern to other movement aliases.                                                    |
| [BackAlias.java](BackAlias.java/README.md)                         | **Movement** — toggles backward key. Identical pattern to other movement aliases.                                                        |
| [BindAlias.java](BindAlias.java/README.md)                         | **Meta** — bind an alias to a key combination at runtime                                                                                 |
| [CyclePerspectiveAlias.java](CyclePerspectiveAlias.java/README.md) | Cycles through perspective modes (first-person, third-person back, third-person front)                                                   |
| [DebugOverlayAlias.java](DebugOverlayAlias.java/README.md)         | **Screen** — toggles the F3 debug overlay (`+debugOverlay` / `-debugOverlay`)                                                            |
| [DropAlias.java](DropAlias.java/README.md)                         | **Important** — hold-to-repeat item dropping via tick-driven `isDown` tracking                                                           |
| [EscAlias.java](EscAlias.java/README.md)                           | **Screen** — closes the current screen (`esc\0`) or toggles pause menu (`esc\1`)                                                       |
| [ForwardAlias.java](ForwardAlias.java/README.md)                   | **Movement** — toggles forward key. Identical pattern to other movement aliases.                                                         |
| [FreeCursorAlias.java](FreeCursorAlias.java/README.md)             | **Input** — cancels mouse grab/lock for unrestricted cursor movement (`+freeCursor` / `-freeCursor`, hidden from suggestions)            |
| [JumpAlias.java](JumpAlias.java/README.md)                         | **Movement** — toggles jump key. Identical pattern to other movement aliases.                                                            |
| [LeftAlias.java](LeftAlias.java/README.md)                         | **Movement** — toggles strafe-left key. Identical pattern to other movement aliases.                                                     |
| [LocalSayAlias.java](LocalSayAlias.java/README.md)                 | Sends a chat message visible only to the local player (not sent to server)                                                               |
| [LockAlias.java](LockAlias.java/README.md)                         | **Important** — cursor lock/unlock system (13KB). Toggles mouse grab, dispatches to `LockAlias_OnLock`/`LockAlias_Unlock`.               |
| [LockAlias_OnLock.java](LockAlias_OnLock.java/README.md)           | Low-level — callback interface invoked when cursor is locked                                                                             |
| [LockAlias_Unlock.java](LockAlias_Unlock.java/README.md)           | Low-level — callback interface invoked when cursor is unlocked                                                                           |
| [LogAlias.java](LogAlias.java/README.md)                           | Writes a message to the Minecraft log file                                                                                               |
| [OpenInventoryAlias.java](OpenInventoryAlias.java/README.md)       | Opens the player's inventory screen                                                                                                      |
| [PickItemAlias.java](PickItemAlias.java/README.md)                 | Picks the block/item the player is looking at (middle-click)                                                                             |
| [PitchAlias.java](PitchAlias.java/README.md)                       | Adjusts player pitch by a relative amount                                                                                                |
| [PlayerListAlias.java](PlayerListAlias.java/README.md)             | **Screen** — holds/releases the Tab key to show online-player overlay (`+playerList` / `-playerList`)                                    |
| [ReapplyAlias.java](ReapplyAlias.java/README.md)                   | **Meta** — re-applies all currently active aliases (used on world join)                                                                  |
| [ReloadCFGAlias.java](ReloadCFGAlias.java/README.md)               | **Config** — reloads the entire configuration from disk at runtime                                                                       |
| [RightAlias.java](RightAlias.java/README.md)                       | **Movement** — toggles strafe-right key. Identical pattern to other movement aliases.                                                    |
| [RunAliasAlias.java](RunAliasAlias.java/README.md)                 | **Meta** — execute a user alias by name at runtime                                                                                       |
| [ScreenshotAlias.java](ScreenshotAlias.java/README.md)             | **Screen** — captures a screenshot via the vanilla screenshot key (`+screenshot` / `-screenshot`)                                        |
| [SayAlias.java](SayAlias.java/README.md)                           | Sends a chat message to the server (public chat)                                                                                         |
| [SendCommandAlias.java](SendCommandAlias.java/README.md)           | Sends a slash command to the server                                                                                                      |
| [SetPerspectiveAlias.java](SetPerspectiveAlias.java/README.md)     | Sets the perspective to a specific mode                                                                                                  |
| [SetPitchAlias.java](SetPitchAlias.java/README.md)                 | Sets player pitch to an absolute value                                                                                                   |
| [SetYawAlias.java](SetYawAlias.java/README.md)                     | Sets player yaw to an absolute value                                                                                                     |
| [ShutdownAlias.java](ShutdownAlias.java/README.md)                 | Triggers a clean game shutdown                                                                                                           |
| [SilentAlias.java](SilentAlias.java/README.md)                     | Suppresses all alias output/logging temporarily                                                                                          |
| [SlotAlias.java](SlotAlias.java/README.md)                         | Selects a specific hotbar slot                                                                                                           |
| [SneakAlias.java](SneakAlias.java/README.md)                       | **Movement** — toggles sneak key. Identical pattern to other movement aliases.                                                           |
| [SprintAlias.java](SprintAlias.java/README.md)                     | **Movement** — toggles sprint key. Identical pattern to other movement aliases.                                                          |
| [SwapHandAlias.java](SwapHandAlias.java/README.md)                 | Swaps main hand and offhand items                                                                                                        |
| [SwapSlotAlias.java](SwapSlotAlias.java/README.md)                 | **Important** — inventory slot swapping system (12KB). Moves items between slots via hover-and-click logic.                              |
| [UnbindAlias.java](UnbindAlias.java/README.md)                     | **Meta** — unbind a key combination from its alias at runtime                                                                            |
| [UnloadCFGAliasesAlias.java](UnloadCFGAliasesAlias.java/README.md) | **Config** — unloads only the `aliases` section of the config                                                                            |
| [UnloadCFGAllAlias.java](UnloadCFGAllAlias.java/README.md)         | **Config** — unloads the entire config (aliases, binds, vars)                                                                            |
| [UnloadCFGBindsAlias.java](UnloadCFGBindsAlias.java/README.md)     | **Config** — unloads only the `binds` section of the config                                                                              |
| [UnloadCFGVarsAlias.java](UnloadCFGVarsAlias.java/README.md)       | **Config** — unloads only the `vars` section of the config                                                                               |
| [UseAlias.java](UseAlias.java/README.md)                           | Right-clicks / uses the item in the player's hand                                                                                        |
| [VarAlias.java](VarAlias.java/README.md)                           | **Important** — variable system (12KB). Stores, retrieves, and manipulates named values (ints, strings, booleans) used by other aliases. |
| [WaitAlias.java](WaitAlias.java/README.md)                         | **Important** — delayed alias execution. Enqueues `WaitAliasRecord` instances that are dispatched on subsequent client ticks.            |
| [WaitAliasRecord.java](WaitAliasRecord.java/README.md)             | Low-level — data record for a single wait step: alias name, args, and remaining tick delay                                               |
| [YawAlias.java](YawAlias.java/README.md)                           | Adjusts player yaw by a relative amount                                                                                                  |

_Documented for Commit: [559546b](https://github.com/Prohect/BindAliasPlus/tree/559546bace0e8a52a434e97e41e60e3a76c78989)_
