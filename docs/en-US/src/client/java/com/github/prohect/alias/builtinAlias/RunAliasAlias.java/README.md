# RunAliasAlias

Builtin alias that invokes another alias by name. Usage: `builtinRunAlias\name` or `builtinRunAlias\name\extraArgs`.

## Fields

_No public/protected fields._

## Methods

| Name | Signature | Description |
|------|-----------|-------------|
| [run](run.md) | `run(String args)` | Split args into name+extraArgs, look up alias, invoke `run(extraArgs)` |

## See Also

| Item | Description |
|------|-------------|
| [AliasAlias](../AliasAlias.java/README.md) | Define aliases at runtime |
| [UserAlias](../../UserAlias.java/README.md) | User-defined alias chain execution |

*Documented for Commit: [6bc6cc0a92af813b68e7afd18dbda0298388962a](https://github.com/Prohect/BindAlias/tree/6bc6cc0a92af813b68e7afd18dbda0298388962a)*
