# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/UnloadCFGAllAlias.java）

移除所有 CFG 加载的别名、按键绑定和变量，并记录一条汇总。

## 语法

```java
public com.github.prohect.alias.builtinAlias.UnloadCFGAllAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| args | String | 未使用（一次性别名，忽略） |

## 备注

**算法：**

1. 保存当前 `silentMode` 并将其设置为 `true`（抑制子操作日志）。
2. 实例化并运行 `UnloadCFGAliasesAlias`——通过流过滤统计移除的别名数量。
3. 实例化并运行 `UnloadCFGBindsAlias`——通过流过滤统计移除的绑定数量。
4. 读取 `VarAlias.CFG_VARIABLES.size()` 作为前置计数，然后实例化并运行 `UnloadCFGVarsAlias`。
5. 恢复原来的 `silentMode`。
6. 如果原本不在静默模式，记录汇总：`"Removed {N} alias(es), {M} keybinding(s), {K} variable(s)"`。

**返回值：** `this`（流畅式返回）。

**副作用：** 从 `aliasesWithoutArgs` 中移除所有 CFG 加载的别名，从 `BINDING_PLUS` 中移除所有 CFG 加载的绑定，并从 `GENERAL_VARIABLES` 和 `CONTAINER_SLOT_VARIABLES` 中移除所有 CFG 加载的变量。

**计数准确性：** 别名/绑定计数通过比较每个子操作前后的注册表状态得出（因为它们在静默模式下运行，不报告计数）。变量计数使用操作前 `CFG_VARIABLES` 的大小。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [UnloadCFGAllAlias](UnloadCFGAllAlias.md) | 类概览 |
| [UnloadUserAllAlias](../UnloadUserAllAlias.java/run.md) | 移除运行时条目（反向操作） |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
