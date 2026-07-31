# currentTick field (src/client/java/com/github/prohect/BindAliasClient.java)

## Syntax

```java
public static long currentTick
```

## Remarks

Monotonically increasing counter incremented once per client tick by the `ClientTickEvents.START_CLIENT_TICK` registration in `onInitializeClient`. Starts at 0 on game start and is never reset. Used together with `joinTick` by `tickPrefix()` to produce `[client_tick:N]` log prefixes (ticks since join). Read by mixins and aliases for tick-based timing.

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
