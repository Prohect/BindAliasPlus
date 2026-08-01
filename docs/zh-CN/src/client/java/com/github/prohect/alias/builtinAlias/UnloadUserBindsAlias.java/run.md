# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/UnloadUserBindsAlias.java）

移除所有在运行时创建（非 CFG）的按键绑定，并清理关联的别名。

## 语法

```java
public com.github.prohect.alias.builtinAlias.UnloadUserBindsAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| args | String | 未使用（一次性别名，忽略） |

## 备注

**算法：**

1. 创建 `toRemove`（按键）和 `aliasesToRemove`（别名名称）列表。
2. 遍历 `BINDING_PLUS`：对每个 `fromCFG() == false` 的绑定：
   - 将其按键添加到 `toRemove`。
   - 将非空的 `aliasNameOnKeyPressed()` 和 `aliasNameOnKeyReleased()` 名称收集到 `aliasesToRemove`。
3. 从 `BINDING_PLUS` 中移除每个按键。
4. 从 `aliasesWithoutArgs_fromBindCommand` 中移除每个别名名称。
5. 如果不在静默模式，记录数量。

**返回值：** `this`（流畅式返回）。

**副作用：** 移除运行时创建的按键绑定并清理关联的自动创建别名。CFG 加载的绑定被保留。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [UnloadUserBindsAlias](UnloadUserBindsAlias.md) | 类概览 |
| [UnloadCFGBindsAlias](../UnloadCFGBindsAlias.java/run.md) | 移除 CFG 加载的绑定（反向操作） |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
