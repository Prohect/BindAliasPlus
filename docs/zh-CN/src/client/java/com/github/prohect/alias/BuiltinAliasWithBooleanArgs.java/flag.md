# flag 字段（src/client/java/com/github/prohect/alias/BuiltinAliasWithBooleanArgs.java）

## 语法

```java
public boolean flag
```

存储别名的当前布尔状态：按键被按下 / 按住时为 `true`，松开时为 `false`。

## 备注

由 `parseArgs(args)` 设置——`"1"` → `true`，`"0"` → `false`。具体子类的 `run()` 方法读取它来决定操作（向原版按键映射注入按下或松开）。`reapplyToGameKeyMapping()` 也读取它来决定界面切换后是否重新同步。

默认值为 `false`（`boolean` 的字段默认值）。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [parseArgs](parseArgs.md) | 从参数字符串设置此字段 |
| [reapplyToGameKeyMapping](reapplyToGameKeyMapping.md) | 使用此字段重新同步按住的按键 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
