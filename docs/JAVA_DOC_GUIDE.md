# Java Doc Drafting Guide

## General

Read the source file before writing any doc. The source is the authority — docs explain, not invent.
Write in English. Keep it concise. One paragraph per section unless detail is warranted.

## Class / Interface / Enum / Record doc (`ClassName.md`)

Fill the **Remarks** section:

- **Purpose**: What is this type for? One sentence.
- **Lifecycle**: When is it instantiated? Singleton? Per-call?
- **Thread safety**: Is it thread-safe? If so, how?
- **Key collaborators**: What other types does it depend on or work with?

Fill the **See Also** table:

- Parent class / implemented interfaces
- Key subtypes (for interfaces/abstract classes)
- Classes that use this type heavily

Example:
```markdown
# UserAlias class (src/client/java/com/github/prohect/alias/UserAlias.java)

## Remarks

Represents a user-defined alias chain parsed from the config file.
Each `UserAlias` decodes its definition string into a queue of `AliasRecord`
entries, then dispatches them to registered alias implementations.

Not thread-safe. Instances are created by `loadCFG()` and by the `/alias` command.
Uses `Alias.getDefinitions()` and `Alias.getDefinitionSplits()` for parsing,
and lookups into `Alias.aliasesWithoutArgs` / `aliasesWithArgs` for dispatch.

Special-cases `WaitAlias` — when encountered, remaining queue entries are
packaged into a deferred execution via `WaitAlias.run()`.

## See Also

| Item | Description |
|------|-------------|
| [Alias](../Alias.java/Alias.md) | Core interface this class implements via `AliasWithoutArgs` |
| [AliasRecord](../AliasRecord.java/AliasRecord.md) | Record type stored in the alias queue |
| [WaitAlias](builtinAlias/WaitAlias.java/WaitAlias.md) | Special-cased during dispatch |
```

## Method doc (`methodName.md`)

Fill all sections:

### Parameters
Extract parameter names from the source signature. Describe each — what it means, valid values, constraints.

```markdown
## Parameters

| Name | Type | Description |
|------|------|-------------|
| `args` | `String` | The raw definition string from config. Space-delimited alias definitions. May be empty. |
```

### Remarks
- **Algorithm**: Step-by-step what the method does. Edge cases.
- **Side effects**: What state changes? Logging? Network calls?
- **Callers**: Who calls this? (onInitializeClient, command handler, tick, etc.)
- **Error handling**: What happens on invalid input?

### Return value
What is returned and what it means. For `void`, say "Returns nothing." or omit.

### See Also
- Methods it calls
- Methods that call it
- Related types

Example:
```markdown
# decodeArgs2Alias method (src/client/java/com/github/prohect/alias/UserAlias.java)

## Parameters

| Name | Type | Description |
|------|------|-------------|
| `args` | `String` | Raw definition string. Split by `divider4AliasDefinition`, each definition split by `divider4AliasArgs`. |

## Remarks

Parses the definition string into `AliasRecord` entries pushed to `this.aliases`.

Algorithm:
1. Call `Alias.getDefinitions(args)` to split into individual definitions.
2. For each definition, call `Alias.getDefinitionSplits()` to separate name from arguments.
3. Build `AliasRecord` from the first non-blank token (alias name) and remaining tokens (args).
4. Push to `this.aliases` queue.

Definitions with only a name (no args) create records with empty args string.

## See Also

| Item | Description |
|------|-------------|
| [Alias.getDefinitions](../Alias.java/getDefinitions.md) | First-stage split |
| [Alias.getDefinitionSplits](../Alias.java/getDefinitionSplits.md) | Second-stage split |
| [run](run.md) | Consumes the queue built here |
```

## Field doc (`fieldName.md`)

Fields are usually documented in the class README table. If a separate `.md` stub exists (public/protected fields), fill the **Remarks**:

- What this field stores and why.
- Who reads/writes it.
- Thread safety.
- Default value and its meaning.

Example:
```markdown
# blackList4Screen field (src/client/java/com/github/prohect/alias/Alias.java)

## Remarks

List of aliases that are restricted when any screen is open.
When a blacklisted alias is dispatched in `UserAlias.run()`:

- If no screen is open: executes normally.
- If a screen is open and args is `"0"` (key-up): executes (allows releasing).
- If a screen is open and args is not `"0"`: skipped.

Aliases add themselves via `addToScreenBlackList()` during registration.
Checked by `UserAlias.run()` and `UserAlias.runInternal()`.
```

## Static initializer doc (`static-init.md`)

Only exists if the source has an explicit `static { }` block. Document:

- What is initialized.
- Why it's in a static block vs field initializers (e.g., complex logic, reflection, try-catch).
- Any failure modes.

## README.md (overview)

Fill the tables:

### Fields table
List all fields with type and one-line description. Group by visibility (public static, private, etc.).

### Methods table
List all public/protected methods with signature and one-line description. Group by category if there are many (lifecycle, command handlers, utilities, etc.).

### See Also table
Link to related types and their READMEs.

## Cross-references

Use relative paths between doc directories:

| From | To | Link |
|------|----|------|
| Same directory | `run.md` | `[run](run.md)` |
| Same package | `../UserAlias.java/UserAlias.md` | `[UserAlias](../UserAlias.java/UserAlias.md)` |
| Sub-package | `builtinAlias/AttackAlias.java/AttackAlias.md` | `[AttackAlias](builtinAlias/AttackAlias.java/AttackAlias.md)` |
| Different module | `../../../../client/...` | depends on depth |

**Rule**: compute the relative path from the current `.md` file's directory to the target doc directory, then append the target filename.

## Commit footer

Every doc file already has the footer — do not remove or modify it.
