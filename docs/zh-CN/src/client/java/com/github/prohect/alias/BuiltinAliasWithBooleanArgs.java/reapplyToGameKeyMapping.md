# reapplyToGameKeyMapping 方法（src/client/java/com/github/prohect/alias/BuiltinAliasWithBooleanArgs.java）

## 语法

```java
public void reapplyToGameKeyMapping()
```

## 备注

在界面切换后重新同步按住的按键。如果 `this.flag` 为 `true`，则调用 `this.run("1")` 将按下事件重新注入游戏的按键映射。

这抵消了 Minecraft 的原版行为：当界面通过 `setScreen()` 打开时，游戏会调用 `releaseAll()`，松开所有按住的按键。界面关闭后，如果不在界面打开前按住的按键恢复到按住状态，这些按键就会保持松开。`reapply` 别名会遍历所有布尔参数别名并调用此方法以恢复它们的状态。

具体子类如果需要自定义的重新应用行为，可以覆盖此方法。默认实现只是在 `flag` 为 `true` 时以 `"1"` 重新运行。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [flag](flag.md) | 此方法检查的按住状态标志 |
| [ReapplyAlias](builtinAlias/ReapplyAlias.java/ReapplyAlias.md) | 在所有布尔别名上触发此方法的 `reapply` 内置别名 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
