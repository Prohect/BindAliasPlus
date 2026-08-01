# parseArgs 方法（src/client/java/com/github/prohect/alias/BuiltinAliasWithBooleanArgs.java）

## 语法

```java
public void parseArgs(String args)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| `args` | `String` | 必须是 `"0"`（松开 / 关）或 `"1"`（按下 / 开） |

## 备注

解析参数字符串并设置 `flag` 字段：

- `"0"` → `flag = false`（松开）
- `"1"` → `flag = true`（按下）
- 任何其他值 → 通过 `BindAliasClient.LOGGER` 记录警告并将 `flag` 设为 `false`

此方法在每个具体子类的 `run()` 方法开头调用。例如，`AttackAlias.run("1")` 调用 `parseArgs("1")`，将 `flag` 设为 `true`，然后 `AttackAlias` 将按下事件注入原版的攻击按键映射。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [flag](flag.md) | 此方法设置的字段 |
| [reapplyToGameKeyMapping](reapplyToGameKeyMapping.md) | 若 `flag` 仍为 true 则调用 `run("1")` |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
