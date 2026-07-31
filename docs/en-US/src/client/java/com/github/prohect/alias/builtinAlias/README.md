# builtinAlias

## Contents

| Name | Description |
|------|-------------|
| [AdvancementsAlias.java](AdvancementsAlias.java/README.md) | |
| [AliasAlias.java](AliasAlias.java/README.md) | |
| [ApplyRecipeAlias.java](ApplyRecipeAlias.java/README.md) | |
| [AttackAlias.java](AttackAlias.java/README.md) | |
| [BackAlias.java](BackAlias.java/README.md) | |
| [BindAlias.java](BindAlias.java/README.md) | |
| [CyclePerspectiveAlias.java](CyclePerspectiveAlias.java/README.md) | |
| [DebugOverlayAlias.java](DebugOverlayAlias.java/README.md) | |
| [DropAlias.java](DropAlias.java/README.md) | |
| [EscAlias.java](EscAlias.java/README.md) | |
| [ForwardAlias.java](ForwardAlias.java/README.md) | |
| [FreeCursorAlias.java](FreeCursorAlias.java/README.md) | |
| [JumpAlias.java](JumpAlias.java/README.md) | |
| [LeftAlias.java](LeftAlias.java/README.md) | |
| [LocalSayAlias.java](LocalSayAlias.java/README.md) | |
| [LockAlias.java](LockAlias.java/README.md) | |
| [LockAlias_OnLock.java](LockAlias_OnLock.java/README.md) | |
| [LockAlias_Unlock.java](LockAlias_Unlock.java/README.md) | |
| [LogAlias.java](LogAlias.java/README.md) | |
| [OpenInventoryAlias.java](OpenInventoryAlias.java/README.md) | Switch alias (+/−) that opens or closes the player inventory screen |
| [PickItemAlias.java](PickItemAlias.java/README.md) | One-shot: triggers vanilla pick-block behavior (middle-click) |
| [PitchAlias.java](PitchAlias.java/README.md) | Double-arg: relative pitch rotation `pitch\deg` |
| [PlayerListAlias.java](PlayerListAlias.java/README.md) | Switch alias (+/−) that shows the online-player overlay (Tab) |
| [ReapplyAlias.java](ReapplyAlias.java/README.md) | String-arg: re-assert a held key after screen transition `reapply\action` |
| [ReloadCFGAlias.java](ReloadCFGAlias.java/README.md) | One-shot: reloads the configuration file at runtime |
| [RightAlias.java](RightAlias.java/README.md) | Switch alias (+/−) for strafe-right movement (D key) |
| [RunAliasAlias.java](RunAliasAlias.java/README.md) | String-arg: execute a registered alias by name `builtinRunAlias\name` |
| [SayAlias.java](SayAlias.java/README.md) | String-arg: send a chat message to the server `say\text` |
| [ScreenshotAlias.java](ScreenshotAlias.java/README.md) | Switch alias (+): take a screenshot via vanilla F2 codepath |
| [SendCommandAlias.java](SendCommandAlias.java/README.md) | String-arg: send a server command (no leading `/`) |
| [SetPerspectiveAlias.java](SetPerspectiveAlias.java/README.md) | Integer-arg: set camera perspective 0=FPS, 1=TPS, 2=TPS2 |
| [SetPitchAlias.java](SetPitchAlias.java/README.md) | Double-arg: absolute pitch setter `setPitch\deg` |
| [SetYawAlias.java](SetYawAlias.java/README.md) | Double-arg: absolute yaw setter `setYaw\deg` |
| [ShutdownAlias.java](ShutdownAlias.java/README.md) | One-shot: cleanly shuts down the game `builtinShutdown` |
| [SilentAlias.java](SilentAlias.java/README.md) | Switch alias (+/−) that toggles silent mode (suppress mod feedback) |
| [SlotAlias.java](SlotAlias.java/README.md) | Integer-arg: select hotbar slot 1-9 `slot\N` |
| [SneakAlias.java](SneakAlias.java/README.md) | Switch alias (+/−) for sneaking/crouching (Shift key) |
| [SprintAlias.java](SprintAlias.java/README.md) | Switch alias (+/−) for sprinting (Ctrl key, requires +forward) |
| [SwapHandAlias.java](SwapHandAlias.java/README.md) | One-shot: swaps main hand and offhand items via packet |
| [SwapSlotAlias.java](SwapSlotAlias.java/README.md) | Multi-arg: swap items between any two inventory/container slots |
| [ToggleInventoryAlias.java](ToggleInventoryAlias.java/README.md) | One-shot: toggle inventory screen open/close |
| [UnbindAlias.java](UnbindAlias.java/README.md) | String-arg: sends an unbind command to the server |
| [UnloadCFGAliasesAlias.java](UnloadCFGAliasesAlias.java/README.md) | One-shot: removes CFG-loaded user aliases |
| [UnloadCFGAllAlias.java](UnloadCFGAllAlias.java/README.md) | One-shot: removes all CFG-loaded items (aliases + binds + vars) |
| [UnloadCFGBindsAlias.java](UnloadCFGBindsAlias.java/README.md) | One-shot: removes CFG-loaded keybindings |
| [UnloadCFGVarsAlias.java](UnloadCFGVarsAlias.java/README.md) | One-shot: removes CFG-loaded general variables |
| [UnloadUserAliasesAlias.java](UnloadUserAliasesAlias.java/README.md) | One-shot: removes runtime-created user aliases |
| [UnloadUserAllAlias.java](UnloadUserAllAlias.java/README.md) | One-shot: removes all runtime-created items (aliases + binds + vars) |
| [UnloadUserBindsAlias.java](UnloadUserBindsAlias.java/README.md) | One-shot: removes runtime-created keybindings |
| [UnloadUserVarsAlias.java](UnloadUserVarsAlias.java/README.md) | One-shot: removes runtime-created variables (general + container) |
| [UseAlias.java](UseAlias.java/README.md) | Switch alias (+/−) for using items / right-click interaction |
| [VarAlias.java](VarAlias.java/README.md) | Multi-arg: store/inspect variables; central resolution system for other aliases |
| [WaitAlias.java](WaitAlias.java/README.md) | Integer-arg: defer alias chain execution by N ticks `wait\N` |
| [WaitAliasRecord.java](WaitAliasRecord.java/README.md) | Deferred-task record holding countdown ticks and definition string |
| [YawAlias.java](YawAlias.java/README.md) | Double-arg: relative yaw rotation `yaw\deg` |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
