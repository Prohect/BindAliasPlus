# Docs

The docs of this project follow an MSDN-style doc schema.
That is:
each source file is a directory (`/<locale>/<source_path>/`, e.g. `/en-US/src/client/java/.../alias/Alias.java/`) with a `README.md` containing an overview that follows the [overview schema](#schema-for-overview-for-a-source-file).
Each top-level item of a source file is a markdown file in that directory (e.g. `/en-US/src/client/java/.../alias/Alias.java/getDefinitions.md`) that follows the [item schema](#schema-for-top-level-item-for-a-source-file).

## Path structure

The doc directory mirrors the **full source path** relative to the project root. This makes cross-reference links inferable from either path — the relative relationship between source files matches their doc directories.

```
Source: src/client/java/com/github/prohect/alias/Alias.java
Doc:    docs/en-US/src/client/java/com/github/prohect/alias/Alias.java/

Source: src/main/java/com/github/prohect/BindAliasPlus.java
Doc:    docs/en-US/src/main/java/com/github/prohect/BindAliasPlus.java/
```

## File naming

File names are the **exact identifier as declared in source** — no case conversion, no prefix, no suffix.

| Language | Type (class/struct/enum)   | Method/Function                       |
| -------- | -------------------------- | ------------------------------------- |
| Java     | `Alias.md`, `UserAlias.md` | `getDefinitions.md`, `loadCFG.md`     |
| Rust     | `ApplyConfigResult.md`     | `apply_affinity.md`, `read_config.md` |

The directory already scopes the source file, so the type name is not repeated in method file names. No `.class`, `.struct`, `.interface`, or other suffix.

## Schema for Overview for a Source File

| Section               | Description                                            |
| --------------------- | ------------------------------------------------------ |
| **Title**             | `# TypeName type (<project_name>)`                     |
| **Short description** | One-paragraph summary                                  |
| **&lt;item_type&gt;** | Table for items of this type (Fields / Methods / etc.) |
| **See Also**          | Table for see-alsos                                    |

## Schema for Top Level Item for a Source File

| Section               | Description                                                              |
| --------------------- | ------------------------------------------------------------------------ |
| **Title**             | `# ItemName type (<source_file_path>)`                                   |
| **Short description** | One-paragraph summary                                                    |
| **Syntax**            | Code block with full signature                                           |
| **Parameters**        | Per-parameter description (methods)                                      |
| **Members**           | Per-field description (classes/enums)                                    |
| **Return value**      | What the method returns                                                  |
| **Remarks**           | Algorithms, important side effects, examples, edge cases, platform notes |
| **Requirements**      | Table of module, callers, callees, API, privileges                       |
| **See Also**          | Table for see-alsos                                                      |

## Cross-references between items use relative markdown links

To another item in the same source file:

```markdown
[getDefinitions](getDefinitions.md)
[run return value](run.md#return-value)
```

To an item in another source file — compute the relative path between their doc directories:

```markdown
[Alias](../alias/Alias.java/Alias.md)
[loadCFG](../../BindAliasPlusClient.java/loadCFG.md)
```

## Locales

| Locale | Overview                           |
| ------ | ---------------------------------- |
| en-US  | [en-US/README.md](en-US/README.md) |
| zh-CN  | [zh-CN/README.md](zh-CN/README.md) |

## CONTRIBUTING

Do not read docs until you need to update them; prefer reading source to understand the implementation.
Know enough about this project before documenting.
The docs should only contain information about the project — no history needs to be documented.
Report any code issue to the maintainer if the issue is essential.
Update en-US first; do not consider updating all locales at the same time.
Cross-check terms between docs to ensure they use the same terminology after batch documenting.
Translate to other locales after documenting in en-US is done.

## Documentation on Commit SHA

Always leave a git commit SHA as a clickable link at the bottom of every doc file.

## Current commit

_Documented for Commit: [`<commit_sha>`](https://github.com/<owner>/<repo>/tree/<commit_sha>)_
