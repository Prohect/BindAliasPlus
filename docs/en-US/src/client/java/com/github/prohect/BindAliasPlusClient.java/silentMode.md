# silentMode field (src/client/java/com/github/prohect/BindAliasClient.java)

## Syntax

```java
public static boolean silentMode
```

## Remarks

Global flag that suppresses in-game chat feedback when `true`.

**Setters**:

- `SilentAlias` (toggles on/off).
- Reset to `false` on disconnect.

**Readers**: Every command handler checks this before sending `sendFeedback`
or `sendSystemMessage` calls.

Default value: `false` (feedback enabled).

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
