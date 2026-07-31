# register method (src/client/java/com/github/prohect/mcp/SoundCapture.java)

## Syntax

```java
public static void register()
```

## Remarks

Registers the singleton `SoundCapture` instance on the client `SoundManager` via `MinecraftClient.getInstance().getSoundManager().addListener(INSTANCE)`. Safe to call multiple times — the `SoundManager` deduplicates by listener identity. Called from `BindAliasClient.onInitializeClient()` during mod initialization.

## See Also

| Item | Description |
|------|-------------|
| [onPlaySound](onPlaySound.md) | The callback invoked for each sound |
| [SoundCapture](SoundCapture.md) | The class doc |
