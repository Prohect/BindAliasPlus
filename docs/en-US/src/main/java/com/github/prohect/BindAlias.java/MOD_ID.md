# MOD_ID field (src/main/java/com/github/prohect/BindAlias.java)

## Syntax

```java
public static final String MOD_ID = "bind-alias"
```

## Remarks

The mod's unique identifier string. Used as the logger name, the CFG file name (`config/bind-alias.cfg`), and is referenced by `fabric.mod.json` for mod registration. Follows Fabric convention of using lowercase hyphenated names.

## See Also

| Item | Description |
|------|-------------|
| [LOGGER](LOGGER.md) | Logger initialized with this ID |
