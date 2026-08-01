# jsonEscape 方法（src/client/java/com/github/prohect/mcp/GameStateCollector.java）

## 语法

```java
static String jsonEscape(String s)
```

## 返回值

按 JSON 规则转义并用双引号包裹的输入字符串；输入为 `null` 时返回 `"null"`。

## 备注

共享 JSON 字符串转义工具。转义反斜杠、双引号、换行、回车与制表符。结果用双引号包裹，调用方可直接将其作为 JSON 字符串值拼接。null 输入产生字面量 `"null"`（对应 JSON null）。本类几乎所有方法及 `RecipeBookHelper.recipesJson` 都使用它。

## 另请参阅

| 条目 | 说明 |
|------|-------------|
| [RecipeBookHelper.recipesJson](RecipeBookHelper.java/recipesJson.md) | 外部调用方 |
