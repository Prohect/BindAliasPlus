# BINDING_PLUS field (src/client/java/com/github/prohect/BindAliasClient.java)

## Syntax

```java
public static final java.util.Map<com.mojang.blaze3d.platform.InputConstants$Key, com.github.prohect.BindAliasKeyBinding> BINDING_PLUS
```

## Remarks

All active key→alias bindings. Keyed by Minecraft's `InputConstants.Key` (representing a keyboard key or mouse button). Each value is a `BindAliasKeyBinding` specifying which alias name to invoke on press and which on release. Populated by CFG autoload (`bind` / `bindByAliasName` lines) and runtime `/bind` commands. Consumed by `MinecraftClientMixin`'s tick loop to dispatch `KEY_QUEUE` events. Entries with `fromCFG=true` are removable by `unloadCFGBinds`.

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
