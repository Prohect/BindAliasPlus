# `src/` file identity across 4 branches

Branches: `1.21_1.21.8`, `1.21.9_1.21.11`, `26.1_26.1.1`, `26.1.2_26.2`

**Total src files**: 88

---

## ✅ Identical across ALL 4 branches (29)

```
src/client/java/com/github/prohect/BindAliasPlusDataGenerator.java
src/client/java/com/github/prohect/KeyBindingPlus.java
src/client/java/com/github/prohect/alias/AliasRecord.java
src/client/java/com/github/prohect/alias/AliasWithArgs.java
src/client/java/com/github/prohect/alias/AliasWithoutArgs.java
src/client/java/com/github/prohect/alias/BuiltinAliasWithArgs.java
src/client/java/com/github/prohect/alias/BuiltinAliasWithBooleanArgs.java
src/client/java/com/github/prohect/alias/BuiltinAliasWithDoubleArgs.java
src/client/java/com/github/prohect/alias/BuiltinAliasWithGreedyStringArgs.java
src/client/java/com/github/prohect/alias/BuiltinAliasWithIntegerArgs.java
src/client/java/com/github/prohect/alias/BuiltinAliasWithoutArgs.java
src/client/java/com/github/prohect/alias/UserAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/LockAlias_OnLock.java
src/client/java/com/github/prohect/alias/builtinAlias/LockAlias_Unlock.java
src/client/java/com/github/prohect/alias/builtinAlias/LogAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/ReapplyAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/ReloadCFGAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/RunAliasAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/SilentAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/UnloadCFGAliasesAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/UnloadCFGAllAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/UnloadCFGVarsAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/WaitAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/WaitAliasRecord.java
src/client/java/com/github/prohect/mcp/GameChannels.java
src/client/java/com/github/prohect/mcp/ScreenshotCapture.java
src/main/java/com/github/prohect/BindAliasPlus.java
src/main/resources/assets/bind-alias-plus/icon.png
src/main/resources/bind-alias-plus.mixins.json
```

---

## 2+2 split — pair-identical, cross-pair API diffs (42)

Identical within each version family (1.21.x pair same, 26.x pair same), differ between families.

```
src/client/java/com/github/prohect/alias/Alias.java
src/client/java/com/github/prohect/KeyPressed.java
src/client/java/com/github/prohect/alias/builtinAlias/AdvancementsAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/AliasAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/ApplyRecipeAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/AttackAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/BackAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/BindAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/CyclePerspectiveAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/EscAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/ForwardAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/FreeCursorAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/JumpAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/LeftAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/LockAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/OpenInventoryAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/PickItemAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/PitchAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/PlayerListAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/RightAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/SayAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/SendCommandAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/SetPerspectiveAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/SetPitchAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/SetYawAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/ShutdownAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/SneakAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/SprintAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/SwapHandAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/ToggleInventoryAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/UnbindAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/UseAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/YawAlias.java
src/client/java/com/github/prohect/mixin/client/ClientPacketListenerMixin.java
src/client/java/com/github/prohect/mixin/client/KeyboardInputMixin.java
src/client/java/com/github/prohect/mcp/SoundCapture.java
src/client/java/com/github/prohect/mcp/StateTracker.java
src/client/java/com/github/prohect/mixin/client/ChatComponentMixin.java
src/client/java/com/github/prohect/mixin/client/NativeImageMixin.java
src/client/java/com/github/prohect/util/McScreenHelper.java
src/client/resources/bind-alias-plus.client.mixins.json
src/client/java/com/github/prohect/alias/builtinAlias/UnloadCFGBindsAlias.java
```

---

## Differ within 1.21.x pair, 26.x pair same (10)

Minecraft API changes between 1.21.0–1.21.8 and 1.21.9–1.21.11.

```
src/client/java/com/github/prohect/alias/builtinAlias/DebugOverlayAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/DropAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/SlotAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java
src/client/java/com/github/prohect/mcp/RecipeBookHelper.java
src/client/java/com/github/prohect/mixin/client/KeyBoardMixin.java
src/client/java/com/github/prohect/mixin/client/MouseMixin.java
src/client/java/com/github/prohect/mcp/GameStateCollector.java
src/client/java/com/github/prohect/BindAliasPlusClient.java
src/client/resources/bind-alias-plus-client.accesswidener
```

---

## Differ within 26.x pair, 1.21.x pair same (4)

```
src/client/java/com/github/prohect/alias/builtinAlias/LocalSayAlias.java
src/client/java/com/github/prohect/alias/builtinAlias/ScreenshotAlias.java
src/client/java/com/github/prohect/mixin/client/MinecraftClientMixin.java
src/client/java/com/github/prohect/mcp/McpHttpServer.java
```

- `LocalSayAlias`: `gui.getChat()` → `gui.hud.getChat()`
- `ScreenshotAlias`: `Screenshot.grab` vs `handleGlobalKeyPress` (different implementation)
- `MinecraftClientMixin`: comment-only (`onMouseClick`/`timesPressed` vs `slotClicked`/`clickCount`)
- `McpHttpServer`: `getMainRenderTarget()` → `gameRenderer.mainRenderTarget()` (one line)

---

## All 4 branches differ (2)

```
src/client/java/com/github/prohect/alias/builtinAlias/SwapSlotAlias.java
src/main/resources/fabric.mod.json
```

- `SwapSlotAlias`: refactored in 1.21.9 + 1.21/26 API diffs
- `fabric.mod.json`: version-specific `minecraft` and `fabricloader` ranges

---

## mixin src name different (1)

```
src/client/java/com/github/prohect/mixin/client/AbstractContainerScreenMixin.java
```

```
src\client\java\com\github\prohect\mixin\client\HandledScreenMixin.java
```

(1.21.x — yarn-mapping; 26.x — mojang-mapping)

---

## Summary

| Category                  | Count  |
| ------------------------- | ------ |
| Identical (all 4)         | 29     |
| 2+2 split                 | 42     |
| Differ within 1.21.x pair | 10     |
| Differ within 26.x pair   | 4      |
| All 4 differ              | 2      |
| mixin src name different  | 1      |
| **Total**                 | **88** |

All non-identical files differ because of Minecraft API mapping changes between
1.21.x (yarn/mojmap) and 26.x (mojang mappings). They cannot be unified without
separate source sets, build-time preprocessing, or an abstraction layer.
