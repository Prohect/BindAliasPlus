# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/UnloadCFGBindsAlias.java）

移除所有从配置文件加载的按键绑定，并清理关联的自动创建别名。

## 语法

```java
public com.github.prohect.alias.builtinAlias.UnloadCFGBindsAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| args | String | 未使用（一次性别名，忽略） |

## 备注

**算法：**

1. 创建 `toRemove`（按键）和 `aliasesToRemove`（别名名称）列表。
2. 遍历 `BINDING_PLUS`：对每个 `fromCFG() == true` 的绑定：
   - 将其按键添加到 `toRemove`。
   - 如果 `aliasNameOnKeyPressed()` 非空，添加到 `aliasesToRemove`。
   - 如果 `aliasNameOnKeyReleased()` 非空，添加到 `aliasesToRemove`。
3. 从 `BINDING_PLUS` 中移除每个按键。
4. 从 `aliasesWithoutArgs_fromBindCommand` 中移除每个别名名称。
5. 如果不在静默模式，记录数量。

**返回值：** `this`（流畅式返回）。

**副作用：**
- 从 `BINDING_PLUS` 中移除 CFG 加载的按键绑定。
- 从 `aliasesWithoutArgs_fromBindCommand` 中清理关联的自动创建别名。
- 物理按键映射恢复默认行为。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [UnloadCFGBindsAlias](UnloadCFGBindsAlias.md) | 类概览 |
| [UnloadUserBindsAlias](../UnloadUserBindsAlias.java/run.md) | 移除运行时绑定（反向操作） |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
