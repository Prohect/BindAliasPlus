# commandUnbindExecute 方法（src/client/java/com/github/prohect/BindAliasClient.java）

## 语法

```java
private int commandUnbindExecute(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `keyName` | `String` | 人类可读的按键名（例如 `"f"`、`"mouse1"`、`"key.keyboard.f"`） |

## 备注

从 `BINDING_PLUS` 中移除按键绑定。首先通过 `parseKey` 将按键名解析为 `InputConstants.Key`。若按键未知，返回 `0`（无操作）。否则移除该条目并返回 `1`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [parseKey](parseKey.md) | 将按键名字符串转换为 `InputConstants.Key` |
| [BINDING_PLUS](BINDING_PLUS.md) | 按键→别名 绑定注册表 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
