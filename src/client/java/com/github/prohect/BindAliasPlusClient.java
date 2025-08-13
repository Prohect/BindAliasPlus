package com.github.prohect;

import com.github.prohect.alias.AliasWithoutArgs;
import com.github.prohect.alias.Aliases;
import com.github.prohect.alias.UserAlias;
import com.github.prohect.alias.builtinAlias.*;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class BindAliasPlusClient implements ClientModInitializer {

    public static final ArrayDeque<KeyPressed> KEY_QUEUE = new ArrayDeque<>();
    public static final Map<InputUtil.Key, KeyBindingPlus> BINDING_PLUS = new HashMap<>();

    public static final Logger LOGGER = LoggerFactory.getLogger("bind-alias-plus");

    @Override
    public void onInitializeClient() {
        // register command alias
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(literal("alias")
                    .then(argument("name", StringArgumentType.word())
                            .then(argument("definition", StringArgumentType.greedyString())
                                    .suggests((context, builder) -> {
                                        String soFar = builder.getRemaining();
                                        if (soFar.isBlank()) {
                                            SuggestionsBuilder finalBuilder = builder;
                                            Aliases.aliasesWithoutArgs.forEach((name, alias) -> {
                                                finalBuilder.suggest(name);
                                            });
                                            Aliases.aliasesWithArgs.forEach((name, alias) -> {
                                                finalBuilder.suggest(name);
                                            });
                                            return builder.buildFuture();
                                        }
                                        int a = soFar.lastIndexOf("\\");
                                        int n = soFar.lastIndexOf("|");
                                        if (n < a /* it's under an arg's definition, don't need to provide alias name suggests*/)
                                            return builder.buildFuture();
                                        String currentToken = soFar.substring(n + 1);

                                        builder = builder.createOffset(builder.getStart() + n + 1);

                                        SuggestionsBuilder finalBuilder = builder;
                                        Aliases.aliasesWithoutArgs.keySet().forEach(alias -> {
                                            if (alias.startsWith(currentToken)) {
                                                finalBuilder.suggest(alias, Text.literal("alias without args"));
                                            }
                                        });
                                        Aliases.aliasesWithArgs.keySet().forEach(alias -> {
                                            if (alias.startsWith(currentToken)) {
                                                finalBuilder.suggest(alias, Text.literal("alias with args"));
                                            }
                                        });

                                        return builder.buildFuture();
                                    })
                                    .executes(context -> {
                                        String name = StringArgumentType.getString(context, "name");
                                        String def = StringArgumentType.getString(context, "definition");

                                        if (Aliases.aliasesWithArgs.containsKey(name)) {
                                            context.getSource().sendFeedback(Text.literal("Can't replace builtinAlias " + name));
                                            return 0;
                                        }

                                        AliasWithoutArgs aliasWithoutArgs = Aliases.aliasesWithoutArgs.get(name);
                                        if (aliasWithoutArgs != null && !(aliasWithoutArgs instanceof UserAlias)) {
                                            context.getSource().sendFeedback(Text.literal("Can't replace builtinAlias " + name));
                                            return 0;
                                        }

                                        Aliases.aliasesWithoutArgs.put(name, new UserAlias(def));

                                        context.getSource().sendFeedback(Text.literal("Alias " + name + " = " + def));
                                        return 1;
                                    }))));
        });
        // register command bind
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(literal("bind").then(argument("key", StringArgumentType.word()).then(argument("aliasName", StringArgumentType.word()).suggests((context, builder) -> {
                Aliases.aliasesWithoutArgs.keySet().forEach(builder::suggest);
//                                                        Aliases.aliasesWithArgs.keySet().forEach(builder::suggest);
                return builder.buildFuture();
            }).executes(context -> {
                String keyName = StringArgumentType.getString(context, "key");
                String aliasName = StringArgumentType.getString(context, "aliasName");

                boolean flag0 = true;//t -> +-aliasName binding pattern
                boolean flag = true;//t -> +aliasName or it doesn't contain +- and would be triggered when pressing down as default
                boolean flag1 = true;//t -> aliasName stays the same, else subString(1)
                AliasWithoutArgs alias = Aliases.aliasesWithoutArgs.get(aliasName);
                if (alias == null) {
                    flag1 = false;
                    if ((aliasName.startsWith("+")) || aliasName.startsWith("-")) {
                        alias = Aliases.aliasesWithoutArgs.get(aliasName.substring(1));
                        if (alias == null) {
                            context.getSource().sendFeedback(Text.literal("§cAlias " + aliasName + " does not exist!"));
                            return 0;
                        } else if (aliasName.startsWith("-")) flag = false;
                    } else {
                        flag0 = false;
                        context.getSource().sendFeedback(Text.literal("§cAlias " + aliasName + " does not exist!"));
                        return 0;
                    }
                } else {
                    flag = aliasName.startsWith("+");
                    flag0 = flag || aliasName.startsWith("-");
                }

                InputUtil.Key key = parseKey(keyName);
                if (key == null) {
                    context.getSource().sendFeedback(Text.literal("§cUnknown key: " + keyName));
                    return 0;
                }

                String aliasNameFinal = flag1 ? aliasName : aliasName.substring(1);
                String aliasNameFinalExtra = flag ? (flag1 ? "-" + aliasNameFinal.substring(1) : "-" + aliasNameFinal) : (flag1 ? "+" + aliasNameFinal.substring(1) : "+" + aliasNameFinal);
                if (flag0) {
                    AliasWithoutArgs aliasWithoutArgs = Aliases.aliasesWithoutArgs.get(aliasNameFinalExtra);
                    if (aliasWithoutArgs == null) aliasNameFinalExtra = "";
                } else aliasNameFinalExtra = "";

                BINDING_PLUS.put(key, flag ? new KeyBindingPlus(aliasNameFinal, aliasNameFinalExtra) : new KeyBindingPlus(aliasNameFinalExtra, aliasNameFinal));

                context.getSource().sendFeedback(Text.literal("§aBound key " + keyName + " to alias " + aliasName));
                return 1;
            }))));
        });

        //load builtin alias

        //load builtin aliasesWithArgs
        Aliases.aliasesWithArgs.put("log", new LogAlias());
        Aliases.aliasesWithArgs.put("slot", new SlotAlias());
        Aliases.aliasesWithArgs.put("swapSlot", new SwapSlotAlias());
        Aliases.aliasesWithArgs.put("builtinAttack", new AttackAlias());
        Aliases.aliasesWithArgs.put("builtinUse", new UseAlias());
        Aliases.aliasesWithArgs.put("builtinForward", new ForwardAlias());
        Aliases.aliasesWithArgs.put("builtinBack", new BackAlias());
        Aliases.aliasesWithArgs.put("builtinLeft", new LeftAlias());
        Aliases.aliasesWithArgs.put("builtinRight", new RightAlias());
        Aliases.aliasesWithArgs.put("builtinJump", new JumpAlias());
        Aliases.aliasesWithArgs.put("builtinSneak", new SneakAlias());
        Aliases.aliasesWithArgs.put("builtinSprint", new SprintAlias());
        Aliases.aliasesWithArgs.put("builtinDrop", new DropAlias());
        Aliases.aliasesWithArgs.put("wait", new WaitAlias());
        //load builtin aliasesWithoutArgs
        Aliases.aliasesWithoutArgs.put("swapHand", new SwapHandAlias());
        Aliases.aliasesWithoutArgs.put("+attack", new UserAlias("builtinAttack\\1"));
        Aliases.aliasesWithoutArgs.put("-attack", new UserAlias("builtinAttack\\0"));
        Aliases.aliasesWithoutArgs.put("+use", new UserAlias("builtinUse\\1"));
        Aliases.aliasesWithoutArgs.put("-use", new UserAlias("builtinUse\\0"));
        Aliases.aliasesWithoutArgs.put("+forward", new UserAlias("builtinForward\\1"));
        Aliases.aliasesWithoutArgs.put("-forward", new UserAlias("builtinForward\\0"));
        Aliases.aliasesWithoutArgs.put("+back", new UserAlias("builtinBack\\1"));
        Aliases.aliasesWithoutArgs.put("-back", new UserAlias("builtinBack\\0"));
        Aliases.aliasesWithoutArgs.put("+left", new UserAlias("builtinLeft\\1"));
        Aliases.aliasesWithoutArgs.put("-left", new UserAlias("builtinLeft\\0"));
        Aliases.aliasesWithoutArgs.put("+right", new UserAlias("builtinRight\\1"));
        Aliases.aliasesWithoutArgs.put("-right", new UserAlias("builtinRight\\0"));
        Aliases.aliasesWithoutArgs.put("+jump", new UserAlias("builtinJump\\1"));
        Aliases.aliasesWithoutArgs.put("-jump", new UserAlias("builtinJump\\0"));
        Aliases.aliasesWithoutArgs.put("+sneak", new UserAlias("builtinSneak\\1"));
        Aliases.aliasesWithoutArgs.put("-sneak", new UserAlias("builtinSneak\\0"));
        Aliases.aliasesWithoutArgs.put("+sprint", new UserAlias("builtinSprint\\1"));
        Aliases.aliasesWithoutArgs.put("-sprint", new UserAlias("builtinSprint\\0"));
        Aliases.aliasesWithoutArgs.put("drop", new UserAlias("builtinDrop\\0"));
        Aliases.aliasesWithoutArgs.put("dropStack", new UserAlias("builtinDrop\\1"));

        //put your elytra in slot 10 ( the first slot of the first row of your inventory, then you can do this
        // /alias equipElytra swapSlot\10\39
        // /alias jump +jump|wait\1|-jump
        // /alias +fly equipElytra|jump|wait\1|jump|slot\9|+use|wait\1|-use
        // /alias -fly equipElytra
        // /bind mouse5 +fly

    }

    private InputUtil.Key parseKey(String name) {
        InputUtil.Key key = null;
        try {
            key = InputUtil.fromTranslationKey("key.keyboard." + name.toLowerCase());
        } catch (Exception e) {
            BindAliasPlusClient.LOGGER.error(e.getMessage());
        }

        if (key == null) {
            if (name.toLowerCase().startsWith("mouse")) {
                try {
                    int button = Integer.parseInt(name.substring(5));
                    return InputUtil.Type.MOUSE.createFromCode(button - 1);
                } catch (Exception ignored) {
                }
            }
        }
        return key;
    }
}