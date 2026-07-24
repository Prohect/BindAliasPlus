# isActive method (src/client/java/com/github/prohect/mcp/ChatCapture.java)

## Syntax

```java
public static boolean isActive()
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| _(none)_ | | |

## Remarks

Returns `true` if a capture window is currently open (i.e., `begin()` was called and `end()` has not been called yet). Reads the `volatile boolean active` field — safe for cross-thread access.

Used by `McpHttpServer` to check whether a capture is already in progress before starting a new one.

## See Also

| Item | Description |
|------|-------------|
| [begin](begin.md) | Sets active to true |
| [end](end.md) | Sets active to false |

*Documented for Commit: [3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833](https://github.com/Prohect/BindAliasPlus/tree/3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833)*
