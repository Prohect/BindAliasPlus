# run method (src/client/java/com/github/prohect/alias/builtinAlias/PlayerListAlias.java)

Presses or releases the player-list key (Tab) to show/hide the online-player overlay.

## Syntax

```java
public PlayerListAlias run(String args)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| args | `String` | `"1"` (press / `+playerList`) or `"0"` (release / `-playerList`) |

## Remarks

- Release (`-playerList`) always works, even under text-input screens.
- Press (`+playerList`) is blocked while typing to avoid showing the overlay during chat.
- Calls `key.setDown(flag)` and increments `clickCount` on press so Minecraft's key-polling loop registers the input.

## See Also

| Item | Description |
|------|-------------|
| [PlayerListAlias](PlayerListAlias.md) | Class documentation |

_Documented for Commit: [2003c5c](https://github.com/Prohect/BindAliasPlus/tree/2003c5c648f2214e6b8c099a0db1ec96a130246b)_
