#!/bin/bash

# Regenerate "src/raw api instruction.json" — a preview of the *exact* tools/list
# response an MCP client receives from src/mcp_server.js. Run this after
# editing src/mcp_server.js's tool descriptions so the preview never goes
# stale. (The alias catalog / gameplay semantics live in
# src/agent_system_prompt.md instead — that's on you to keep current.)
# Usage: bash src/sync_mcp_instructions.sh

set -e
cd "$(dirname "$0")/.."

printf '{"jsonrpc":"2.0","id":1,"method":"tools/list"}\n' |
	node src/mcp_server.js |
	node -e "process.stdout.write(JSON.stringify(JSON.parse(require('fs').readFileSync(0,'utf8')), null, 2) + '\n')" \
		>"src/raw api instruction.json"

echo "src/raw api instruction.json updated from src/mcp_server.js tools/list"
