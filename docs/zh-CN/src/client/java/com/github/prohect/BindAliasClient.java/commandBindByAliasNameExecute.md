# commandBindByAliasNameExecute 方法（src/client/java/com/github/prohect/BindAliasClient.java）

## 语法

```java
private int commandBindByAliasNameExecute(java.lang.String, java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `keyName` | `String` | 人类可读的按键名（例如 `"f"`、`"mouse1"`） |
| `aliasName` | `String` | 已存在的别名名，可选地以 `+` 或 `-` 为前缀 |

## 备注

按名称将按键绑定到已存在的 `AliasWithoutArgs`，支持 `+`/`-` 切换模式。算法如下：

1. 在 `Alias.aliasesWithoutArgs` 中查找 `aliasName`。
2. 若未找到，尝试去掉前导 `+` 或 `-` 后查找剩余部分。
3. 确定按下/松开配对：
   - 若原名以 `+` 开头，则按下别名是 `+name`、松开别名是 `-name`（以 `-` 开头则相反）。
   - 若名称是裸名（无 `+`/`-` 前缀）但其 `-` 对应物存在，则两者都用。否则只绑定裸名（松开别名为空）。
4. 通过 `parseKey` 解析按键名。
5. 将 `BindAliasKeyBinding` 放入 `BINDING_PLUS`，包含解析出的按下与松开别名名。

返回码：`1` = 成功，`2` = 别名未找到，`3` = 别名找到但无法推断 `+`/`-` 模式（非切换），`4` = 未知按键。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [BindAliasKeyBinding](BindAliasKeyBinding.md) | 存储在 `BINDING_PLUS` 中的绑定记录 |
| [parseKey](parseKey.md) | 将按键名字符串转换为 `InputConstants.Key` |
| [Alias](../alias/Alias.java/Alias.md) | 此处查询的 `aliasesWithoutArgs` 注册表 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
