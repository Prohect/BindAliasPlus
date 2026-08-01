# ApplyRecipeAlias

用于将已解锁的配方放入合成格的字符串参数别名。不执行任何合成——取出产物是单独的操作。错误报告到本地聊天；成功时记录日志。

## 字段

| 名称 | 类型 | 说明 |
|------|------|-------------|
| _（除继承外无其他）_ | | |

## 方法

| 名称 | 签名 | 说明 |
|------|-----------|-------------|
| [run](run.md) | `ApplyRecipeAlias run(String args)` | 按查询查找配方并将其放入打开的配方菜单中 |
| [chatError](chatError.md) | `static void chatError(LocalPlayer, String)` | 向玩家的本地聊天发送错误消息 |

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [RecipeBookHelper](../../../mcp/RecipeBookHelper.java/RecipeBookHelper.md) | 带可合成检查的配方查找 |
| [SwapSlotAlias](../SwapSlotAlias.java/SwapSlotAlias.md) | 将合成产物移出合成格 |
| [BuiltinAliasWithStringArgs](../../BuiltinAliasWithStringArgs.java/BuiltinAliasWithStringArgs.md) | 字符串参数别名的基类 |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
