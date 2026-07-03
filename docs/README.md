# Docs

## Path structure

A source file `<path>/srcfile.suffix` maps to a doc directory `<path>/srcfile.suffix/` under the locale:

```
Source: src/module/pkg/Foo.java
Doc:    docs/en-US/src/module/pkg/Foo.java/
```

## File naming

Each doc file is named after the **exact identifier** declared in source, with `.md` suffix — no conversion, no prefix, no type suffix.

```
doc dir:  Foo.java/
files:    Foo.md          ← the class/struct/enum itself
          doSomething.md  ← a method/function
          count.md        ← a field
```

The directory scopes the source file, so the type name is not repeated on methods or fields. The link to any item is inferable from the identifier alone — no need to consult the doc to predict the filename.

## Cross-references

Relative links between doc directories mirror the relative path between source files:

```
# Same file
[doSomething](doSomething.md)

# Sibling (same package)
[Bar](Bar.java/Bar.md)

# Other package
[Baz](../../other/Baz.java/Baz.md)
```

## Schema for overview (`README.md`)

| Section               | Description           |
| --------------------- | --------------------- |
| **Title**             | `# Identifier kind`   |
| **Short description** | One-paragraph summary |
| **&lt;item_type&gt;** | Table of items        |
| **See Also**          | Cross-reference table |

## Schema for item doc

| Section               | Description                                    |
| --------------------- | ---------------------------------------------- |
| **Title**             | `# Identifier kind (<source_file_path>)`       |
| **Short description** | One-paragraph summary                          |
| **Syntax**            | Code block with full signature                 |
| **Parameters**        | Per-parameter description                      |
| **Members**           | Per-field description                          |
| **Return value**      | What it returns                                |
| **Remarks**           | Algorithms, side effects, edge cases, examples |
| **Requirements**      | Dependencies, callers, callees, privileges     |
| **See Also**          | Cross-reference table                          |

## Documentation on Commit SHA

Every doc file ends with the commit it was written for:

```
*Documented for Commit: [`<sha>`](https://github.com/<owner>/<repo>/tree/<sha>)*
```

## Locales

| Locale | Overview                           |
| ------ | ---------------------------------- |
| en-US  | [en-US/README.md](en-US/README.md) |
| zh-CN  | [zh-CN/README.md](zh-CN/README.md) |

## CONTRIBUTING

Prefer reading source to understand the implementation. Read docs only when you need to update them.
Know the project before documenting.
Docs contain project information only — no history.
Update en-US first, cross-check terminology after batch documenting, then translate.
