# UnloadUserAllAlias（src/client/java/com/github/prohect/alias/builtinAlias/UnloadUserAllAlias.java）

移除所有在运行时创建（非 CFG）的别名、按键绑定和变量的一次性别名。继承 `BuiltinAliasWithoutArgs`。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.UnloadUserAllAlias extends com.github.prohect.alias.BuiltinAliasWithoutArgs<com.github.prohect.alias.builtinAlias.UnloadUserAllAlias>
```

## 静态初始化器

_无。_

## 备注

**别名名称：** `unloadUserAll`（内部，以 `unloadUserAll` 形式暴露）。

**行为：** 依次调用全部三个运行时清理操作的便捷别名：
1. `UnloadUserAliasesAlias` — 移除运行时创建的用户别名
2. `UnloadUserBindsAlias` — 移除运行时创建的按键绑定
3. `UnloadUserVarsAlias` — 移除运行时创建的变量（通用和容器槽位）

**静默模式处理：** 与 `UnloadCFGAllAlias` 一样，此别名在子操作期间临时启用静默模式，并记录一条汇总：`"Removed {N} alias(es), {M} keybinding(s), {K} variable(s)"`。

**不受影响的内容：** CFG 加载的条目和内置条目。

**变量清理包括：** `GENERAL_VARIABLES`（通用变量）和 `CONTAINER_SLOT_VARIABLES`（容器槽位引用）。计数包含两种类型。

**用例：** 重置所有临时/运行时状态而不影响永久的 CFG 配置。在测试会话结束时或重新运行一系列测试之前很有用。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [UnloadUserAliasesAlias](../UnloadUserAliasesAlias.java/UnloadUserAliasesAlias.md) | 仅移除运行时别名 |
| [UnloadUserBindsAlias](../UnloadUserBindsAlias.java/UnloadUserBindsAlias.md) | 仅移除运行时按键绑定 |
| [UnloadUserVarsAlias](../UnloadUserVarsAlias.java/UnloadUserVarsAlias.md) | 仅移除运行时变量 |
| [UnloadCFGAllAlias](../UnloadCFGAllAlias.java/UnloadCFGAllAlias.md) | 移除 CFG 加载的条目（反向操作） |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
