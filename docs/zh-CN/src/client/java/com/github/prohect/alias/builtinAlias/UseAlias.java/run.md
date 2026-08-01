# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/UseAlias.java）

手动解析 "0"/"1" 参数并按下或松开使用/物品键（右键）。

## 语法

```java
public com.github.prohect.alias.builtinAlias.UseAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| args | String | `"1"` 使用物品 / 交互，`"0"` 松开。其他值记录警告。 |

## 备注

**算法：**

1. 通过对 `args` 进行 switch 手动确定 `flag`：
   - `"0"` → flag = false
   - `"1"` → flag = true
   - 默认 → 记录警告，flag 保持 false
2. 如果文本输入界面已打开且 flag 为 true，则立即返回（按下被抑制）。
3. 获取原版 `useKey` 按键绑定。
4. 调用 `key.setPressed(flag)`。
5. 如果 flag 为 true，则递增 `timesPressed` 以产生初始按下刻。

**返回值：** `this`（流畅返回）。

**副作用：** 使用手持物品或与目标方块/实体交互。按住会触发持续使用（进食、拉弓、举盾格挡）。

**界面抑制：** 按下在文本输入界面上被抑制。此外，出于安全考虑，`+use` 被 MCP 的内置保护在**所有**界面上完全抑制。

**为什么手动解析：** 与大多数 BooleanArgs 别名不同，此别名**不**使用 `parseArgs()`。参数被手动检查为 "0"/"1"。无效参数记录警告但保持按键状态不变。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [UseAlias](UseAlias.md) | 类概览 |
| [AttackAlias](../AttackAlias.java/run.md) | 左键对应实现 |
| [ReapplyAlias](../ReapplyAlias.java/run.md) | 界面切换后重新应用 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
