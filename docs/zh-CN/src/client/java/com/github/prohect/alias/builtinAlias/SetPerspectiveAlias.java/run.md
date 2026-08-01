# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/SetPerspectiveAlias.java）

解析整数参数（0-2）并设置相机视角。

## 语法

```java
public com.github.prohect.alias.builtinAlias.SetPerspectiveAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| args | String | 整数 0-2：0=FPS，1=TPS（第三人称背面），2=TPS2（第三人称正面） |

## 备注

**算法：**

1. 通过 `parseArgs(args)` 解析 `args`——使用 `VarAlias.resolveInt()` 解析 `flag`（int）。
2. 验证 `flag` 在 [0, 2] 范围内。超出范围时记录警告并返回。
3. 获取当前视角（`options.getPerspective()`）。
4. 获取目标视角（`Perspective.values()[flag]`）。
5. 如果当前 != 目标：
   - 调用 `options.setPerspective(targetPerspective)`。
   - 如果更改在第一人称和第三人称之间跨越，则通过 `mc.setCameraEntity(...)` 更新相机实体。

**返回值：** `this`（流畅返回）。

**副作用：**
- 更改相机视角（立即可见）。
- 跨越 FPS↔TPS 边界时更新相机实体。

**错误处理：**
- Options 为 null：记录警告，返回。
- 范围无效（不是 0-2）：记录带有效范围提示的警告，返回。

**无界面抑制：** 在任意界面上都有效。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [SetPerspectiveAlias](SetPerspectiveAlias.md) | 类概览 |
| [CyclePerspectiveAlias](../CyclePerspectiveAlias.java/run.md) | 循环切换视角 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
