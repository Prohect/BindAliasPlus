package com.github.prohect.alias.builtinAlias;

import com.github.prohect.BindAliasPlusClient;
import com.github.prohect.alias.Alias;
import com.github.prohect.alias.BuiltinAliasWithArgs;
import com.github.prohect.util.McScreenHelper;
import java.util.regex.Pattern;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

public class SwapSlotAlias extends BuiltinAliasWithArgs<SwapSlotAlias> {

    public SwapSlotAlias() {
        super("swapSlot");
    }

    private record SlotRef(boolean container, int index) {
		static SlotRef player(int i) {
			return new SlotRef(false, i);
		}
		static SlotRef container(int i) {
			return new SlotRef(true, i);
		}
	}

    @Override
    public SwapSlotAlias run(String args) {
        MinecraftClient mc = MinecraftClient.getInstance();
        ClientPlayerEntity p = mc.player;
        if (p == null) {
            BindAliasPlusClient.LOGGER.warn("{}[switchSlot]Player is null", BindAliasPlusClient.tickPrefix());
            return this;
        }
        PlayerInventory inv = p.getInventory();
        if (inv == null) {
            BindAliasPlusClient.LOGGER.warn("{}[switchSlot]Inventory is null", BindAliasPlusClient.tickPrefix());
            return this;
        }
        int sel = inv.getSelectedSlot();
        ClientPlayNetworkHandler net = mc.getNetworkHandler();
        if (net == null) {
            BindAliasPlusClient.LOGGER.warn("{}[SwitchSlot]network handler is null", BindAliasPlusClient.tickPrefix());
            return this;
        }

        String[] ss = args.split(Pattern.quote(String.valueOf(Alias.divider4AliasArgs)));
        SlotRef[] sr;
        if (ss.length == 1)
            sr = new SlotRef[] {parseSlotRef(ss[0]), SlotRef.player(sel)};
        else if (ss.length == 2)
            sr = new SlotRef[] {parseSlotRef(ss[0]), parseSlotRef(ss[1])};
        else {
            BindAliasPlusClient.LOGGER.warn("{}[SwitchSlot]Invalid arguments", BindAliasPlusClient.tickPrefix());
            return this;
        }
        for (int i = 0; i < sr.length; i++)
            if (sr[i] == null) {
                BindAliasPlusClient.LOGGER.warn("{}[SwitchSlot]Invalid '{}'", BindAliasPlusClient.tickPrefix(), ss[i]);
                return this;
            }
        if (sr[0].equals(sr[1])) {
            BindAliasPlusClient.LOGGER.warn("{}[SwitchSlot]same slot", BindAliasPlusClient.tickPrefix());
            return this;
        }

        Screen screen = Alias.getCurrentScreen();
        boolean creativeInv = Alias.isInCreativeInventoryScreen();
        boolean inInv = Alias.isInInventoryScreen() || creativeInv;
        HandledScreen<?> cs = screen instanceof HandledScreen<?> h && !inInv ? h : null;
        if (creativeInv)
            screen.close();

        try {
            int oh = 40;
            boolean bp = !sr[0].container() && !sr[1].container();
            boolean s0oh = bp && sr[0].index() == oh;
            boolean hasOH = bp && (sr[1].index() == oh || s0oh);
            boolean s0hb = bp && sr[0].index() < 9;
            boolean inside = bp && (s0hb || s0oh) && (sr[1].index() < 9 || sr[1].index() == oh);

            if (inside) {
                if (hasOH)
                    swapOff(net, s0oh ? sr[1].index() : sr[0].index());
                else {
                    swapOff(net, sr[0].index());
                    swapOff(net, sr[1].index());
                    swapOff(net, sr[0].index());
                }
                net.sendPacket(new UpdateSelectedSlotC2SPacket(sel));
                return this;
            }
            if (Alias.isUnderAnyScreen() && !inInv && cs == null)
                return this;

            ScreenHandler menu;
            InventoryScreen iscr = null;
            if (cs != null)
                menu = cs.getScreenHandler();
            else {
                iscr = inInv ? (creativeInv ? new InventoryScreen(p) : (InventoryScreen) screen) : new InventoryScreen(p);
                if (!inInv || creativeInv)
                    McScreenHelper.setScreen(mc, iscr);
                menu = iscr.getScreenHandler();
            }
            try {
                ClientPlayerInteractionManager im = mc.interactionManager;
                if (im != null) {
                    Slot s0 = resolveSlot(menu, sr[0]), s1 = resolveSlot(menu, sr[1]);
                    if (s0 != null && s1 != null)
                        swapInMenu(im, menu, s0, s1, p);
                    else
                        BindAliasPlusClient.LOGGER.warn("{}[SwitchSlot]slot not found", BindAliasPlusClient.tickPrefix());
                } else
                    BindAliasPlusClient.LOGGER.warn("{}[SwitchSlot]interactionManager null", BindAliasPlusClient.tickPrefix());
            } finally {
                if (iscr != null && !inInv)
                    iscr.close();
            }
        } catch (Exception e) {
            BindAliasPlusClient.LOGGER.error("{}[SwitchSlot]Failed", BindAliasPlusClient.tickPrefix(), e);
        }
        return this;
    }

    private static SlotRef parseSlotRef(String a) {
        String t = a.trim();

        // cN is always a direct container slot reference, even if a variable with
        // the same name exists ("c<n>" could also be a valid var name per VarAlias).
        if (t.length() > 1 && t.charAt(0) == 'c') {
            try {
                int n = Integer.parseInt(t.substring(1));
                if (n >= 1)
                    return SlotRef.container(n - 1);
            } catch (NumberFormatException ignored) {
            }
        }

        // Check if this is a variable that holds a container slot reference
        Integer cSlot = VarAlias.CONTAINER_SLOT_VARIABLES.get(t);
        if (cSlot != null) {
            return SlotRef.container(cSlot - 1);
        }

        Integer r = VarAlias.resolveInt(t);
        if (r == null)
            return null;
        int idx = r - 1;
        return idx >= 0 && idx <= 40 ? SlotRef.player(idx) : null;
    }

    private static Slot resolveSlot(ScreenHandler menu, SlotRef ref) {
        if (ref.container())
            return ref.index() >= 0 && ref.index() < menu.slots.size() ? menu.slots.get(ref.index()) : null;
        for (Slot s : menu.slots)
            if (s.getIndex() == ref.index() && s.inventory instanceof PlayerInventory)
                return s;
        return null;
    }

    private static int swapButton(Slot s) {
        if (!(s.inventory instanceof PlayerInventory))
            return -1;
        int i = s.getIndex();
        return i < 9 ? i : (i == 40 ? 40 : -1);
    }

    private static void swapInMenu(ClientPlayerInteractionManager im, ScreenHandler menu, Slot s0, Slot s1,
            ClientPlayerEntity p) {
        int b0 = swapButton(s0);
        if (b0 != -1) {
            click(im, menu, s1, b0, SlotActionType.SWAP, p);
            return;
        }
        int b1 = swapButton(s1);
        if (b1 != -1) {
            click(im, menu, s0, b1, SlotActionType.SWAP, p);
            return;
        }
        click(im, menu, s0, 0, SlotActionType.PICKUP, p);
        click(im, menu, s1, 0, SlotActionType.PICKUP, p);
        if (!menu.getCursorStack().isEmpty())
            click(im, menu, s0, 0, SlotActionType.PICKUP, p);
        if (!menu.getCursorStack().isEmpty())
            click(im, menu, s1, 0, SlotActionType.PICKUP, p);
        if (!menu.getCursorStack().isEmpty())
            BindAliasPlusClient.LOGGER.warn("{}[switchSlot]item on cursor", BindAliasPlusClient.tickPrefix());
    }

    private static void click(ClientPlayerInteractionManager im, ScreenHandler menu, Slot s, int btn, SlotActionType act,
            ClientPlayerEntity p) {
        im.clickSlot(menu.syncId, s.id, btn, act, p);
    }

    private static void swapOff(ClientPlayNetworkHandler net, int idx) {
        net.sendPacket(new UpdateSelectedSlotC2SPacket(idx));
        net.sendPacket(new PlayerActionC2SPacket(PlayerActionC2SPacket.Action.SWAP_ITEM_WITH_OFFHAND, BlockPos.ORIGIN,
                Direction.DOWN));
    }
}
