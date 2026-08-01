# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/SetPerspectiveAlias.java）

解析整数参数（0-2）并设置相机视角。

## 语法

```java
public com.github.prohect.alias.builtinAlias.SetPerspectiveAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| args | String | 整数 0-2：0=FPS，1=TPS（第三人称背后），2=TPS2（第三人称正面） |

## 备注

**算法：**

1. 通过 `parseArgs(args)` 解析 `args`——使用 `VarAlias.resolveInt()` 解析 `flag`（整数）。
2. 校验 `flag` 在 [0, 2] 范围内。若超出范围，记录警告并返回。
3. 获取当前视角（`options.getCameraType()`）。
4. 获取目标视角（`CameraType.values()[flag]`）。
5. 若当前 != 目标：
   - 调用 `options.setCameraType(targetPerspective)`。
   - 若切换跨越第一人称与第三人称之间，通过 `mc.setCameraEntity(...)` 更新相机实体。

**返回值：** `this`（流畅式返回）。

**副作用：**
- 改变相机视角（立即可见）。
- 跨越 FPS↔TPS 边界时更新相机实体。

**错误处理：**
- Options 为 null：记录警告，返回。
- 范围无效（非 0-2）：记录带有效范围提示的警告，返回。

**无界面抑制：** 在任何界面上均有效。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [SetPerspectiveAlias](SetPerspectiveAlias.md) | 类概览 |
| [CyclePerspectiveAlias](../CyclePerspectiveAlias.java/run.md) | 循环切换视角 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
