package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasClient;
import com.github.prohect.alias.BuiltinAliasWithoutArgs;
import com.github.prohect.mcp.GameChannels;
import com.github.prohect.mcp.StateTracker;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Captures the current state diff and posts it to the {@code agent_msg} channel keyed by clientTick, so the next drained
 * envelope carries it as a structured entry. Does NOT drain message channels. Only one state per tick is kept — subsequent
 * calls at the same tick are no-ops.
 */
public class DiffStateAlias extends BuiltinAliasWithoutArgs<DiffStateAlias> {

    public DiffStateAlias() {
        super("diffState");
    }

    @Override
    public DiffStateAlias run(String args) {
        if (BindAliasClient.joinTick < 0)
            return this; // not yet in a world — nothing to diff

        String begun = StateTracker.begin(false);
        String envelope = StateTracker.finishNoDrain(begun);
        // Extract the "state" field from the envelope JSON
        String stateJson = extractState(envelope);
        if (stateJson != null) {
            GameChannels.postAgentState(BindAliasClient.currentTick - BindAliasClient.joinTick, stateJson);
        }
        return this;
    }

    private static String extractState(String envelope) {
        try {
            JsonObject obj = JsonParser.parseString(envelope).getAsJsonObject();
            if (obj.has("state")) {
                return obj.get("state").toString();
            }
        } catch (Exception ignored) {
        }
        return null;
    }
}
