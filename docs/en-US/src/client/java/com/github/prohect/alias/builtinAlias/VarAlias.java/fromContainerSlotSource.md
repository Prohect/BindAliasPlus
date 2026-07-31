# fromContainerSlotSource method (src/client/java/com/github/prohect/alias/builtinAlias/VarAlias.java)

Parses a `cN` container-slot source string and returns the 1-based slot number.

## Syntax

```java
private static int fromContainerSlotSource(java.lang.String)
```

## Parameters

| Name | Type | Description |
|------|------|-------------|
| source | String | The source string to parse (e.g., `"c1"`, `"c5"`, `"c12"`) |

## Remarks

**Algorithm:**

1. Trim the source string.
2. If length < 2 or first character is not `'c'`, return `CONTAINER_SLOT_PARSE_ERR`.
3. Parse the substring after 'c' as an integer.
4. If the integer >= 1, return it. Otherwise, return `CONTAINER_SLOT_PARSE_ERR`.

**Return value:** The 1-based container slot number (>= 1), or `CONTAINER_SLOT_PARSE_ERR` (`Integer.MIN_VALUE + 17`) if not a valid `cN` string.

**Sentinel value:** `CONTAINER_SLOT_PARSE_ERR` is chosen to be hard to guess and impossible to collide with a real slot index, making it an unambiguous "not a cN source" signal.

**Why private:** This is an internal helper used only by `VarAlias` methods. External code uses `SwapSlotAlias.parseSlotRef()` which has its own `cN` parsing logic.

## See Also

| Item | Description |
|------|-------------|
| [getValueFromSource](getValueFromSource.md) | Primary caller |
| [SwapSlotAlias](../SwapSlotAlias.java/parseSlotRef.md) | External cN parsing for swap operations |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
