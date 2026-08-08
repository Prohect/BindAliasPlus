#!/usr/bin/env node
/**
 * MCP stdio bridge for BindAlias mod.
 */

const http = require("http");
const fs = require("fs");

// ---- port: --port N | --port=N | default 25575 ----
function parsePort() {
  const argv = process.argv.slice(2);
  for (let i = 0; i < argv.length; i++) {
    if (argv[i] === "--port" && i + 1 < argv.length) {
      const p = parseInt(argv[i + 1], 10);
      if (p > 0 && p < 65536) return p;
    }
    const m = /^--port=(\d+)$/.exec(argv[i]);
    if (m) {
      const p = parseInt(m[1], 10);
      if (p > 0 && p < 65536) return p;
    }
  }
  return 25575;
}

const API_BASE = "http://127.0.0.1:" + parsePort();

// ===========================================================================
// runAlias description — wire-protocol facts only (chain syntax lives on the
// `def` param below). The alias catalog and gameplay semantics (which aliases
// exist, screens/variables interplay, etc.) change with the mod and per-world
// cfg, so they live in src/agent_system_prompt.md instead of here — paste
// that into your agent's system prompt. Run src/sync_mcp_instructions.sh
// after editing this file to refresh "src/raw api instruction.json", a preview of
// exactly what a caller receives from tools/list.
// ===========================================================================

const RUNALIAS_DESCRIPTION =
  "Execute a chain of aliases against the running game. " +
  "SILENT FAILURES: an unknown alias name or invalid args fails that step with no thrown error. " +
  "Returns the standard envelope (see SNAP_DEFERRED_TICKS_PARAM for its shape); agent_msg entries from diffState / printScreen aliases are delivered as MCP image+text content blocks.";

// Shared optional params for every tool that returns the standard envelope.
const VERBOSE_PARAM = {
  type: "boolean",
  description:
    "Optional (default false). When true, the envelope's state is the FULL snapshot instead of the diff.",
};
const SNAP_DEFERRED_TICKS_PARAM = {
  type: "integer",
  minimum: 0,
  maximum: 1200,
  description:
    "Client_tick offset at which to capture the standard envelope. 0 = capture immediately alongside the action; 1-1200 = defer capture by this many ticks. The action runs once immediately regardless. A snapDeferredTicks >= 10 fast-forwards a singleplayer world (~20 tps) for the duration of the snap.",
};

// What the standard envelope may carry: {"client_tick":N, "state":{...}, "chat":[...], "mod":[...],
// "sound":[...], "unlocked_recipe":[...], "agent_msg":[...]}.  agent_msg entries are structured JSON
// objects ({"client_tick":N,"state":{...},"screenShot":"base64..."}) posted by diffState / printScreen
// aliases mid-chain; screenshots in agent_msg are delivered as MCP image content blocks.

const TOOLS = [
  {
    name: "runAlias",
    description: RUNALIAS_DESCRIPTION,
    inputSchema: {
      type: "object",
      properties: {
        def: {
          type: "string",
          description:
            'Alias chain definition. Space(\' \') for alias(with arg) separator, slash(\'/\') for alias_name-arg separator or arg-arg separator, quote(\'"\') quotes multi-word arg preventing space inside to be parsed as alias(with arg) separator: e.g. `say/"hello world"`. Semicolon for alias\'s (the alias named as `alias`) extra separator: e.g. `alias/turnDown;setPitch/90`, `alias/turnRight;yaw/90`',
        },
        snapDeferredTicks: SNAP_DEFERRED_TICKS_PARAM,
        verbose: VERBOSE_PARAM,
      },
      required: ["def"],
    },
  },
  {
    name: "defineAlias",
    description: "Define a new alias. Returns the standard envelope.",
    inputSchema: {
      type: "object",
      properties: {
        name: {
          type: "string",
          description: "Alias name to create (single word).",
        },
        def: {
          type: "string",
          description:
            "Alias definition string (chain syntax, same as 'runAlias').",
        },
        snapDeferredTicks: SNAP_DEFERRED_TICKS_PARAM,
        verbose: VERBOSE_PARAM,
      },
      required: ["name", "def"],
    },
  },
  {
    name: "readCFG",
    description:
      "Read the cfg file, returned as plain text. " +
      "cfg syntax: one command(optional leading '/') per line — alias <name> <definition>, " +
      "var <name> <source>, runAlias <def>; '#' starts a comment. " +
      "Read it to discover the currently defined custom aliases/variables — the live capability catalog beyond the builtins.",
    inputSchema: { type: "object", properties: {}, required: [] },
  },
  {
    name: "writeCFG",
    description:
      "Overwrite the cfg file with new content and immediately reload it. " +
      "Same line format as 'readCFG'. Reloading only adds/overwrites — put \"runAlias unloadCFGAll\" as the " +
      "first line to clear stale entries before the new content. " +
      "Returns the standard envelope.",
    inputSchema: {
      type: "object",
      properties: {
        content: {
          type: "string",
          description: "Full config file content to write.",
        },
        snapDeferredTicks: SNAP_DEFERRED_TICKS_PARAM,
        verbose: VERBOSE_PARAM,
      },
      required: ["content"],
    },
  },
  {
    name: "readNotes",
    description:
      "Read the entire content of a file. " +
      'Returns {"content":"..."} (empty string if the file does not exist).',
    inputSchema: {
      type: "object",
      properties: {
        file: {
          type: "string",
          description:
            "Plain filename (with extension) inside the agent directory. No path separators or '..' allowed.",
        },
      },
      required: ["file"],
    },
  },
  {
    name: "writeNotes",
    description: "Write a file.",
    inputSchema: {
      type: "object",
      properties: {
        file: {
          type: "string",
          description:
            "Plain filename (with extension) inside the agent directory. No path separators or '..' allowed.",
        },
        content: {
          type: "string",
          description: "Full file content to write.",
        },
      },
      required: ["file", "content"],
    },
  },
  {
    name: "listRecipes",
    description:
      "List recipes unlocked in the recipe book. Only works while a recipe-book screen is open (player inventory via toggleInventory, crafting table, furnace, ...). " +
      "Without 'queries': returns recipes learned since the previous 'listRecipes' call (a diff — the first call after joining the world returns everything). " +
      "With 'queries' (result-item ids like 'minecraft:torch' or 'torch', or locale-name substrings like 'iron sword'): every query is answered independently — matches land in 'recipes', per-query failures in 'recipe_errors'. " +
      "Entries: {name, item, craftable, placeable}. craftable=true means the ingredients are in your inventory right now; placeable=false means the open menu cannot place it (grid too small or wrong station). " +
      "Returns the standard envelope plus recipes/recipe_errors.",
    inputSchema: {
      type: "object",
      properties: {
        queries: {
          type: "array",
          items: { type: "string" },
          description:
            "Optional list of recipe queries. Omit to list newly unlocked recipes.",
        },
        snapDeferredTicks: SNAP_DEFERRED_TICKS_PARAM,
        verbose: VERBOSE_PARAM,
      },
      required: [],
    },
  },
];

// ---- HTTP helpers ----

function buildUrl(path, params) {
  let url = API_BASE + path;
  if (params) {
    const qs = Object.entries(params)
      .map(function (e) {
        return encodeURIComponent(e[0]) + "=" + encodeURIComponent(e[1]);
      })
      .join("&");
    url += "?" + qs;
  }
  return url;
}

function apiGet(path, params, timeoutMs) {
  const url = buildUrl(path, params);
  return new Promise((resolve) => {
    const req = http.get(url, { timeout: timeoutMs || 10000 }, (res) => {
      let data = "";
      res.on("data", (chunk) => {
        data += chunk;
      });
      res.on("end", () => {
        try {
          resolve(JSON.parse(data));
        } catch {
          resolve({ error: "Invalid JSON response" });
        }
      });
    });
    req.on("timeout", () => req.destroy(new Error("request timed out")));
    req.on("error", (e) => {
      resolve({ error: "Cannot connect to mod: " + e.message });
    });
  });
}

function apiPost(path, params, body, timeoutMs) {
  const url = buildUrl(path, params);
  const bodyStr = body ? JSON.stringify(body) : null;
  return new Promise((resolve) => {
    const req = http.request(
      url,
      {
        method: "POST",
        timeout: timeoutMs || 10000,
        headers: bodyStr
          ? {
              "Content-Type": "application/json",
              "Content-Length": Buffer.byteLength(bodyStr),
            }
          : {},
      },
      (res) => {
        let data = "";
        res.on("data", (chunk) => {
          data += chunk;
        });
        res.on("end", () => {
          try {
            resolve(JSON.parse(data));
          } catch {
            resolve({ error: "Invalid JSON response" });
          }
        });
      },
    );
    req.on("timeout", () => req.destroy(new Error("request timed out")));
    req.on("error", (e) => {
      resolve({ error: "Cannot connect to mod: " + e.message });
    });
    req.end();
  });
}

// ---- MCP JSON-RPC ----

function send(obj) {
  fs.writeSync(1, JSON.stringify(obj) + "\n");
}

function makeResponse(id, result) {
  return { jsonrpc: "2.0", id, result };
}

function makeError(id, code, message) {
  return { jsonrpc: "2.0", id, error: { code, message } };
}

// ---- MCP result formatting ----

function errorResult(message) {
  return {
    isError: true,
    content: [{ type: "text", text: "Error: " + message }],
  };
}

function textResult(text) {
  return { content: [{ type: "text", text }] };
}

// Normalize a raw bridge/mod response into a proper MCP tool result.
// Extracts base64 PNG screenshots from `agent_msg` channel entries into MCP image content blocks.
function wrapResult(result) {
  if (result == null) return errorResult("no response from mod");
  if (Array.isArray(result.content)) return result; // already MCP-shaped
  if (result.error) return errorResult(result.error); // bridge/mod error

  if (typeof result.content === "string") {
    // readCFG: raw config file text
    return textResult(
      result.content.length ? result.content : "",
    );
  }

  // Single envelope — extract agent_msg screenshots into image content blocks
  return envelopeResult(result);
}

// Format a JSON-serializable value as text: compact one-liner when short, pretty-printed when long.
function formatJson(val) {
  const compact = JSON.stringify(val);
  return compact.length <= 120 ? compact : JSON.stringify(val, null, 2);
}

// Convert an envelope into MCP content blocks, extracting `screenShot` fields from `agent_msg` entries
// as image blocks. The outer envelope and agent_msg entries are merged into a single timeline sorted by
// client_tick, with each image followed by its entry text (minimal {"client_tick":N} stamp when no state).
function envelopeResult(envelope) {
  const items = []; // {tick, shot, text}

  // 1. Separate agent_msg entries from the outer envelope
  let outerTick = null;
  let restEnvelope = envelope;
  if (restEnvelope && Array.isArray(restEnvelope.agent_msg)) {
    const { agent_msg, ...outer } = restEnvelope;
    restEnvelope = outer;
    outerTick = restEnvelope.client_tick;

    for (const entry of agent_msg) {
      if (!entry) continue;
      const { screenShot, ...cleanEntry } = entry;
      items.push({
        tick: entry.client_tick,
        shot: typeof screenShot === "string" ? screenShot : null,
        text: formatJson(cleanEntry),
      });
    }
  }

  // 2. Outer envelope as a text-only item
  if (outerTick != null || Object.keys(restEnvelope).length > 0) {
    items.push({
      tick: outerTick,
      shot: null,
      text: formatJson(restEnvelope),
    });
  }

  // 3. Sort by client_tick ascending (nulls last)
  items.sort((a, b) => {
    if (a.tick == null) return 1;
    if (b.tick == null) return -1;
    return a.tick - b.tick;
  });

  // 4. Flatten: each item → image (if any) then text
  const content = [];
  for (const item of items) {
    if (item.shot != null) {
      content.push({ type: "image", data: item.shot, mimeType: "image/png" });
    }
    if (item.text != null) {
      content.push({ type: "text", text: item.text });
    }
  }

  if (content.length === 0) {
    content.push({ type: "text", text: "{}" });
  }

  return { content };
}

// Shared envelope plumbing: pass through verbose and snapDeferredTicks as a single query param.
// A snap extends the HTTP timeout (a client_tick is ~50 ms at nominal speed).
function envelopeParams(args) {
  const params = {};
  if (args.verbose === true) params.verbose = "1";

  // snapDeferredTicks: single integer defer in [0, 1200]
  const ticks = Number(args.snapDeferredTicks);
  const maxSnap = (Number.isInteger(ticks) && ticks >= 0 && ticks <= 1200) ? ticks : 0;
  if (maxSnap > 0) params.snapDeferredTicks = String(maxSnap);

  return { params, maxSnap };
}

async function handleToolCall(toolName, args) {
  switch (toolName) {
    case "runAlias": {
      const { params, maxSnap } = envelopeParams(args);
      params.def = args.def || "";
      const result = await apiPost(
        "/runAlias",
        params,
        null,
        10000 + maxSnap * 50,
      );
      return wrapResult(result);
    }

    case "defineAlias": {
      const { params, maxSnap } = envelopeParams(args);
      params.name = args.name || "";
      params.def = args.def || "";
      const result = await apiPost(
        "/defineAlias",
        params,
        null,
        10000 + maxSnap * 50,
      );
      return wrapResult(result);
    }

    case "readCFG":
      return wrapResult(await apiGet("/readCFG"));

    case "writeCFG": {
      const { params, maxSnap } = envelopeParams(args);
      params.content = args.content || "";
      return wrapResult(
        await apiPost("/writeCFG", params, null, 30000 + maxSnap * 50),
      );
    }

    case "listRecipes": {
      let queries = args.queries;
      if (typeof queries === "string") queries = [queries];
      const { params, maxSnap } = envelopeParams(args);
      if (Array.isArray(queries) && queries.length > 0)
        params.q = queries.join(",");
      return wrapResult(
        await apiGet("/listRecipes", params, 10000 + maxSnap * 50),
      );
    }

    case "readNotes":
      return wrapResult(await apiGet("/readNotes", { file: args.file || "" }));

    case "writeNotes":
      return wrapResult(
        await apiPost(
          "/writeNotes",
          {
            file: args.file || "",
            content: args.content || "",
          },
          null,
          30000,
        ),
      );

    default:
      return errorResult("unknown tool: " + toolName);
  }
}

// ---- Main ----

let stdinBuffer = "";

function main() {
  fs.writeSync(
    2,
    "[mcp_server] started, API " + API_BASE + ", waiting for stdin...\n",
  );

  process.stdin.setEncoding("utf8");
  process.stdin.resume();

  process.stdin.on("data", (chunk) => {
    fs.writeSync(
      2,
      "[mcp_server] received chunk: " + JSON.stringify(chunk) + "\n",
    );
    stdinBuffer += chunk;
    // Process complete lines
    let newlineIdx;
    while ((newlineIdx = stdinBuffer.indexOf("\n")) !== -1) {
      const line = stdinBuffer.slice(0, newlineIdx).trim();
      stdinBuffer = stdinBuffer.slice(newlineIdx + 1);
      if (!line) continue;
      handleLine(line);
    }
  });

  process.stdin.on("end", () => {
    fs.writeSync(2, "[mcp_server] stdin ended, exiting\n");
    process.exit(0);
  });

  process.stdin.on("error", (err) => {
    fs.writeSync(2, "[mcp_server] stdin error: " + err.message + "\n");
  });
}

function handleLine(line) {
  let request;
  try {
    request = JSON.parse(line);
  } catch {
    return;
  }

  const id = request.id;
  const method = request.method || "";

  if (method === "initialize") {
    send(
      makeResponse(id, {
        protocolVersion: "2024-11-05",
        capabilities: { tools: {} },
        serverInfo: { name: "bind-alias-mcp", version: "3.0.0" },
      }),
    );
  } else if (method === "ping") {
    send(makeResponse(id, {}));
  } else if (method === "tools/list") {
    send(makeResponse(id, { tools: TOOLS }));
  } else if (method === "tools/call") {
    const params = request.params || {};
    const toolName = params.name || "";
    const args = params.arguments || {};

    handleToolCall(toolName, args)
      .then((result) => {
        send(makeResponse(id, result));
      })
      .catch((e) => {
        // Never leave a tool call unanswered, even on an internal bug.
        send(makeError(id, -32603, String((e && e.message) || e)));
      });
  } else if (method === "notifications/initialized") {
    // No response for notifications
  } else {
    send(makeError(id, -32601, "Method not found: " + method));
  }
}

process.on("uncaughtException", (err) => {
  fs.writeSync(
    2,
    "[mcp_server] FATAL: " + err.message + "\n" + err.stack + "\n",
  );
  process.exit(1);
});

main();
