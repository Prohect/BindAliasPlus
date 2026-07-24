# capture method (src/client/java/com/github/prohect/mixin/client/ChatComponentMixin.java)

## Syntax

```java
private static void capture(String text)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `text` | `String` | Plain-text message extracted from the `Component` parameter. |

## Remarks

Static bridge method that forwards captured chat text to `ChatCapture.onSystemMessage(text)`. Each of the three `@Inject` methods calls this with the plain-text version of the `Component` message.

Made `private static` to minimize overhead — no Mixin instance state is needed, and the JIT can inline the call to `ChatCapture`. The static modifier is allowed here because Mixin only forbids non-private static members on the mixin class itself; private statics are permitted.

## See Also

| Item | Description |
|------|-------------|
| [ChatCapture.onSystemMessage](../../mcp/ChatCapture.java/onSystemMessage.md) | The actual capture logic |
| [onAddClientSystemMessage](onAddClientSystemMessage.md) | Caller |
| [onAddServerSystemMessage](onAddServerSystemMessage.md) | Caller |
| [onAddPlayerMessage](onAddPlayerMessage.md) | Caller |

*Documented for Commit: [3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833](https://github.com/Prohect/BindAliasPlus/tree/3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833)*
