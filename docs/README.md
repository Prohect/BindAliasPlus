# Docs

The docs of this project follow an MSDN-style doc schema.
That is:
each source file is a directory (`/<locale>/<source_file_name>/`, e.g. `/en-US/Config.java/`) with a `README.md` containing an overview that follows the [overview schema](#schema-for-overview-for-a-source-file).
Each top-level item of a source file is a markdown file in that directory (e.g. `/en-US/Config.java/load_config.md`) that follows the [item schema](#schema-for-top-level-item-for-a-source-file).
Naming follows language conventions. Methods of classes or enums have a doc file name like `ClassName_method_name.md`.

## Schema for Overview for a Source File

| Section               | Description                          |
| --------------------- | ------------------------------------ |
| **Title**             | `# ModuleName type (<project_name>)` |
| **Short description** | One-paragraph summary                |
| **<item_type>**       | Table for items of this type         |
| **See Also**          | Table for see-alsos                  |

## Schema for Top Level Item for a Source File

| Section               | Description                                                              |
| --------------------- | ------------------------------------------------------------------------ |
| **Title**             | `# ItemName type (<source_file_name>)`                                   |
| **Short description** | One-paragraph summary                                                    |
| **Syntax**            | Code block with full signature                                           |
| **Parameters**        | Per-parameter description (functions/methods)                            |
| **Members**           | Per-field description (structs/classes/enums)                            |
| **Return value**      | What the function/method returns                                         |
| **Remarks**           | Algorithms, important side effects, examples, edge cases, platform notes |
| **Requirements**      | Table of module, callers, callees, API, privileges                       |
| **See Also**          | Table for see-alsos                                                      |

## Cross-references between items use relative markdown links

[ItemName](en-US/source_file.ext/ItemName.md)

[return value for SomeType::method()](en-US/source_file.ext/SomeType.md#return-value)

[another_function](../docs/en-US/other_file.ext/another_function.md)

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
