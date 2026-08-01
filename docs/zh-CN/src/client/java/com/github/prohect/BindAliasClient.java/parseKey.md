# parseKey 方法（src/client/java/com/github/prohect/BindAliasClient.java）

## 语法

```java
private com.mojang.blaze3d.platform.InputConstants$Key parseKey(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `name` | `String` | 按键名字符串 —— 键盘按键（例如 `"f"`、`"key.keyboard.f"`、`"left.shift"`）或鼠标按键（例如 `"mouse1"`、`"mouse.1"`） |

## 返回值

解析后的 `InputConstants.Key`，若名称无法解析则为 `null`。

## 备注

将人类可读的按键名转换为 Minecraft 的 `InputConstants.Key`。尝试两种解析策略：

1. **键盘按键**：调用 `InputConstants.getKey("key.keyboard." + name.toLowerCase())`。Minecraft 的按键名注册表会将 `"f"`、`"left.shift"`、`"key.keyboard.f"` 之类的名称转换为内部按键常量。

2. **鼠标按键**：若键盘查找失败且名称以 `"mouse"` 开头，则解析数字后缀（例如 `"mouse1"` → 按键 1，`"mouse.1"` 也可通过 `toLowerCase` 工作）。返回 `InputConstants.Type.MOUSE.getOrCreate(button - 1)`（0 基，因此鼠标按键 1 → 索引 0）。

无效的鼠标按键号或无法识别的按键名会记录警告并返回 `null`。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [InputConstants.Key](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a) | Minecraft 的按键类型 —— 此方法的返回类型 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
