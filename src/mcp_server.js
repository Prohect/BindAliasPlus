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
  "Returns the standard envelope: the state diff captured before execution, or after the snap when `snap` is given.";

// Shared optional params for every tool that returns the standard envelope.
const VERBOSE_PARAM = {
  type: "boolean",
  description:
    "Optional (default false). When true, the envelope's state is the FULL snapshot instead of the diff.",
};
const SNAP_PARAM = {
  type: "array",
  items: {
    type: "object",
    properties: {
      deferredTick: {
        type: "integer",
        minimum: 0,
        maximum: 1200,
        description:
          "Client_tick offset at which to capture the standard envelope. 0 = capture immediately alongside the action; 1-1200 = defer capture by this many ticks. The action runs once immediately regardless.",
      },
      screenShot: {
        type: "boolean",
        description:
          "Optional (default false). When true, a base64 PNG screenshot is included in this capture point's envelope as the `screenShot` field.",
      },
    },
    required: ["deferredTick"],
  },
  description:
    "Optional. Capture the standard envelope at the given client_tick offsets, each optionally with a screenshot. The action runs immediately; state is captured at each `deferredTick`. A single entry returns one envelope; multiple entries (e.g. [{\"deferredTick\":1},{\"deferredTick\":2,\"screenShot\":true}]) return an array of envelopes — one per capture point. The game keeps running the whole time — you cannot react to anything or poll state until the call returns. A deferredTick >= 10 fast-forwards a singleplayer world (~20 tps) for the duration of the longest snap.",
};

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
        snap: SNAP_PARAM,
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
        snap: SNAP_PARAM,
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
        snap: SNAP_PARAM,
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
        snap: SNAP_PARAM,
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

// JSON object -> text content: compact one-liner when short, pretty-printed
// when long (state snapshots).
function jsonResult(obj) {
  const compact = JSON.stringify(obj);
  return textResult(
    compact.length <= 120 ? compact : JSON.stringify(obj, null, 2),
  );
}

// Normalize a raw bridge/mod response into a proper MCP tool result.
// Handles extraction of an embedded `screenShot` (base64 PNG) into an image content block.
function wrapResult(result) {
  if (result == null) return errorResult("no response from mod");
  if (Array.isArray(result.content)) return result; // already MCP-shaped
  if (result.error) return errorResult(result.error); // bridge/mod error

  // Multi-snap response: array of envelopes
  if (Array.isArray(result)) {
    return multiSnapResult(result);
  }

  // Single envelope — check for embedded screenshot
  if (typeof result === "object" && typeof result.screenShot === "string") {
    return envelopeWithScreenshot(result);
  }

  if (typeof result.content === "string") {
    // readCFG: raw config file text
    return textResult(
      result.content.length ? result.content : "(config file is empty)",
    );
  }
  return jsonResult(result);
}

// Convert a single envelope that contains a `screenShot` field into MCP content
// blocks: image content for the screenshot + text content for the rest.
function envelopeWithScreenshot(result) {
  const { screenShot, ...envelope } = result;
  const content = [{ type: "image", data: screenShot, mimeType: "image/png" }];
  const compact = JSON.stringify(envelope);
  content.push({
    type: "text",
    text: compact.length <= 120 ? compact : JSON.stringify(envelope, null, 2),
  });
  return { content };
}

// Convert a multi-snap response (array of envelopes) into MCP content.
// Each envelope may contain a `screenShot` field — the first screenshot is
// surfaced as an image block; subsequent ones are kept as text.
function multiSnapResult(envelopes) {
  const content = [];
  let firstScreenshot = null;

  for (let i = 0; i < envelopes.length; i++) {
    const env = envelopes[i];
    if (env && typeof env.screenShot === "string") {
      if (firstScreenshot === null) {
        firstScreenshot = env.screenShot;
      }
      // Remove screenShot from the envelope text to avoid bloat
      const { screenShot, ...rest } = env;
      envelopes[i] = rest;
    }
  }

  if (firstScreenshot !== null) {
    content.push({
      type: "image",
      data: firstScreenshot,
      mimeType: "image/png",
    });
  }

  const compact = JSON.stringify(envelopes);
  content.push({
    type: "text",
    text: compact.length <= 120 ? compact : JSON.stringify(envelopes, null, 2),
  });
  return { content };
}

// Shared envelope plumbing: pass through verbose and encode snap as deferredTick:screenShot pairs.
// A snap extends the HTTP timeout (a client_tick is ~50 ms at nominal speed).
function envelopeParams(args) {
  const params = {};
  if (args.verbose === true) params.verbose = "1";

  // snap: array of {deferredTick, screenShot?} → comma-separated "tick:flag" query param
  const snap = args.snap;
  let maxSnap = 0;
  if (Array.isArray(snap) && snap.length > 0) {
    const parts = [];
    for (const entry of snap) {
      if (entry == null || typeof entry !== "object") continue;
      const tick = Number(entry.deferredTick);
      if (!Number.isInteger(tick) || tick < 0 || tick > 1200) continue;
      const ss = entry.screenShot === true ? 1 : 0;
      parts.push(tick + ":" + ss);
      if (tick > maxSnap) maxSnap = tick;
    }
    if (parts.length > 0) params.snap = parts.join(",");
  }

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
