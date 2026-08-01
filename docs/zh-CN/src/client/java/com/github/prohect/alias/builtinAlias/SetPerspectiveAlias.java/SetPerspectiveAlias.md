# SetPerspectiveAlias（src/client/java/com/github/prohect/alias/builtinAlias/SetPerspectiveAlias.java）

将相机视角设置为第一人称、第三人称背后或第三人称正面的内置别名。继承 `BuiltinAliasWithIntegerArgs`。

## 语法

```java
public class com.github.prohect.alias.builtinAlias.SetPerspectiveAlias extends com.github.prohect.alias.BuiltinAliasWithIntegerArgs<com.github.prohect.alias.builtinAlias.SetPerspectiveAlias>
```

## 静态初始化器

_无。_

## 备注

**别名名称：** `builtinSetPerspective`（内部，通过 `builtinSetPerspective` 暴露）。

**用法：** `builtinSetPerspective\N`，其中 N 为：
- `0` — 第一人称（FPS）
- `1` — 第三人称背后（TPS）
- `2` — 第三人称正面（TPS2）

**行为：** 将相机设置为指定的视角模式。如果视角变化在第一人称和第三人称之间切换，相机实体将相应更新（`setCameraEntity`）。

**参数解析：** 整数参数通过 `VarAlias.resolveInt()` 解析，因此可以是字面数字或变量名。

**输入验证：** 超出 0-2 范围的值会被拒绝并记录警告。仅当目标与当前视角不同时才更改相机。

**与 CyclePerspectiveAlias 的区别：** `SetPerspectiveAlias` 设置绝对视角，而 `CyclePerspectiveAlias` 在每次调用时循环 FPS → TPS → TPS2 → FPS。

**无界面抑制：** 在任何界面上都能工作（它修改相机设置，而不是游戏输入）。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [CyclePerspectiveAlias](../CyclePerspectiveAlias.java/CyclePerspectiveAlias.md) | 循环切换视角（FPS→TPS→TPS2→FPS） |
| [BuiltinAliasWithIntegerArgs](../../BuiltinAliasWithIntegerArgs.java/BuiltinAliasWithIntegerArgs.md) | 整数参数别名的基类 |
| [VarAlias](../VarAlias.java/VarAlias.md) | 用于参数解析的变量系统 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
