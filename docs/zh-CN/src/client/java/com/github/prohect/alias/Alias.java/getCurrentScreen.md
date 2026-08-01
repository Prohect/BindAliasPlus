# getCurrentScreen 方法（src/client/java/com/github/prohect/alias/Alias.java）

## 语法

```java
public static Screen getCurrentScreen()
```

## 返回值

`BindAliasClient.currentScreen` 中的当前 `Screen` 实例；如果没有打开的界面，则为 `null`。

## 备注

`BindAliasClient.currentScreen` 的便捷访问器，该字段由 `MinecraftClientMixin` mixin 每刻设置。所有其他界面类型辅助方法都委托给此方法。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [isUnderAnyScreen](isUnderAnyScreen.md) | 当此方法返回非 null 时返回 `true` |
| [isUnderTextInputScreen](isUnderTextInputScreen.md) | 检查特定界面类型 |
| [BindAliasClient](BindAliasClient.java/BindAliasClient.md) | 更新 `currentScreen` 的位置 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
