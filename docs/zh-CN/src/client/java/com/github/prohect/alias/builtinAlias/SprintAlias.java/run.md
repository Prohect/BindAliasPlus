# run 方法（src/client/java/com/github/prohect/alias/builtinAlias/SprintAlias.java）

解析 +/- 布尔参数并按下或松开疾跑键（Ctrl）。

## 语法

```java
public com.github.prohect.alias.builtinAlias.SprintAlias run(java.lang.String)
```

## 参数

| 名称 | 类型 | 说明 |
|------|------|-------------|
| args | String | `"1"` 疾跑（按下 Ctrl），`"0"` 停止（松开 Ctrl） |

## 备注

**算法：**

1. 通过 `parseArgs(args)` 解析 `args`——设置 `this.flag`（"1" 为 true，"0" 为 false）。
2. 若文本输入界面打开且 flag 为 true，立即返回。
3. 获取原版 `keySprint` 按键绑定。
4. 调用 `key.setDown(flag)` 按下或松开按键。
5. 若为按下（flag=true），递增 `clickCount`。

**返回值：** `this`（流畅式返回）。

**副作用：** 与前进移动结合时启用疾跑。玩家移动更快，但饥饿度消耗增加。疾跑受原版约束限制（饥饿度 > 6、未被阻挡、持续前进移动）。

**界面抑制：** 文本输入界面上抑制按下。松开从不被抑制。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [SprintAlias](SprintAlias.md) | 类概览 |
| [SneakAlias](../SneakAlias.java/run.md) | 潜行键 |
| [ForwardAlias](../ForwardAlias.java/run.md) | 前进移动（疾跑所需） |
| [ReapplyAlias](../ReapplyAlias.java/run.md) | 界面切换后重新应用 |

*Documented for Commit: [28c13970494133bbf3880d2d2e3f8d6153a484fd](https://github.com/Prohect/BindAlias/tree/28c13970494133bbf3880d2d2e3f8d6153a484fd)*
