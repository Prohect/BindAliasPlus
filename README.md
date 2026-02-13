# BindAliasPlus

A Fabric **client-side** mod that lets you create **aliases** (macros) and **bind them to keys/mouse buttons**, so one key press can run a full sequence of actions — similar to a “bind/alias config” workflow.

<!-- languages -->
- 🇺🇸 [English](README.md)
- 🇨🇳 [中文 (简体)](README_CN.md)

> Client-only: servers do not need to install this mod.

---

## Installation

1. Install [Fabric Loader](https://fabricmc.net/use/) for your Minecraft version.
2. Download the mod from Modrinth: https://modrinth.com/mod/bind-alias-plus/versions
3. Put the `.jar` into your Minecraft `mods` folder.
4. Launch Minecraft.

---

## Core idea

You define an **alias** as a list of **steps**, then bind a key to run it.

- Steps are separated by **spaces**.
- A step can be:
  - an alias with no args: `jump`
  - an alias with args: `swapSlot\10\39` (args are separated by backslash `\`)

You can bind keys so that:
- on **press** it runs one alias
- on **release** it runs another alias (typical `+something` / `-something` pattern)

---

## Syntax

### A) Alias definitions are split by spaces

An alias definition is a sequence of steps **split by space ` `**:

- `equipElytra jump wait\1 jump swapSlot\19 +use -use`

### B) **Step arguments** are split by backslash `\`

If a step contains `\`, the part before the first `\` is the alias name, and the rest are its arguments:

- `swapSlot\10\39` → alias name `swapSlot`, args: `10`, `39`
- `wait\20` → alias name `wait`, args: `20`

### C) Spaces inside a single **step argument**

If a single **step argument** must contain spaces, wrap that argument in **double quotes**.

This matters mostly for the built-ins that send chat/commands, or for nested definitions (see next section).

---

## Nested definitions (important): `BuiltinAliasWithGreedyStringArgs` and `;`

Some built-in aliases take a **greedy string** payload so you can define / bind / unbind / chat / run commands from inside another alias:

- `alias`
- `bind`
- `unbind`
- `say`
- `sendCommand`

### The key trick: avoid quotes by using `;`

Outer parsing always splits alias steps by **spaces**.

So if you write a nested payload that contains spaces, you normally need quotes to keep it as a single argument.

However, for the greedy-string built-ins **you can also avoid quotes** by writing the nested payload as a single token and using **semicolon `;`** to separate pieces inside that token.

Examples (both work):

- With quotes (payload has spaces):
  - `/alias makeJumpAlias alias\"jump +jump wait\1 -jump"`

- Without quotes (payload is one token, use `;` as separators):
  - `/alias makeJumpAlias alias\jump;+jump;wait\1;-jump`

### How greedy-string built-ins process the payload

- `alias\...`, `bind\...`, `unbind\...`:
  - they **replace `;` with space ` `** before sending the final chat command to the game.
  - This is what makes `/alias makeJumpAlias alias\jump;+jump;wait\1;-jump` work: it sends a chat command `/alias jump +jump wait\1 -jump` when `makeJumpAlias` is called.

- `say\...` and `sendCommand\...`:
  - they **do NOT replace `;`**.
  - Whatever you put in their payload is sent as-is (so `;` stays as a literal character).

---

## Commands

### `/alias <name> <definition>`
Create or replace a **user alias**.

Notes:
- You **cannot overwrite** built-in aliases.
- User aliases run by expanding the definition into steps.

Example:
- `/alias pearl swapSlot\12 +use wait\1 -use swapSlot\12`

### `/bind <key> <definition-or-aliasName>`
Bind a key/mouse button.

Behavior:
1. If `<definition-or-aliasName>` matches an existing alias name (including `+name`/`-name` forms), it binds to that alias.
2. Otherwise it treats it as an inline definition and creates an internal alias for the bind.

Press/release behavior:
- If your bind definition contains `+something` and/or `-something`, the mod can derive an “opposite” release side automatically from those `+/-` steps.

Examples:
- `/bind g jump`
- `/bind g +forward wait\10 -forward`

### `/bindByAliasName <key> <aliasName>`
Bind a key directly to an existing alias name.

Examples:
- `/bindByAliasName mouse5 +fly`
- `/bindByAliasName g jump`

### `/unbind <key>`
Remove a bind.

Example:
- `/unbind mouse5`

### `/reloadCFG`
Reload config file from disk.

---

## Configuration file

Path:
- `config/bind-alias-plus.cfg`

Rules:
- One command per line
- Leading `/` is optional
- `#` starts a comment line

Example:

- `alias jump +jump wait\1 -jump`
- `alias +bow swapSlot\11 +use`
- `alias -bow -use swapSlot\11`
- `bind mouse4 +bow`

Reload after editing:
- `/reloadCFG`

---

## Built-in aliases

BindAliasPlus ships with built-in aliases you can call inside your alias definitions.

### 1) Built-ins with arguments (use `\` between args)

| Alias | Arguments | What it does | Example |
|---|---:|---|---|
| `log\text` | text | Log message to console (debug) | `log\Hello` |
| `slot\n` | `n=1..9` | Select hotbar slot | `slot\3` |
| `swapSlot\a\b` | `a,b` | Swap two slots | `swapSlot\10\39` |
| `swapSlot\a` | `a` | Swap slot `a` with the **currently selected hotbar slot** | `swapSlot\19` |
| `wait\ticks` | ticks | Delay execution (`20 ticks = 1 second`) | `wait\20` |
| `yaw\deg` | deg | Add to yaw (relative) | `yaw\90` |
| `pitch\deg` | deg | Add to pitch (relative) | `pitch\-30` |
| `setYaw\deg` | deg | Set yaw (absolute) | `setYaw\180` |
| `setPitch\deg` | deg | Set pitch (absolute) | `setPitch\0` |
| `alias\payload` / `alias\"payload"` | payload | Create/replace an alias. If you want to avoid quotes, you can write the payload as one token and use `;` (it will be converted to spaces). | `alias\jump;+jump;wait\1;-jump` |
| `bind\payload` / `bind\"payload"` | payload | Bind a key. If you want to avoid quotes, write the payload as one token and use `;` (it will be converted to spaces). | `bind\mouse4;+bow` |
| `unbind\key` | key | Unbind a key. (No special `;` handling needed.) | `unbind\g` |
| `say\text` / `say\"text"` | text | Send a chat message. `;` is NOT special here (it is sent literally). Quotes only needed if text has spaces. | `say\"hello world"` |
| `sendCommand\cmd` / `sendCommand\"cmd"` | cmd | Send a command (no leading `/`). `;` is NOT special here. Quotes only needed if command has spaces. | `sendCommand\"gamemode creative"` |

#### Slot numbering for `swapSlot`

Slots follow Minecraft’s internal indexing in this mod’s UI docs:

- `1-9` → hotbar
- `10-36` → inventory
- `37-40` → armor slots (37 feet … 40 head)
- `41` → offhand

### 2) Aliases without arguments (direct actions)

These are available by default and are convenient for press/release patterns:

| Alias | Equivalent to | What it does |
|---|---|---|
| `+attack` / `-attack` | `builtinAttack\1` / `builtinAttack\0` | Hold/release left-click |
| `+use` / `-use` | `builtinUse\1` / `builtinUse\0` | Hold/release right-click |
| `+forward` / `-forward` | `builtinForward\1` / `builtinForward\0` | Hold/release forward |
| `+back` / `-back` | `builtinBack\1` / `builtinBack\0` | Hold/release back |
| `+left` / `-left` | `builtinLeft\1` / `builtinLeft\0` | Hold/release left |
| `+right` / `-right` | `builtinRight\1` / `builtinRight\0` | Hold/release right |
| `+jump` / `-jump` | `builtinJump\1` / `builtinJump\0` | Hold/release jump |
| `+sneak` / `-sneak` | `builtinSneak\1` / `builtinSneak\0` | Hold/release sneak |
| `+sprint` / `-sprint` | `builtinSprint\1` / `builtinSprint\0` | Hold/release sprint |
| `drop` / `dropStack` | `builtinDrop\0` / `builtinDrop\1` | Drop one / whole stack |
| `swapHand` | — | Swap main-hand and offhand |
| `cyclePerspective` | — | Cycle camera perspective |
| `FPS` / `TPS` / `TPS2` | `builtinSetPerspective\0/1/2` | Set specific perspective |
| `+silent` / `-silent` | `builtinSilent\1` / `builtinSilent\0` | Suppress/restore bind/alias feedback messages |
| `reloadCFG` | — | Reload config file |

---

## Examples

### Elytra + firework (press-and-hold)

Put:
- Elytra in slot `10` (inventory first row, first slot)
- Fireworks in slot `19` (inventory second row, first slot)

Then:

- `/alias equipElytra swapSlot\10\39`
- `/alias jump +jump wait\1 -jump`
- `/alias +fly equipElytra jump wait\1 jump swapSlot\19 +use -use`
- `/alias -fly equipElytra swapSlot\19`
- `/bind mouse5 +fly`

### Quick bow without using a hotbar slot

Put your bow in slot `11`:

- `/alias +bow swapSlot\11 +use`
- `/alias -bow -use swapSlot\11`
- `/bind mouse4 +bow`

### Toggle bind pattern (rebind on press)

You can build “press to switch state” scripts by rebinding a key to another alias.
If you do that, `+silent/-silent` can be used to avoid chat spam from bind feedback.

(Example idea: press once to bind to state2, press again to bind back to state1. You can have multiple states.)

---

## Notes / limitations

- Keybind triggers are ignored while you are typing in: chat, sign editor, book editor, command block screen.
- Automation can be suspicious on some servers (anti-cheat). Use responsibly.

---

## License

[CC0-1.0](LICENSE)
