# Docs

## Path structure

A source file `<path>/srcfile.suffix` maps to a doc directory `<path>/srcfile.suffix/` under the locale:

```
Source: src/<source-set>/<pkg>/Foo.ext
Doc:    docs/en-US/src/<source-set>/<pkg>/Foo.ext/
```

## File naming

Each doc file is named after the **exact identifier** declared in source, with `.md` suffix — no conversion, no prefix, no type suffix.

```
doc dir:  Foo.ext/
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
[Bar](Bar.ext/Bar.md)

# Other package
[Baz](../../other/Baz.ext/Baz.md)
```

## Parent directory overviews

Any directory with 2 or more children gets a `README.md` listing its contents — descriptions, recommended reading order, and bold entry points. These form a navigable tree from the top-level source root down to each leaf package:

```
docs/en-US/src/README.md            ← links to each source set
  <source-set>/.../<ns>/README.md   ← links to sub-packages, types, entry points
    <sub-pkg>/README.md             ← links to interfaces, base classes, impls/
      impls/README.md               ← links to all implementations
```

Each overview tells the reader: what lives here, what to read first, and how the pieces connect.

## Schema for source file overview (`<File>.ext/README.md`)

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
*Documented for Commit: [`<sha>`](https://<git-host>/<owner>/<repo>/tree/<sha>)*
```

## Locales

| Locale | Overview                           |
| ------ | ---------------------------------- |
| en-US  | [en-US/README.md](en-US/README.md) |
| zh-CN  | [zh-CN/README.md](zh-CN/README.md) |

## CONTRIBUTING

**Workflow for batch doc generation:**
1. `<build-command>` then `<doc-generator-script>`
2. **Commit the empty stubs first** — this records baseline + lets subsequent runs only flag orphans
3. Fill in the stubs with real content, commit the fill
4. This avoids re-generating stubs and makes orphan detection reliable

**Before writing any doc under `docs/`:** check for companion `.md` files in the same directory — particularly `JAVA_DOC_GUIDE.md` or language-specific guides — that may define conventions, schemas, or constraints beyond this general overview.

Prefer reading source to understand the implementation. Read docs only when you need to update them.
Know the project before documenting.
Docs contain project information only — no history.
Update en-US first, cross-check terminology after batch documenting, then translate.
