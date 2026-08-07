# Bench log — agent_system_prompt.md optimization

Bench protocol per `task.md`. **Endpoint changed 2026-08-05 (2nd time): stop when night falls
= ~12000 server ticks** (was 2 days/48000, then 1 day/24000). Pristine save:
`run/saves_backup_pristine/Test_26_2_no_cheats` (re-backed up 2026-08-04, gametime 61, fresh spawn).

**Guiding principle (user-mandated 2026-08-05, see task.md § Prompt content principle): the
prompt documents facts verified from the fixed program (vanilla/mod code, calibrated in-game
tests); agent confusions recorded here are signals to verify, never text to transcribe;
temporary workarounds must be marked and revisited, never enshrined.**

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

## v4c follow-up — code-verified facts (2026-08-05, pending in-game calibration)

Per the principle above, the v4c confusions were traced to code BEFORE any v5 prompt edit:

1. TOWERING — the 15 dry-land failures were systematic, not jitter. Placing into the cell
   below you requires feet >1.0 m off the ground (AABB overlap check). Vanilla jump physics
   (BASE_JUMP_POWER 0.42, gravity 0.08/tick, drag x0.98 — LivingEntity.jumpFromGround) keep
   feet above 1.0 m only on ticks 2-7 after jump start (apex 1.25 at tick 5). The prompt's
   documented [+jump wait/8 +use ...] fires at tick ~8-9 (feet ~0.8 m) → always denied on dry
   land; water "worked" because floating keeps feet rising. Fixed tap: wait/4-5. Robust form:
   hold +jump and +use together — Minecraft.startUseItem sets rightClickDelay=4
   UNCONDITIONALLY on entry (Minecraft.java:1776), so a held +use re-attempts every ~5 ticks
   and one attempt lands in the 6-tick window each jump cycle. UNVERIFIED IN-GAME YET —
   calibrate on runTestClient (cheats) before writing v5.
2. applyRecipe — "applied" = optimistic packet send (ApplyRecipeAlias.java:59-60 logs right
   after handlePlaceRecipe); the grid fills when the server's container sync arrives (~2-3
   ticks at equal tps). v4c's "silent rollbacks" = reading PRE-execution envelopes or taking
   before the ack. Fact contract for v5: nap for post-state, verify grid non-empty in the
   container member, then take c1; screen reopen forces a full re-sync (recovery move).
3. Sounds — SoundCapture is the vanilla SUBTITLE feed (SoundEventListener); direction is
   yaw/pitch RELATIVE TO VIEW at the moment heard (rounded to 20 deg), distance is true 3D
   and legitimately exceeds reach. v4c's "implausible positions" were misreads (pitch+0 =
   overhead while looking straight up). Useful as mob radar: yaw/<relYaw> faces the source.

Post-bench world state: host dug a test chimney from the y=40 mine, hit water, shaft flooded,
player drowned (respawned at spawn). WORLD IS DIRTY — next bench MUST restore pristine.
No-cheats client still running (port 25575). Next: (1) calibrate tower timing on
runTestClient; (2) write v5 prompt (tower fix, applyRecipe contract, sound-feed semantics,
grass tip); (3) restore pristine + bench v5.

## v5 — tower calibration + v5 prompt (2026-08-06)

Session-start infra fix: this thread had no bind-alias MCP tools (Zed `Write-` profile had
`enable_all_context_servers: false`, no bind-alias entry) — patched
`~/.agents/Zed/settings.json` (backup `settings.json.bak-20260806-mcprofile`): Write- now
enables the 8 current tools; stale `getFullState` entries removed from Agent-mc profiles.
zed-reload spawned the bridge.

TOWER CALIBRATION (runTestClient, Test_26_2, dry land at spawn, 1 tps, dirt in hand,
setPitch/85 + wait/9 aim settle first):
- Tap form [`+jump wait/4 +use wait/2 -use -jump wait/8`]: 1/1 block, then 3/3 blocks in a
  3x-repeated single chain (y 0→1→4). 14 ticks/block.
- Hold form [`+jump +use wait/30 -use -jump`]: 3/3 blocks in 30 ticks (y 4→7), one placement
  per jump cycle — confirms rightClickDelay=4 re-attempt lands in the ticks-2-7 window.
- Both forms 100% on dry land → code-derived fix VERIFIED in-game. v4's documented
  [+jump wait/8 +use ...] fires at tick ~8-9 (feet ~0.8 m) = outside the window, matching
  v4c's 15/15 failures.

v5 prompt deltas vs v4 (all from verified facts):
1. Tower pattern replaced: hold-both form (primary, robust) + wait/4 tap form, with the
   feet->1m/ticks-2-7 rule and the water exception explained.
2. applyRecipe contract: "applied" = request sent; nap for post-state, VERIFY grid non-empty
   in container state before taking c1; screen reopen = full re-sync recovery.
3. New sound-feed bullet: subtitle feed, yaw/pitch relative to view when heard (rounded
   20°), true-3D distance can exceed reach; yaw/<relYaw>+pitch/<relPitch> faces the source.
4. New grass bullet: tall grass intercepts swings/placements — clear it first.

v5 BENCH — ABORTED (infra, 2026-08-06 ~12:50-13:10). No-cheats client launched fine on the
restored pristine save (gametime 82, PauseScreen, 1 tps), AGENTS.md injected with v5 — but
every spawn_agent attempt failed: first `User canceled`, then transient network errors, then
a Kimi API temperature contradiction: with model_parameters temperature=1 the sub-agent
request was rejected ("only 0.6 is allowed for this model"); with 0.6 it was rejected the
other way ("only 1 is allowed"). User stopped the session. OPEN for next session: the
sub-agent spawn apparently hits a model with a different temperature constraint than the
main thread (k3-256k @1 works; maybe spawn uses k3 or another default) — try setting
`agent.subagent_model` explicitly (e.g. k3-256k @ temperature 1) in settings.json.
Cleanup done: client shut down, temperature reverted to 1, AGENTS.md restored (project
rules), pristine save restored (idle drift only, no agent changes). The Write- profile
bind-alias tools addition in settings.json was KEPT (needed for the bench loop).
NEXT: resolve the sub-agent model/temperature issue, then bench v5 unchanged (prompt ready
in src/agent_system_prompt.md; tower calibration already verified above).

## v5 bench attempt 2 — infra OK, bench PAUSED by user before agent spawn (2026-08-06 ~15:56)

- Sub-agent spawn blocker RESOLVED: trivial no-tool spawn_agent returned OK. The explicit
  `agent.subagent_model` (Kimi/k3-256k) already in settings.json seems to have fixed the
  temperature contradiction; no settings change needed this session.
- Bench setup completed: pristine save restored (gametime 82 verified via time query),
  AGENTS.md backed up to `AGENTS.md.project-rules.bak` and overwritten with the v5 prompt,
  no-cheats client launched (PauseScreen, 1 tps, spawn 6.5/69/4.5), autoload cfg ran.
- Bench sub-agent spawn returned `User canceled`; user then called PAUSE (resume later).
  The world was NEVER touched by an agent.
- Cleanup for the pause: client shut down via builtinShutdown (port 25575 closed).
  AGENTS.md LEFT AS v5 PROMPT (not restored) so the bench is ready to fire.
- RESUME CHECKLIST: (1) restore pristine save (launch-time idle drift only);
  (2) launch `gradlew runTestClientNoCheats` detached, wait for port 25575, verify
  gametime ~82 + PauseScreen; (3) spawn the bench sub-agent with the verbatim task message
  and the 8-tool allowlist; (4) after the bench, record results here, restore
  `AGENTS.md.project-rules.bak` -> AGENTS.md when the loop is done.

## v5 — bench COMPLETED but INVALID as clean datapoint (2026-08-06 16:05-18:24)

Infra (kept): resumed per user instruction "no reset, same save, same subagent". The canceled
session (10a1daa4) proved UN-RESUMABLE (transport error x3 while fresh no-tool spawns worked)
-> fresh bench spawn instead. Temperature contradiction root-caused: Kimi rejects k3-256k at
temp 1 ("only 0.6") AND at 0.6 ("only 1") — gateway-side inconsistency; `k3` @ temp 1 works
reliably. FIX KEPT in settings.json: `model_parameters` scoped per model (k3->1, k3-256k->0.6),
`agent.subagent_model` = k3. Message change (user-mandated): bench message now carries a
PRECISE TICK ENDPOINT ("night falls at ~12000 ticks; stop when time query reaches 12000"),
because agents may not realize night falls.

Run (session 442bbe9e, v5 prompt, world = same save, age ~852 at start):
- Wall ~16:05 -> 18:24 (~2 h 19 m). Stopped at gametime 36062.
- LOG-VERIFIED milestones: Stone Age 16:26, Getting an Upgrade 16:43 (stone pick),
  Acquire Hardware 17:21 (iron ingot — smelted ITSELF), crafting table placed, furnace-less,
  staircase mine + cave found (y~12-45), sealed lit foxhole at spawn for the night.
  Final inv (host-verified): stone pick/sword/wooden axe, iron ingot, raw iron, 9 raw copper,
  ~40 cobble/andesite/diorite, dirt, granite. NO torches left, NO logs, NO food.
- LOG-VERIFIED deaths: 2 — fell from a high place (18:04), slain by zombie (18:20).
- Mod log: ZERO PlaceRecipePacket errors; 1x `[switchSlot]An item stack remains on the cursor`
  WARN (18:14) — matches the agent's lost-stone-axe report.

**Endpoint overshoot + self-report confabulation (the big finding, user warned exactly this):**
agent's FIRST time query was 8509 (16:34), then 9546, 11161, 11803 (16:48) — all BEFORE the
12000 endpoint. It then went 22 min wall without checking (11803 -> 18089 at 17:10), blew past
nightfall, and RETARGETED to 36000 (next dusk). In its final report and follow-up it claimed
the world "was already at 33431 when I first queried" (FALSE — first query 8509) and that
sword/iron/copper were "carried over from the interrupted session" (FALSE — Acquire Hardware
earned 17:21; the pristine save was verified note-free and item-free). Reported 1 death (log:
2). LESSON: agent self-reports are unreliable even about checkable facts — always verify
against the game log. v6 message candidate: check gametime after EVERY long nap and at least
every ~10 min wall (a 600-tick nap passes in ~30 s wall at 20 tps ff).

Agent-reported confusions (signals to verify, NOT yet prompt facts):
1. swapSlot into OCCUPIED non-container slots silently no-ops (consistent with documented
   all-or-nothing); agent also claims verbose full-state shows only HOTBAR unless a screen is
   open — VERIFY in mod code: does the full snapshot include inv slots 10-41? If not, that's a
   real visibility gap (its first craft chain "vanished" into unseen leftovers in 10-15).
2. Closing a screen with an item on the cursor LOST the stone axe (mod log WARN confirms the
   cursor state existed). VERIFY what screen-close does with the cursor stack (vanilla: carried
   vs dropped vs returned) -> prompt rule candidate: never close a screen with a cursor item.
3. `+use` tap on ground placed the table AND opened it (double-fire?) — verify.
4. Stone pick on dirt needs ~wait/30 per block; wait/15 resets progress — unverified timing.
5. Mining floor-ahead at pitch 30-35 creates an accidental descending staircase -> 17-block
   fall into a cave (technique hazard; arguably a prompt tip: dig-down in place only).
6. +attack stuck once; held_keys rule caught it (prompt working as intended).
7. Agent's own sticker tip: dig straight down in place (no +forward wiggle) stays on-column —
   contradicts the prompt's documented wiggle; keep both under review.

Status: NOT comparable to v0-v4c (started age 852 + ran 3x budget to day-2 dusk). A clean v5
datapoint still requires a pristine-save rerun with the tick-endpoint message.
NEXT: (1) verify confusion #1 (full-state inventory visibility) + #2 (cursor-on-close) in code;
(2) restore pristine, bench v5.1 = v5 prompt unchanged + tick-endpoint message;
(3) only then fold verified findings into a v6 prompt.
