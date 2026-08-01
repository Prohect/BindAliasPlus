# commandBindExecute 方法（src/client/java/com/github/prohect/BindAliasClient.java）

## 语法

```java
private int commandBindExecute(java.lang.String, java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `keyName` | `String` | 人类可读的按键名（例如 `"f"`、`"mouse1"`） |
| `args` | `String` | 别名定义链 —— 可以是已存在的别名名或新的内联定义 |

## 备注

将按键绑定到别名定义。首先尝试 `commandBindByAliasNameExecute` —— 若 `args` 字符串匹配已存在的 `AliasWithoutArgs` 名称，则委托给该路径（返回 `1`）。否则将 `args` 视为新的内联别名定义：

1. 生成两个随机的 16 字符别名名（分别用于按下和松开）。
2. 通过 `parseKey` 解析 `keyName` —— 未知时返回 `2`。
3. 用 `args` 创建 `UserAlias`，以第一个随机名存入 `aliasesWithoutArgs_fromBindCommand`。
4. 通过 `Alias.getOppositeDefinition(args)` 计算相反定义（例如 `+attack` → `-attack`）。若非空，以第二个随机名存储第二个 `UserAlias`。
5. 将 `BindAliasKeyBinding` 放入 `BINDING_PLUS`，把按键映射到生成的别名名。

成功（别名已创建并绑定）时返回 `3`。私有的 3 参数重载增加了 `fromAutoload` 跟踪，用于 CFG 来源的绑定。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [commandBindByAliasNameExecute](commandBindByAliasNameExecute.md) | 已存在别名名称的优先尝试路径 |
| [BindAliasKeyBinding](BindAliasKeyBinding.md) | 存储在 `BINDING_PLUS` 中的绑定记录 |
| [UserAlias](../alias/UserAlias.java/UserAlias.md) | 创建用于包装内联定义 |
| [parseKey](parseKey.md) | 将按键名字符串转换为 `InputConstants.Key` |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
