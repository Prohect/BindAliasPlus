# 文档

## 路径结构

源文件 `<path>/srcfile.suffix` 映射到对应语言下名为 `<path>/srcfile.suffix/` 的文档目录：

```
Source: src/<source-set>/<pkg>/Foo.ext
Doc:    docs/en-US/src/<source-set>/<pkg>/Foo.ext/
```

## 文件命名

每个文档文件都以源码中声明的**确切标识符**命名，加 `.md` 后缀——不做转换，不加前缀，不加类型后缀。

```
doc dir:  Foo.ext/
files:    Foo.md          ← the class/struct/enum itself
          doSomething.md  ← a method/function
          count.md        ← a field
```

目录限定了源文件的范围，因此方法和字段上不会重复类型名。任何条目的链接都可以仅凭标识符推断出来——无需查阅文档即可预知文件名。

## 交叉引用

文档目录之间的相对链接与源文件之间的相对路径一一对应：

```
# Same file
[doSomething](doSomething.md)

# Sibling (same package)
[Bar](Bar.ext/Bar.md)

# Other package
[Baz](../../other/Baz.ext/Baz.md)
```

## 父目录概览

任何包含 2 个及以上子项的目录都会有一个 `README.md` 列出其内容——说明、推荐阅读顺序和加粗的重点入口。这些概览从顶层源码根目录向下延伸到每个叶子包，构成一棵可导航的树：

```
docs/en-US/src/README.md            ← links to each source set
  <source-set>/.../<ns>/README.md   ← links to sub-packages, types, entry points
    <sub-pkg>/README.md             ← links to interfaces, base classes, impls/
      impls/README.md               ← links to all implementations
```

每个概览都会告诉读者：这里有什么、应该先读什么、各部分之间如何关联。

## 源文件概览的结构（`<File>.ext/README.md`）

| 小节                | 说明             |
| --------------------- | --------------------- |
| **标题**             | `# Identifier kind`   |
| **简介**             | 一段话总结 |
| **&lt;item_type&gt;** | 条目表格        |
| **另请参阅**          | 交叉引用表 |

## 条目文档的结构

| 小节          | 说明                                    |
| --------------------- | ---------------------------------------------- |
| **标题**             | `# Identifier kind (<source_file_path>)`       |
| **简介**             | 一段话总结                          |
| **语法**            | 包含完整签名的代码块                 |
| **参数**            | 逐个参数的说明                      |
| **成员**            | 逐个字段的说明                          |
| **返回值**          | 返回的内容                                |
| **备注**            | 算法、副作用、边界情况、示例 |
| **要求**            | 依赖、调用方、被调用方、权限     |
| **另请参阅**          | 交叉引用表                          |

## 提交 SHA 的文档说明

每个文档文件都以撰写所依据的提交结尾：

```
*Documented for Commit: [`<sha>`](https://<git-host>/<owner>/<repo>/tree/<sha>)*
```

## 语言

| 语言 | 概览                            |
| ------ | ---------------------------------- |
| en-US  | [en-US/README.md](en-US/README.md) |
| zh-CN  | [zh-CN/README.md](zh-CN/README.md) |

## 贡献指南

**批量文档生成工作流：**
1. 先运行 `<build-command>`，再运行 `<doc-generator-script>`
2. **先提交空占位文档**——这会记录基线，并让后续运行只标记孤儿文档
3. 用真实内容填充占位文档，提交这次填充
4. 这样可避免重新生成占位文档，并让孤儿检测保持可靠

**在 `docs/` 下撰写任何文档之前：**先检查同一目录下是否有配套的 `.md` 文件——尤其是 `JAVA_DOC_GUIDE.md` 或特定语言的指南——它们可能定义了超出本文通用概览范围的约定、结构或约束。

理解实现时优先阅读源码；只有需要更新文档时才读文档。
撰写文档前先了解项目。
文档只包含项目信息——不含历史。
先更新 en-US，批量撰写文档后交叉核对术语，再翻译。
