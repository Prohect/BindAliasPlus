# UnloadCFGAllAlias（src/client/java/com/github/prohect/alias/builtinAlias/UnloadCFGAllAlias.java）

移除所有从配置文件（CFG）加载的别名、按键绑定和变量的一次性别名。继承 `BuiltinAliasWithoutArgs`。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.UnloadCFGAllAlias extends com.github.prohect.alias.BuiltinAliasWithoutArgs<com.github.prohect.alias.builtinAlias.UnloadCFGAllAlias>
```

## 静态初始化器

_无。_

## 备注

**别名名称：** `unloadCFGAll`（内部，以 `unloadCFGAll` 形式暴露）。

**行为：** 依次调用全部三个卸载操作的便捷别名：
1. `UnloadCFGAliasesAlias` — 移除 CFG 加载的用户别名
2. `UnloadCFGBindsAlias` — 移除 CFG 加载的按键绑定
3. `UnloadCFGVarsAlias` — 移除 CFG 加载的变量

**静默模式处理：** 每个子操作通常会记录自己的消息。为避免刷屏，`UnloadCFGAllAlias` 在子操作期间临时启用静默模式，然后记录一条包含三个计数的汇总消息：`"Removed {N} alias(es), {M} keybinding(s), {K} variable(s)"`。

**计数跟踪：** 由于子操作在静默模式下运行且不返回计数，`UnloadCFGAllAlias` 在每个操作前后对条目计数。该计数使用基于流的相关注册表过滤。

**不受影响的内容：** 运行时创建的条目（游戏过程中通过命令创建）和内置条目。

**用例：** 通常在 `reloadCFG` 之前调用以完全重置 CFG 加载的状态，或临时禁用所有 CFG 行为时使用。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [UnloadCFGAliasesAlias](../UnloadCFGAliasesAlias.java/UnloadCFGAliasesAlias.md) | 仅移除 CFG 加载的别名 |
| [UnloadCFGBindsAlias](../UnloadCFGBindsAlias.java/UnloadCFGBindsAlias.md) | 仅移除 CFG 加载的按键绑定 |
| [UnloadCFGVarsAlias](../UnloadCFGVarsAlias.java/UnloadCFGVarsAlias.md) | 仅移除 CFG 加载的变量 |
| [UnloadUserAllAlias](../UnloadUserAllAlias.java/UnloadUserAllAlias.md) | 移除运行时创建的条目（反向操作） |
| [ReloadCFGAlias](../ReloadCFGAlias.java/ReloadCFGAlias.md) | 卸载后重新加载 CFG |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
