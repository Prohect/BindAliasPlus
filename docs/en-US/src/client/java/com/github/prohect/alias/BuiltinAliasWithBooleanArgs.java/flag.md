# flag field (src/client/java/com/github/prohect/alias/BuiltinAliasWithBooleanArgs.java)

## Syntax

```java
public boolean flag
```

## Remarks

The parsed boolean value set by `parseArgs()`.

- `true` — key-down / press event (parsed from `"1"`)
- `false` — key-up / release event (parsed from `"0"` or invalid input)

Defaults to `false`. Written by `parseArgs()`. Read by subclasses and by
`reapplyToGameKeyMapping()`.

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
