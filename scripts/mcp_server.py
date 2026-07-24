#!/usr/bin/env python3
"""
MCP stdio bridge for BindAliasPlus mod.

Connects to the mod's HTTP API (127.0.0.1:25575) and exposes 8 tools
to AI agents via the Model Context Protocol (JSON-RPC 2.0 on stdio).

Usage:
    python mcp_server.py

The mod must be running with the MCP HTTP server active.
"""

import json
import sys
import urllib.request
import urllib.parse
import urllib.error
import base64
import os

API_BASE = "http://127.0.0.1:25575"

SUGGESTED_ALIASES = (
    "+attack, -attack, +use, -use, +forward, -forward, +back, -back, "
    "+left, -left, +right, -right, +jump, -jump, +sneak, -sneak, "
    "+sprint, -sprint, +drop, -drop, +screenshot, -screenshot, "
    "+playerList, -playerList, esc, closeScreen, +advancements, "
    "-advancements, +debugOverlay, -debugOverlay, +openInventory, "
    "-openInventory, swapSlot, wait, yaw, pitch, setYaw, setPitch, "
    "FPS, TPS, TPS2, cyclePerspective, swapHand, pickItem, "
    "toggleInventory, sendCommand, say, localSay, log, slot, silent, "
    "var, lock, +lock, -lock, reapply, runAlias, alias, reloadCFG, "
    "unloadCFGAliases, unloadCFGBinds, unloadCFGVars, unloadCFGAll"
)

TOOLS = [
    {
        "name": "getState",
        "description": (
            "Get current Minecraft game state snapshot: screen class name, "
            "world name, dimension, player x/y/z/yaw/pitch, health, "
            "held item registry name + count + hotbar slot. "
            "Returns JSON."
        ),
        "inputSchema": {
            "type": "object",
            "properties": {},
            "required": [],
        },
    },
    {
        "name": "getScreenshot",
        "description": (
            "Trigger a Minecraft screenshot (native F2), wait for the file, "
            "and return it as a base64-encoded PNG image with path and name. "
            "Returns JSON with fields: path, name, base64."
        ),
        "inputSchema": {
            "type": "object",
            "properties": {},
            "required": [],
        },
    },
    {
        "name": "runAlias",
        "description": (
            "Execute a registered BindAliasPlus alias by name, optionally "
            "with backslash-separated arguments. "
            "Suggested aliases: " + SUGGESTED_ALIASES + ". "
            "Returns JSON {\"ok\": true} on success."
        ),
        "inputSchema": {
            "type": "object",
            "properties": {
                "name": {
                    "type": "string",
                    "description": "Alias name to execute.",
                },
                "args": {
                    "type": "string",
                    "description": (
                        "Optional backslash-separated arguments for the alias."
                    ),
                },
            },
            "required": ["name"],
        },
    },
    {
        "name": "defineAlias",
        "description": (
            "Define a new alias via the /alias command (sendCommand). "
            "Returns JSON {\"ok\": true} on success. "
            "Must be in a world (not on title screen)."
        ),
        "inputSchema": {
            "type": "object",
            "properties": {
                "name": {
                    "type": "string",
                    "description": "Alias name to create.",
                },
                "def": {
                    "type": "string",
                    "description": "Alias definition string.",
                },
            },
            "required": ["name", "def"],
        },
    },
    {
        "name": "reloadCFG",
        "description": (
            "Reload the bind-alias-plus.cfg config file. "
            "Returns JSON {\"ok\": true}."
        ),
        "inputSchema": {
            "type": "object",
            "properties": {},
            "required": [],
        },
    },
    {
        "name": "unloadCFG",
        "description": (
            "Unload config-loaded state. "
            "Parameter 'what' can be: aliases, binds, vars, all (default). "
            "Returns JSON {\"ok\": true}."
        ),
        "inputSchema": {
            "type": "object",
            "properties": {
                "what": {
                    "type": "string",
                    "description": (
                        "What to unload: aliases, binds, vars, or all."
                    ),
                },
            },
            "required": [],
        },
    },
    {
        "name": "readCFG",
        "description": (
            "Read the raw content of the bind-alias-plus.cfg config file. "
            "Returns JSON {\"content\": \"...\"} with the file contents."
        ),
        "inputSchema": {
            "type": "object",
            "properties": {},
            "required": [],
        },
    },
    {
        "name": "writeCFG",
        "description": (
            "Overwrite the bind-alias-plus.cfg config file with new content "
            "and reload it. Parameter 'content' is the full file text. "
            "Returns JSON {\"ok\": true}."
        ),
        "inputSchema": {
            "type": "object",
            "properties": {
                "content": {
                    "type": "string",
                    "description": "Full config file content to write.",
                },
            },
            "required": ["content"],
        },
    },
]


def api_get(path, params=None):
    """Make a GET request to the mod API."""
    url = API_BASE + path
    if params:
        url += "?" + urllib.parse.urlencode(params)
    try:
        with urllib.request.urlopen(url, timeout=10) as resp:
            return json.loads(resp.read().decode("utf-8"))
    except urllib.error.URLError as e:
        return {"error": f"Cannot connect to mod: {e.reason}"}
    except Exception as e:
        return {"error": str(e)}


def api_post(path, params=None, body=None):
    """Make a POST request to the mod API."""
    url = API_BASE + path
    if params:
        url += "?" + urllib.parse.urlencode(params)
    data = None
    if body is not None:
        data = json.dumps(body).encode("utf-8")
    try:
        req = urllib.request.Request(
            url, data=data, method="POST",
            headers={"Content-Type": "application/json"} if data else {}
        )
        with urllib.request.urlopen(req, timeout=10) as resp:
            return json.loads(resp.read().decode("utf-8"))
    except urllib.error.URLError as e:
        return {"error": f"Cannot connect to mod: {e.reason}"}
    except Exception as e:
        return {"error": str(e)}


def handle_tool_call(tool_name, arguments):
    """Dispatch a tool call to the appropriate API endpoint."""
    if tool_name == "getState":
        return api_get("/state")

    elif tool_name == "getScreenshot":
        result = api_get("/screenshot")
        # If screenshot succeeded, include the image as content
        if "base64" in result:
            return {
                "content": [
                    {
                        "type": "text",
                        "text": (
                            f"Screenshot saved: {result.get('name', 'unknown')}\n"
                            f"Path: {result.get('path', 'unknown')}"
                        ),
                    },
                    {
                        "type": "image",
                        "data": result["base64"],
                        "mimeType": "image/png",
                    },
                ]
            }
        return result

    elif tool_name == "runAlias":
        name = arguments.get("name", "")
        args_val = arguments.get("args", "")
        params = {"name": name}
        if args_val:
            params["args"] = args_val
        return api_post("/runAlias", params=params)

    elif tool_name == "defineAlias":
        return api_post(
            "/defineAlias",
            params={
                "name": arguments.get("name", ""),
                "def": arguments.get("def", ""),
            },
        )

    elif tool_name == "reloadCFG":
        return api_post("/reloadCFG")

    elif tool_name == "unloadCFG":
        what = arguments.get("what", "all")
        return api_post("/unloadCFG", params={"what": what})

    elif tool_name == "readCFG":
        return api_get("/readCFG")

    elif tool_name == "writeCFG":
        return api_post(
            "/writeCFG",
            body={"content": arguments.get("content", "")},
        )

    else:
        return {"error": f"Unknown tool: {tool_name}"}


def make_response(id_, result):
    """Build a JSON-RPC 2.0 success response."""
    return json.dumps({
        "jsonrpc": "2.0",
        "id": id_,
        "result": result,
    })


def make_error(id_, code, message):
    """Build a JSON-RPC 2.0 error response."""
    return json.dumps({
        "jsonrpc": "2.0",
        "id": id_,
        "error": {"code": code, "message": message},
    })


def main():
    """MCP JSON-RPC 2.0 main loop on stdio."""
    # Ensure stdout is unbuffered for JSON-RPC
    sys.stdout.reconfigure(line_buffering=True) if hasattr(
        sys.stdout, "reconfigure"
    ) else None

    for line in sys.stdin:
        line = line.strip()
        if not line:
            continue

        try:
            request = json.loads(line)
        except json.JSONDecodeError:
            continue

        req_id = request.get("id")
        method = request.get("method", "")

        if method == "initialize":
            resp = make_response(req_id, {
                "protocolVersion": "2024-11-05",
                "capabilities": {"tools": {}},
                "serverInfo": {
                    "name": "bind-alias-plus-mcp",
                    "version": "1.0.0",
                },
            })
            sys.stdout.write(resp + "\n")
            sys.stdout.flush()

        elif method == "tools/list":
            resp = make_response(req_id, {"tools": TOOLS})
            sys.stdout.write(resp + "\n")
            sys.stdout.flush()

        elif method == "tools/call":
            params = request.get("params", {})
            tool_name = params.get("name", "")
            arguments = params.get("arguments", {})

            result = handle_tool_call(tool_name, arguments)

            # Wrap result in MCP content format if not already
            if "content" not in result and "error" not in result:
                result = {
                    "content": [
                        {"type": "text", "text": json.dumps(result, indent=2)}
                    ]
                }

            resp = make_response(req_id, result)
            sys.stdout.write(resp + "\n")
            sys.stdout.flush()

        elif method == "notifications/initialized":
            # No response needed for notifications
            pass

        else:
            resp = make_error(
                req_id, -32601, f"Method not found: {method}"
            )
            sys.stdout.write(resp + "\n")
            sys.stdout.flush()


if __name__ == "__main__":
    main()
