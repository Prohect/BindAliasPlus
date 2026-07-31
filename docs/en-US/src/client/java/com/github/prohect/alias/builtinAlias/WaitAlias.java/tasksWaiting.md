# tasksWaiting field (src/client/java/com/github/prohect/alias/builtinAlias/WaitAlias.java)

Static list of all currently waiting deferred alias tasks.

## Syntax

```java
public static final java.util.ArrayList<com.github.prohect.alias.builtinAlias.WaitAliasRecord> tasksWaiting
```

## Remarks

**Purpose:** Holds all in-progress `WaitAliasRecord` instances. Each entry represents a deferred alias chain waiting to execute after its tick counter expires.

**Writers:**
- `WaitAlias.run(String, String)` — adds new records when `flag > 0`.
- `WaitAliasRecord.tick()` — removes records when their counter reaches 0 (self-removal).

**Readers:** `MinecraftClientMixin` — iterates and calls `.tick()` on each entry every game tick. MCP nap mechanism also interacts with this list.

**Thread safety:** Accessed only from the game thread. `ArrayList` is used without synchronization.

**Important behavior:** The list is iterated during `MinecraftClientMixin` ticks. Since `WaitAliasRecord.tick()` removes `this` from the list when the counter expires, the iteration must handle concurrent modification. The mixin avoids this by iterating backwards or collecting expired tasks before removal.

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
