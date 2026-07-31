# begin method (src/client/java/com/github/prohect/mcp/StateTracker.java)

## Syntax

```java
public static synchronized String begin(boolean full)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `full` | `boolean` | `true` to force all state members included (for `/state`), `false` for changed-members-only diff |

## Return value

The beginning of the JSON envelope: `{"client_tick":N[,"state":{...}]}`. The caller must pass this to `finish()` to complete the envelope.

## Remarks

Must be called on the Minecraft main thread (accesses `Minecraft.getInstance()`). Thread-safe via `synchronized`.

1. **World-change detection**: if `BindAliasClient.joinTick` differs from the stored baseline, forces `full = true` and resets the previous snapshot.
2. **State collection**: calls `GameStateCollector.collect()` for the current snapshot.
3. **Member-level diff**: for each current state member, includes it if `full` or if the value differs from the previous snapshot. `held_keys` is force-included when non-empty.
4. **Disappeared members**: any key in the previous snapshot but absent from the current one is serialized as `null`.
5. **Container diff**: calls `containerSnapshot()`, compares against `lastContainer`. Full view on `full` / open / menu identity change; otherwise slot-level diff.
6. **Hotbar diff**: same pattern — full on `full` / world change; otherwise per-slot diff.
7. **Baseline update**: stores the current snapshot as `last`.

## See Also

| Item | Description |
|------|-------------|
| [finish](finish.md) | The second phase |
| [reset](reset.md) | Manual baseline reset |
| [GameStateCollector.collect](GameStateCollector.java/collect.md) | Raw state collection |
| [GameStateCollector.containerSnapshot](GameStateCollector.java/containerSnapshot.md) | Container state extraction |
| [GameStateCollector.hotbarItems](GameStateCollector.java/hotbarItems.md) | Hotbar state extraction |
