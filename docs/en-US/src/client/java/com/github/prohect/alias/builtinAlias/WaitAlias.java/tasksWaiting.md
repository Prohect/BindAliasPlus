# tasksWaiting field (src/client/java/com/github/prohect/alias/builtinAlias/WaitAlias.java)

## Syntax

```java
public static final java.util.ArrayList<com.github.prohect.alias.builtinAlias.WaitAliasRecord> tasksWaiting
```

## Remarks

The global queue of pending deferred alias executions. Each entry is a `WaitAliasRecord` that counts down each tick and executes its stored alias definition when the timer reaches zero.

Populated by `WaitAlias.run()`. Consumed by the tick handler that calls `WaitAliasRecord.tick()` on each entry. Records self-remove from this list when their delay expires.

Declared `final` (the reference is immutable) but the list contents are mutable. Not thread-safe — all access is from the render thread.

## See Also

| Item                                                    | Description                  |
| ------------------------------------------------------- | ---------------------------- |
| [WaitAliasRecord.tick](../WaitAliasRecord.java/tick.md) | Consumes and removes entries |
| [WaitAlias.run](run.md)                                 | Populates this list          |

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
