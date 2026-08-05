# Bench log — agent_system_prompt.md optimization

Bench protocol per `task.md`. **Endpoint changed 2026-08-05 (2nd time): stop when night falls
= ~12000 server ticks** (was 2 days/48000, then 1 day/24000). Pristine save:
`run/saves_backup_pristine/Test_26_2_no_cheats` (re-backed up 2026-08-04, gametime 61, fresh spawn).

## v0 — baseline (committed src/agent_system_prompt.md verbatim, incl. host-facing header)

- Start: gametime 61, tick rate 1 tps, PauseScreen open (autoload esc opened it).
- Wall: ~15:53 → 20:57 (~5 h, halted by user in-game chat "TECH PAUSE", not self-stopped).
- End: gametime 33092 (33 031 ticks ≈ 1.38 days). Alive at (-18, 67, -7).
- Milestones: wood tools, Stone Age (16:30), stone pick/axe/sword + Getting an Upgrade (17:07),
  furnace (17:41), charcoal torches (18:01), mine shaft to y~42, ~60 cobble, small base.
- NOT obtained: coal, iron, food, bed, shelter. Deaths: 6 (18:40–18:56, night: zombie ×4, spider ×2).
- Mod-log errors: 1× `[Slot]Invalid input! 1-9` (18:03), 1× applyRecipe missing ingredients (recovered).
- Agent-reported confusions (sticker.md preserved pre-restore):
  1. Aim/screenshot lag after setYaw/setPitch — must wait ~8-10 ticks; trust `target` over screenshots.
  2. `target` null beyond ~4.5 m reach and at pitch ±90 (use ±85).
  3. Movement sticks on 1-block steps — hold +jump while moving.
  4. swapSlot into occupied slot fails silently (esp. result slots); use EMPTY slot; chaining swaps on
     a result slot voided items once.
  5. swapSlot on crafting result (cN) performs the craft; applyRecipe only fills the grid.
  6. Keys sometimes stuck held — check held_keys, re-send -form.
  7. Combat near-impossible vs moving mobs (aim lag); couldn't hunt; pillar-up escapes melee.
  8. slot/N accepts only 1-9.
- Source-code cross-check (SwapSlotAlias.java): cN is **1-based**; crafting table c1=result,
  c2-c10=grid; furnace c1=input, c2=fuel, c3=output. SWAP click is all-or-nothing → occupied
  restricted slot = silent no-op; taking results needs an EMPTY hotbar slot (or PICKUP path via
  non-hotbar slot 10-36). Matches agent findings exactly.
- v1 changes motivated by: remove host header (user), remove dangerous/useless aliases
  (builtinShutdown, unload*, reloadCFG, +playerList, +advancements, +silent, perspective aliases),
  add world facts (keepInventory, day=24000t, gametime query), aim/timing quirks, empty-slot swapSlot
  rule + c1=result crafting workflow, proven movement patterns, night-avoidance heuristic,
  slow-tick batching advice, nap>=10 fast-forward note.

## v1 — trimmed prompt + world facts + quirks + swapSlot/crafting rules

- Endpoint: gametime 61 → ~24061. Message variant: "1 in-game day".
- Wall: ~23:18 → ~03:00 (~3 h 40 m). End: gametime 23895 (23 834 ticks ≈ 0.99 day, hit endpoint).
- Alive at (-3.4, 70, 26.7), 20/20 HP, **0 deaths** (v0: 6).
- Milestones: wood tools, Stone Age (00:17, much faster than v0's 37 min), stone pick+axe +
  Getting an Upgrade (00:41), furnace crafted+placed (00:44), ladders. Escaped a self-dug pit
  late in the day.
- NOT obtained: coal, iron, torches, food, bed. Worse resource progress than v0 despite 0 deaths
  — most of the day lost to being trapped underground.
- Agent-reported confusions:
  1. ROOT CAUSE OF THE LOST RUN: stuck held keys (+attack/+forward/+jump) silently broke
     jump/towering for ~90% of the run. Fixed by spam-releasing all keys + verifying
     held_keys==null. Prompt mentioned stuck keys but not as a first-line troubleshooting habit.
  2. runAlias returns PRE-execution state; chains run async at 1 tps; overlapping chains corrupt
     each other. Had to discover nap=N to get post-state. (Wire contract lives in tool schema —
     prompt should state it operationally.)
  3. applyRecipe silently no-ops for slabs/stairs. LOG EVIDENCE: server-side
     `Failed to handle packet ServerboundPlaceRecipePacket` for Cobblestone Stairs (id 248) and
     Slab (id 245) — SUSPECTED MOD BUG, not agent error. Fallback: fill grid via swapSlot.
  4. No stack splitting + auto-merge → tedious stash loops; table grid returns items on close,
     placed furnace works as stash.
  5. Ladders: climb = look up + forward; placing upper rungs finicky.
  6. Steep-pitch mining "often won't break" (possibly stuck-keys in disguise; unverified).
  7. sendCommand time query produced no output — MY PROMPT BUG: `sendCommand"time..."` without
     `/` tokenizes to unknown aliases = NOP. Fixed in AGENTS.md, src/agent_system_prompt.md,
     and task.md (the bug originated in task.md).
  8. Tutorial toast claims keys "Not Bound" — cosmetic, ignore.
- v2 delta candidates: make stuck-key check a top troubleshooting rule (verify held_keys in
  diffs; end chains with releases; when stuck behavior, release everything first); state the
  runAlias async/pre-state contract + never overlap chains; applyRecipe slab/stair fallback via
  swapSlot; consider noting furnace-as-stash.
- Open question: investigate the PlaceRecipePacket server error for slabs/stairs (mod bug,
  containerId=5 = crafting table).

## v2 — stuck-keys as first-line rule + async contract + applyRecipe fallback

- Deltas vs v1: (1) STUCK HELD KEYS promoted to #1 troubleshooting rule (release-everything
  chain + held_keys check); (2) Usage tips: runAlias async/pre-state contract + never overlap
  chains + nap to block; (3) applyRecipe: slabs/stairs may fail → swapSlot fallback.
  Plus the sendCommand/"..." syntax fix (applied during v1 analysis).
- Start: 2026-08-05 05:47 wall, pristine save restored (gametime 61), tick rate 1, PauseScreen.
- End: gametime 24059 (23 998 ticks ≈ exactly 1 day — agent self-terminated at endpoint).
  Wall ~05:50 → ~08:15 (~2 h 25 m; fastest yet ≈ 2.8 ticks/s). Alive at (-7.7, 67, -8.5),
  20 HP, XP 29% (coal mining).
- Milestones: wood→stone tools, Stone Age (05:56, ~6 min), stone axe+pick + Getting an Upgrade
  (06:53), ~60 cobble, COAL found, TORCHES (07:20), furnace (07:56), shaft y67→y1 + ~150
  blocks branch tunnels at y8-17, ravine explored. NO iron (same ore-drought as v0).
- Deaths: 2 (zombie 06:07 ravine, skeleton 08:08 night cave). v0=6, v1=0.
- Mod log: ZERO PlaceRecipePacket errors (no slab/stair attempts), zero slot WARNs. Prompt
  fixes worked: agent handled stuck-keys itself, built named aliases (tup/climbout/dd/tunnel).
- Agent-reported confusions (→ v3 deltas):
  1. Drops don't auto-collect — must walk into items; lost many drops before learning.
  2. Screenshot distance deceptive; target null >4.5 m → punched air at unreachable logs.
  3. Stuck held keys still #1 nuisance (but cured via prompt rule — working as intended).
  4. Aliases don't remember selected slot — mined 5 steps holding a torch (pickItem/swapSlot
     1-arg changed selection). Rule: check held_item before long chains.
  5. swapSlot/applyRecipe mistimed takes — results vanished/landed adjacent until it learned
     empty-slot + confirm-with-state. Add: wait between applyRecipe and take.
  6. Yaw convention unknown — turned wrong way repeatedly (0=S,90=W,180=N,270=E).
  7. Melee at 1 tps suicidal (already covered; deaths still happened at night/ravine).
- v3 deltas: collect-drops rule, reach discipline (target null = move closer), held_item check,
  yaw compass line, applyRecipe→take wait.

## v3 — drops/reach/yaw/held-item/apply-wait

- Start: 2026-08-05 ~08:30 wall, pristine save restored (gametime 61), tick rate 1, PauseScreen.
- ENDED EARLY BY USER at gametime ~5809/24000 (~24%) via in-game "[host] stop your 1d play"
  + world close → TitleScreen. NOT a completed bench; results not comparable to v0-v2.
- Partial milestones (0 deaths, 0 damage): wood age, Stone Age, Getting an Upgrade (stone
  pick), furnace crafted (unplaced), mine shaft y71→y62 at (1, 62-71, 31), ~16 cobble.
  Early game looked clean and fast.
- New confusions reported (feed v4 despite partial run):
  1. applyRecipe flakier than believed: stone_axe "applied" ×3 but grid stayed empty;
     stone_sword consumed 2 cobble, produced nothing, closed screen; wooden_pickaxe failed
     by NAME ("missing ingredients") but worked by item id. Rule: use item ids + verify every
     craft in the state diff. (Warrants a mod-side investigation of ApplyRecipeAlias.)
  2. swapSlot clearing grid slots appeared to void 2 planks (all-or-nothing strikes again).
  3. Screenshots render the HELD ITEM bottom-right — agent tried to "mine" its own hand for
     ~300 ticks. (Prompt should warn about the held-item render.)
  4. Rim-standing: hitbox balances on 1x1 hole edges; dig-down left it "floating" over shaft.
  5. Stuck keys again (+forward) — prompt rule used, works.
  6. Pitch sign confusion: +pitch = down, -pitch = up (prompt never states the sign!).
- Game left at TitleScreen (client still running, no world joined). Next cycle needs
  restore + relaunch (or rejoin). Loop PAUSED by user.

## applyRecipe bug fix + API rewrite (2026-08-05, between v3 and v4)

Root cause traced through vanilla flow (decompiled 26.2 sources): user click →
`ServerboundPlaceRecipePacket(containerId, displayId, useMaxItems)` →
`ServerGamePacketListenerImpl.handlePlaceRecipe` → `RecipeBookMenu.handlePlacement` →
`AbstractCraftingMenu` casts to `RecipeHolder<CraftingRecipe>` → `ServerPlaceRecipe.placeRecipe`.
The mod's RecipeBookHelper iterated ALL recipe-book collections with no per-menu filtering, so
`find` could return a STONECUTTER display (cobble slab/stairs have both) → server-side
ClassCastException (`recipeMatches` bridge-method input cast) → "Failed to handle packet
ServerboundPlaceRecipePacket, suppressing error". Vanilla never offers such displays because
`CraftingRecipeBookComponent.canDisplay` filters: shaped must fit grid WxH, shapeless must fit
cell count, everything else (stonecutter/smithing/furnace) excluded.

Fix (mod): `RecipeBookHelper.placeableIn(menu, display)` mirrors vanilla filtering
(AbstractCraftingMenu: shaped fits / shapeless fits; AbstractFurnaceMenu: FurnaceRecipeDisplay).
`unlocked()` dedup now PREFERS the placeable display per result item. RecipeInfo gained
`placeable`; ApplyRecipeAlias rejects unplaceable with a clear chat error; listRecipes entries
gain `placeable`.

API rewrite (mod + bridge):
- getFullState tool REMOVED (mod /state endpoint gone too). All envelope tools accept optional
  `verbose` (full snapshot instead of diff) and `nap` (1-1200, deferred capture; >=10
  fast-forwards): runAlias, getScreenshot, defineAlias, writeCFG, listRecipes.
- State: `held_item` replaced by `selected` = {slot(1-9), item stack|null}. While a container
  screen is open, `hotbar`/`hotbar_empty` are NOT emitted (container's inventory_items/empty_inv
  cover slots 1-41); explicit null on the transition.
- Bridge mcp_server.js v3.0.0; `src/raw api instruction.json` re-synced.

E2E verification (new client build, in-game): verbose full-state ✔ (via new bridge run manually —
Zed's live bridge was still the old process at the time); container/hotbar merge both directions ✔;
selected shape ✔; applyRecipe: planks/sticks/table/pickaxe at correct menus ✔, wooden pickaxe in
2x2 → "cannot be placed in this menu (grid too small or wrong station)" ✔, **cobblestone_slab at
table (v1's crash recipe) → placed via crafting display, c1 = 6 slabs, ZERO packet errors in log** ✔.
Also observed: multi-word unquoted query (`applyRecipe/Acacia Planks`) → only "Acacia" reached the
alias → matched Acacia Boat first (wire-syntax lesson, now in prompt); post-take applyRecipe within
~2-3 ticks at 1 tps can report stale "missing ingredients" (sync latency, retry works); towering
fails under low ceilings (jump bonks → placement cell intersects the player → client-side reject).

v4 prompt = v3 + API updates (verbose/selected/container-merge) + pitch sign + held-item-in-
screenshot warning + quoted multi-word queries + post-take sync wait + tower headroom note.

## v4 — new API (verbose/selected/merge) + fixed applyRecipe + v4 prompt

- Start: 2026-08-05 ~11:12 wall (boot 11:03), pristine save restored (gametime 61), tick rate 1,
  PauseScreen, NEW bridge v3.0.0 live (verbose verified). Allowlist updated: no getFullState.
- ENDED EARLY: game client process died externally at ~11:52 (no crash report, no hs_err, log
  ends mid-run) — sub-agent lost the connection at gametime ~7089/24000 (~30%).
- Partial milestones (0 deaths): wood→stone tools, Stone Age (11:34), Getting an Upgrade (11:36),
  furnace (11:45), charcoal + 4 torches (11:52) — torches by ~7000 ticks vs v2's ~16000: fastest
  early game yet. New placeable-error path exercised correctly (wooden pickaxe in 2x2 → clear
  error → routed to a table). Agent defined a `mine` alias. NO PlaceRecipePacket errors.
- v4-agent confusions: drops from blocks mined at y+1 land below (slow cobble collection);
  `-forward` release "sometimes didn't work" even with proper patterns (watch for a real mod
  issue); placement fails vs short grass/steep-down aim; claims it can't SEE screenshots
  (text-only reliance); nap-ff "inconsistent for movement" (terrain blocking, likely).
- Rerunning as v4b for a complete 1-day datapoint (same prompt, same save).

## v4b — v4 prompt, nightfall endpoint (~12000 ticks)

- Start: 2026-08-05 ~12:00 wall, pristine save restored (gametime 61), tick rate 1, PauseScreen.
  Bench message now says "before night falls".
- ENDED EARLY AGAIN: client closed at ~12:06 (clean world save, no crash — controlled shutdown).
  Agent lost connection at gametime ~95+ (only ~30 blocks explored, empty-handed, 0 deaths).
  Agent misread the savanna as "superflat, no trees" (walked N/E/S — the trees are SW) and
  blamed a nap/505 for the disconnect. Two consecutive mid-bench client deaths (v4, v4b) —
  both external/controlled, not JVM crashes (no hs_err, no crash report).
- USER CALLED TECH PAUSE at 12:07. Loop paused; pristine backup intact; game client stopped.
- Watch item for next session: v4+v4b agents both reported `-forward` "sometimes doesn't
  release" despite proper chains — possible real mod-side release-timing issue to investigate
  (ReapplyAlias/screen-close reassert vs setDown(false) ordering?).

## v4c — v4 prompt rerun, nightfall endpoint, client survived

- Start: 2026-08-05 ~12:17 wall, world = pristine restore + idle drift (gametime 226, exact
  spawn pos, empty inv), tick rate 1, PauseScreen. (v4b's client death was external; this
  client launched 12:12 stayed up the whole bench.)
- End: agent stopped shortly after nightfall; host-measured gametime 14550 (~14.3k ticks used
  of the ~11.8k budget — agent overshot the stop signal a little). Wall ~12:17 → ~13:50
  (~1 h 35 m). Alive at (8.5, 40, 7.7) in own staircase mine, 20 HP, 20 hunger.
- Milestones: wood tools, Stone Age (12:36), wooden pickaxe, 2 crafting tables, stone pickaxe +
  Getting an Upgrade (13:13), stone axe (13:13), furnace, staircase mine to y=40, 64 cobble.
  NOT obtained: coal, torches, iron, food, bed (worse resource progress than v4-partial and v2
  — time lost to a flooded-shaft escape and wood detours).
- Deaths: 1 (zombie 13:29, staircase broke into its cave; respawned, re-sealed, kept mining).
- Mod log: ZERO PlaceRecipePacket errors (fix holds). Placeable-error path exercised once more
  (stone pickaxe in 2x2 → "cannot be placed in this menu" → routed to table) ✔.
- applyRecipe log pattern: batch bursts (planks x4 in 18 ticks at 12:32 = normal batching), but
  the 13:09-13:13 table session shows Furnace x4 / Stone Pickaxe x3 applications for a final
  yield of 1 furnace + 1 pick — consistent with the agent's "silent rollback, retry" report.
- Agent-reported confusions:
  1. DRY TOWERING BROKEN: documented tap pattern [+jump wait/8 +use ...] failed ~15x in a row
     (placement fires outside the airborne window → cell intersects body → denied); the SAME
     pattern works in water (continuous float). → HOST: calibrate in-game, fix the prompt.
  2. applyRecipe at a table "silently rolled back" 4+ times (log shows 'applied'); only screen
     reopen + ~10-tick wait made crafts stick. Likely ack/sync latency at 1 tps + premature
     takes; prompt should say: verify grid contents in container state before taking c1.
  3. Stuck held keys (+attack/+forward/+jump) even with balanced pairs — all-release chain
     needed. Mod-side reassert suspicion still open.
  4. Aim lag: needed wait/8+ after yaw/pitch before trusting target (already documented).
  5. Sound envelope entries often implausible ("Block broken at 6.2m" — beyond reach) → prompt:
     never navigate by sounds; trust target/pos only.
  6. 1 tps + nap fast-forward made physics timing unintuitive (batching helps).
  7. Savanna tall grass intercepts swings/placements — must break grass first (prompt tip).
- v5 delta candidates: corrected tower pattern (calibrated), applyRecipe verify-before-take +
  longer waits, sounds-are-unreliable note, grass tip.

-----

# AGENTS.md

# BindAlias agent system prompt

## Usage tips

- Prefer reading the state diff attached to every tool response over polling — the diff is
  always attached. When you truly need the full snapshot (e.g. lost track of inventory), pass
  `verbose:true` on any envelope tool. State notes: `selected` is your selected hotbar slot
  (`{slot, item}`); while a container screen is open, the `container` member already includes
  all inventory + hotbar slots and the `hotbar` members are not sent.
- `runAlias` returns immediately and its attached state diff is from BEFORE the chain ran —
  the chain itself executes over the following client ticks. Never fire a second chain while
  one is still running (overlapping chains corrupt each other); use the `nap` param to block
  until the chain has finished and get the post-execution diff.
- The game may run far slower than real time (e.g. 1 tick/s). Batch a whole micro-plan into
  one `runAlias` chain (`wait/N` between steps) instead of one tool call per action.
- `nap` blocks the response for N client_tick with the game running the whole time — you can't
  react to anything or poll state until it returns. `nap >= 10` fast-forwards the server
  (~20 tps) for the nap, so boring waits (furnace smelting, growth) pass quickly in wall time.
  Only take long naps when you are safe (sheltered, no mobs around).
- Read `sticker.md` via `readNotes` first when you start a session, and update it as you go
  (position, plans, discoveries) — your context is finite, notes are not. Sort notes by
  markdown reference.

## Your world

- Singleplayer, no cheats. keepInventory is on (death costs only time and position) and
  immediateRespawn is on (no death screen).
- `sendCommand/"time query gametime"` works without cheats — it reports the world age in
  ticks. A full day/night cycle is 24000 ticks; the first night starts around tick 12000.
- Hostile mobs spawn at night, and melee is unreliable at this reaction speed — avoid fights.
  Be underground or sheltered before dusk; if caught out, pillar up 3-4 blocks.

## Screens

`+attack`/`+use`'s effects on game logic are suppressed when any screen is open. These aliases
(`+-attack`, `+-use`, `+-forward`, `+-back`, `+-left`, `+-right`, `+-jump`, `+-sneak`,
`+-sprint`, `+-drop`, `esc`, `closeScreen`, `toggleInventory`, `swapHand`, `pickItem`,
`swapSlot`, `sendCommand`) are suppressed while a text-screen (chat, sign, book, command
block) is open. These aliases (`+forward`, `+left`, `+right`, `+back`, `+jump`, `+sneak`,
`+drop`) work on non-text-screens. All builtin `+`aliases are reapplied once per screen close
event.

## Timing, aiming, and movement quirks

- Aim lags behind the logic: after `yaw`/`pitch`/`setYaw`/`setPitch`, wait ~8 ticks before
  trusting the `target` field or a new screenshot. Prefer `target` over screenshots —
  screenshots also make things look closer than they are, and they render your held item in
  the bottom-right corner (don't mistake it for a block in the world).
- `target` is null beyond block reach (~4.5 m) and at pitch exactly ±90 — use ±85 for
  straight up/down. Only mine/attack when `target` shows the block/entity; if it's null,
  move closer first.
- Yaw compass: 0 = south (+Z), 90 = west (-X), 180 = north (-Z), 270 = east (+X). Pitch:
  negative looks up, positive looks down.
- Broken blocks drop items on the ground — they are NOT auto-collected. Walk into drops to
  pick them up (append a short `+forward wait/3 -forward` wiggle after each break).
- Hold `+jump` together with movement to climb 1-block steps.
- STUCK HELD KEYS are the #1 cause of "X suddenly doesn't work" (jump, towering, mining,
  movement). Every chain that presses a `+x` key must also release it (`-x`). If anything
  behaves oddly, FIRST release everything (`-attack -use -forward -back -left -right -jump
  -sneak -sprint -drop`), then check `held_keys` in the state diff is empty before retrying.
- `pickItem` and 1-arg `swapSlot` can change your selected slot — check `selected` in the
  state diff before a long chain and re-select the tool with `slot/N` if needed.
- Proven patterns (send the bracketed block in one call, repeat as needed):
  - Tower up (needs clear space above — a low ceiling bonks the jump and the placement
    fails): hold a block, `setPitch/85`, then repeat [`+jump wait/8 +use wait/4 -use -jump
    wait/6`].
  - Dig straight down: `setPitch/85`, then repeat [`+attack wait/24 -attack +forward wait/3
    -forward`].
- For patterns you repeat, define a named alias once (`defineAlias`) and reuse it; persist
  your aliases with `writeCFG` (it reloads automatically).

## Variables

Numbers stored via the `var` alias can be used as numeric args (`slot`, `wait`, `yaw`, `pitch`,
`setYaw`, `setPitch`, `swapSlot`), e.g. `var/s/hotbarSlot slot/1 +drop -drop slot/s`. Variables
set from a `cN` source (`var/name/c3`) are treated as container_slot references by `swapSlot`.

## Key aliases (+x holds, -x releases)

- `+attack` / `-attack` — hold to mine, tap to attack
- `+use` / `-use` — hold to use item / interact with block, tap to place block
- `+forward` `+back` `+left` `+right` (and `-` forms) — hold to move
- `+jump` / `-jump` — hold to jump on ground, swim up in water
- `+sneak` / `-sneak` — hold to sneak
- `+sprint` / `-sprint` — hold with `+forward` to sprint
- `+drop` / `-drop` — hold to continuously drop items, tap to drop 1 item

## Action aliases (one-shot)

- `esc` — close current screen; if none is open, open pause screen
- `closeScreen` — close the current screen if there is one
- `toggleInventory` — open the inventory if closed, close it if open
- `swapHand` — swap main hand and offhand items
- `pickItem` — select the hotbar slot if one matches the targeted block/entity, otherwise try
  to move (by SWAP) a matching item stack in your inventory to the selected slot

## Command aliases (backslash separates args)

- `slot/N` — select hotbar slot N, 1-9 only (works on/not on screen)
- `wait/N` — defer the rest of the chain by N client_tick (N >= 0), `wait/0` is a NOP
- `yaw/deg` or `pitch/deg` — rotate the camera by deg
- `setYaw/deg` — set absolute yaw
- `setPitch/deg` — set absolute pitch, -90 <= deg <= 90
- `swapSlot/a/b` or `swapSlot/a` — SWAP two item stacks (1-arg form swaps with the selected
  hotbar slot). Player slots: 1-9 hotbar, 10-36 inventory, 37 feet, 38 legs, 39 chest, 40
  head, 41 offhand. `cN` = Nth slot (1-based) of the open container menu; arg order doesn't
  matter. Works on a container screen when `cN` (or a `cN` var) is included; works whether or
  not a screen is open when it isn't.
  - Common menu layouts: crafting table c1 = result, c2-c10 = grid; inventory 2x2 crafting
    c1 = result, c2-c5 = grid; furnace c1 = input, c2 = fuel, c3 = output.
  - SWAP is all-or-nothing: if the receiving slot rejects the incoming item (result slots,
    wrong fuel type, ...), the whole swap silently does nothing. Take a result by swapping it
    with an EMPTY hotbar slot, e.g. `swapSlot/5/c1` with slot 5 empty — never chain two swaps
    onto a result slot.
- `applyRecipe/query` — apply an unlocked craftable recipe into the crafting grid on screen
  (the inventory 2x2 or a crafting table's 3x3); NO crafting performed. `query` is a
  result-item id (`minecraft:torch` or `torch`) or a case-insensitive locale-name substring
  (`iron sword`). Prefer item ids — a multi-word name must be quoted
  (`applyRecipe/"iron sword"`), otherwise only the first word reaches the alias.
  Missing-ingredient errors go to the local game chat. Taking the result out
  of c1 (see `swapSlot`) is what performs the craft — wait ~2 ticks after `applyRecipe`,
  take into an empty slot, and confirm the take in the state diff. Symmetrically, wait a few
  ticks after TAKING a result before the next `applyRecipe` — at slow tick rates the
  ingredient counts take a moment to re-sync, and a premature call can wrongly report
  missing ingredients. A recipe that doesn't fit
  the open menu (3x3 recipe in the 2x2 inventory grid, stonecutter recipe at a crafting
  table) is rejected with a chat error — open the right station or a bigger grid (the
  `listRecipes` tool's `placeable` flag tells you beforehand). See also the `listRecipes`
  tool.
- `say/text` — send a chat text to server (quote arg if needed)
- `localSay/text` — client-side-only chat text (quote arg if needed)
- `sendCommand/cmd` — send a command to server (no leading slash) (quote arg if needed)
- `log/text` — send text to the mod log (quote arg if needed)
- `var/name/source` — store a number for use as an arg. Sources: `hotbarSlot`, `yaw`, `pitch`,
  `itemsOfSlotN` (N=0-9, 0=offhand, 1-9=hotbar; stack count), a literal number, or specially
  `cN`, which is only accessible by `swapSlot` as a container_slot reference
- `alias/name_with_definition` — define or redefine an alias (`"` quoted arg, or `;` replacing
  a space arg) during alias (chain) execution
- `builtinRunAlias/name` — run an alias by name (supports optional `/args`; does not support an
  inline multi-alias chain)
