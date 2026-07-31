# joinTick field (src/client/java/com/github/prohect/BindAliasClient.java)

## Syntax

```java
public static long joinTick
```

## Remarks

Set to `currentTick` when `ClientPlayConnectionEvents.JOIN` fires (i.e., the player enters a world). Initial value is `-1`, which causes `tickPrefix()` to return `[client_tick:-1]` until the first join. After join, `tickPrefix()` computes `currentTick - joinTick` to report ticks since join. Never reset (even on disconnect); a re-join updates it again.

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
