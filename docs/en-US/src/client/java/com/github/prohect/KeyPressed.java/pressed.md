# pressed method (src/client/java/com/github/prohect/KeyPressed.java)

## Syntax

```java
public boolean pressed()
```

## Remarks

Record accessor. Returns `true` if the key was pressed down, `false` if released.
The mod dispatches `aliasNameOnKeyPressed` from [KeyBindingPlus](../KeyBindingPlus.java/KeyBindingPlus.md)
when `pressed` is `true`, and `aliasNameOnKeyReleased` when `false`.

## See Also

_Documented for Commit: [7c41e9ab8bab207ec351187cabc3c260c9087925](https://github.com/Prohect/BindAlias/tree/7c41e9ab8bab207ec351187cabc3c260c9087925)_
