# onMainThread method (src/client/java/com/github/prohect/mcp/McpHttpServer.java)

## Syntax

```java
private static <T> T onMainThread(CheckedSupplier<T> task) throws Exception
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `task` | `CheckedSupplier<T>` | Functional interface wrapping a callable that may throw. Executed on the game thread. |

## Remarks

Bridges the HTTP handler thread to the Minecraft main (render/game) thread. All game state access must happen on the main thread — this method dispatches the task via `Minecraft.getInstance().execute()` and blocks the calling HTTP thread until the task completes or times out.

Returns the task's result. Throws `Exception` on timeout (5 seconds) or if the task throws. The `TIMEOUT_SECONDS` constant controls the maximum wait.

The `CheckedSupplier<T>` functional interface is a local equivalent of `Callable<T>` that allows checked exceptions (the HTTP handlers throw `IOException` through the call chain).

## See Also

| Item | Description |
|------|-------------|
| [handleState](handleState.md) | Uses this to build the state JSON on the game thread |
| [handleScreenshot](handleScreenshot.md) | Uses this to trigger the screenshot on the game thread |
| [handleRunAlias](handleRunAlias.md) | Uses this to execute aliases on the game thread |

*Documented for Commit: [3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833](https://github.com/Prohect/BindAlias/tree/3c3ca2d09e6dd6a483ade9730a29d42bb1ee5833)*
