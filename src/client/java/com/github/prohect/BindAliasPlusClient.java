package com.github.prohect;

import com.github.prohect.alias.AliasWithoutArgs;
import com.github.prohect.alias.Aliases;
import com.github.prohect.alias.UserAlias;
import com.github.prohect.alias.builtinAlias.*;
import com.mojang.brigadier.arguments.StringArgumentType;
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
                            .then(argument("aliasName", StringArgumentType.word())
                                    .suggests((context, builder) -> {
                                        Aliases.aliasesWithoutArgs.keySet().forEach(builder::suggest);
                                        Aliases.aliasesWithArgs.keySet().forEach(builder::suggest);
                                        return builder.buildFuture();
                                    })
                                    .then(argument("extra definition", StringArgumentType.greedyString())
                                            .suggests((context, builder) -> {
                                                String soFar = builder.getRemaining();
                                                int a = soFar.lastIndexOf("\\");
                                                int n = soFar.lastIndexOf("|");
                                                if (n < a || n == -1) return builder.buildFuture();
                                                String currentToken = soFar.substring(n + 1);

                                                builder = builder.createOffset(builder.getStart() + n + 1);

                                                com.mojang.brigadier.suggestion.SuggestionsBuilder finalBuilder = builder;
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
                                                String aliasName = StringArgumentType.getString(context, "aliasName");
                                                String def = StringArgumentType.getString(context, "extra definition");

                                                if (Aliases.aliasesWithArgs.containsKey(name)) {
                                                    context.getSource().sendFeedback(Text.literal("Can't replace builtinAlias " + name));
                                                    return 0;
                                                }

                                                AliasWithoutArgs aliasWithoutArgs = Aliases.aliasesWithoutArgs.get(name);
                                                if (aliasWithoutArgs != null && !(aliasWithoutArgs instanceof UserAlias)) {
                                                    context.getSource().sendFeedback(Text.literal("Can't replace builtinAlias " + name));
                                                    return 0;
                                                }

                                                Aliases.aliasesWithoutArgs.put(name, new UserAlias(aliasName + "\\" + def));

                                                context.getSource().sendFeedback(Text.literal("Alias " + name + " = " + aliasName + def));
                                                return 1;
                                            })))));
        });
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(literal("alias")
                    .then(argument("name", StringArgumentType.word())
                            .then(argument("aliasName", StringArgumentType.word())
                                    .suggests((context, builder) -> {
                                        Aliases.aliasesWithoutArgs.keySet().forEach(builder::suggest);
                                        Aliases.aliasesWithArgs.keySet().forEach(builder::suggest);
                                        return builder.buildFuture();
                                    })
                                    .executes(context -> {
                                        String name = StringArgumentType.getString(context, "name");
                                        String aliasName = StringArgumentType.getString(context, "aliasName");

                                        if (Aliases.aliasesWithArgs.containsKey(name)) {
                                            context.getSource().sendFeedback(Text.literal("Can't replace builtinAlias " + name));
                                            return 0;
                                        }

                                        AliasWithoutArgs aliasWithoutArgs = Aliases.aliasesWithoutArgs.get(name);
                                        if (aliasWithoutArgs != null && !(aliasWithoutArgs instanceof UserAlias)) {
                                            context.getSource().sendFeedback(Text.literal("Can't replace builtinAlias " + name));
                                            return 0;
                                        }

                                        Aliases.aliasesWithoutArgs.put(name, new UserAlias(aliasName));

                                        context.getSource().sendFeedback(Text.literal("Alias " + name + " = " + aliasName));
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
        Aliases.aliasesWithArgs.put("builtinAttack", new AttackAlias());
        Aliases.aliasesWithArgs.put("slot", new SlotAlias());
        Aliases.aliasesWithArgs.put("builtinUse", new UseAlias());
        Aliases.aliasesWithArgs.put("switchSlot", new SwitchSlotAlias());
        //load builtin aliasesWithoutArgs
        Aliases.aliasesWithoutArgs.put("helloWorld", new HelloWorldAlias());
        Aliases.aliasesWithoutArgs.put("swapHand", new SwapHandAlias());
        Aliases.aliasesWithoutArgs.put("+attack", new UserAlias("builtinAttack\\\\1"));
        Aliases.aliasesWithoutArgs.put("-attack", new UserAlias("builtinAttack\\\\0"));
        Aliases.aliasesWithoutArgs.put("+use", new UserAlias("builtinUse\\\\1"));
        Aliases.aliasesWithoutArgs.put("-use", new UserAlias("builtinUse\\\\0"));


        //load user alias defined by user, would be ... like read a json file locally and cast them into aliases
        /*
        Aliases.aliasesWithoutArgs.put("+kpw", new UserAlias("log\\\\ on key w pressed\\|helloWorld"));
        Aliases.aliasesWithoutArgs.put("-kpw", new UserAlias("log\\\\ on key w released"));
        Aliases.aliasesWithoutArgs.put("-kps", new UserAlias("log\\\\ on key s released"));*/

        //register keybinds
        /*
        BINDING_PLUS.put(InputUtil.Type.KEYSYM.createFromCode(GLFW.GLFW_KEY_W), new KeyBindingPlus("+kpw", "-kpw"));
        BINDING_PLUS.put(InputUtil.Type.KEYSYM.createFromCode(GLFW.GLFW_KEY_S), new KeyBindingPlus("helloWorld", "-kps"));*/
    }

    private InputUtil.Key parseKey(String name) {
        InputUtil.Key key = InputUtil.fromTranslationKey("key.keyboard." + name.toLowerCase());
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