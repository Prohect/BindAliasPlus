# INSTANCE field (src/client/java/com/github/prohect/BindAliasPlusClient.java)

## Syntax

```java
public static final com.github.prohect.BindAliasPlusClient INSTANCE
```

## Remarks

Eager singleton instance of `BindAliasPlusClient`. Initialized at class load time.

Used by mixins (key handlers, `GuiMixin`) and built-in alias classes to access
the shared state (`KEY_QUEUE`, `BINDING_PLUS`, `silentMode`, `currentScreen`)
without needing to pass the client instance around.

Read-only reference; the instance itself is mutable (fields on it can change).

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAliasPlus/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
