# signed method (src/client/java/com/github/prohect/mcp/SoundCapture.java)

## Syntax

```java
private static String signed(int v)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `v` | `int` | The integer value to format |

## Return value

The value prefixed with `"+"` for non-negative, `"-"` for negative. Zero returns `"+0"`.

## Remarks

Always-signed integer formatting for yaw/pitch in directional strings (e.g., `"yaw-40"`, `"pitch+20"`). The explicit sign makes the output unambiguous for parsing.

## See Also

| Item | Description |
|------|-------------|
| [directionOf](directionOf.md) | The caller |
