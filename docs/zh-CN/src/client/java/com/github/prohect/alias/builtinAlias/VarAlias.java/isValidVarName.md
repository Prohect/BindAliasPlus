# isValidVarName 方法（src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java）

验证变量名称是否可接受——不能以数字开头。

## 语法

```java
private boolean isValidVarName(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| varName | String | 建议的变量名称 |

## 备注

**验证规则：**
- 不能为 null 或空。
- 不能以数字（0-9）开头。通过正则表达式 `^[0-9].*` 检查。

**返回值：** 名称有效则返回 `true`，否则返回 `false`。

**为什么不允许前导数字：** 此约束防止变量名与字面数字之间的歧义。例如，`5` 是数字，不是变量名。它还确保 `resolveValue("5")` 正确将其视为字面整数，而不是作为变量查找。

**调用者：** 两个 `run()` 重载在存储变量前都会调用此方法。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [run](run.md) | 调用此方法进行验证 |
| [resolveValue](resolveValue.md) | 数字解析（区分名称和数字） |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
