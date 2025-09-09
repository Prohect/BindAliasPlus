package com.github.prohect;

import com.github.prohect.alias.Alias;
import com.github.prohect.alias.AliasWithoutArgs;
import com.github.prohect.alias.UserAlias;
import com.github.prohect.alias.builtinAlias.*;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

import static com.github.prohect.BindAliasPlus.MOD_ID;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class BindAliasPlusClient implements ClientModInitializer {
    public static final BindAliasPlusClient INSTANCE = new BindAliasPlusClient();
    public static final Path cfgPath = FabricLoader.getInstance().getConfigDir().resolve(MOD_ID + ".cfg");

    public static final ArrayDeque<KeyPressed> KEY_QUEUE = new ArrayDeque<>();
    public static final Map<InputUtil.Key, KeyBindingPlus> BINDING_PLUS = new HashMap<>();

    public static final Logger LOGGER = LoggerFactory.getLogger("bind-alias-plus");


    /*
     * put your elytra in slot 10 ( the first slot of the first row of your inventory,
     * and firework in slot 19 ( the first slot of the second row of your inventory,
     * no need to take place of your hotbars,
     * then you can do this
     * */
    // /alias equipElytra swapSlot\10\39
    // /alias jump +jump wait\1 -jump
    // /alias +fly equipElytra jump wait\1 jump swapSlot\19 +use -use
    // /alias -fly equipElytra swapSlot\19
    // /bind mouse5 +fly

    /*
     * put your bow in slot 11 ( the second slot of the first row of your inventory
     * then u can do this
     * */
    // /alias +bow swapSlot\11 +use
    // /alias -bow -use swapSlot\11
    // /bind mouse4 +bow

    @Override
    public void onInitializeClient() {
        //load builtin alias

        //load builtin aliasesWithArgs
        WaitAlias waitAlias;
        new AttackAlias().putToAliasesWithArgs_notSuggested("builtinAttack");
        new UseAlias().putToAliasesWithArgs_notSuggested("builtinUse");
        new ForwardAlias().putToAliasesWithArgs_notSuggested("builtinForward");
        new BackAlias().putToAliasesWithArgs_notSuggested("builtinBack");
        new LeftAlias().putToAliasesWithArgs_notSuggested("builtinLeft");
        new RightAlias().putToAliasesWithArgs_notSuggested("builtinRight");
        new JumpAlias().putToAliasesWithArgs_notSuggested("builtinJump");
        new SneakAlias().putToAliasesWithArgs_notSuggested("builtinSneak");
        new SprintAlias().putToAliasesWithArgs_notSuggested("builtinSprint");
        new DropAlias().putToAliasesWithArgs_notSuggested("builtinDrop").addToLockCursorBlackList();
        new LogAlias().putToAliasesWithArgs("log");
        new SlotAlias().putToAliasesWithArgs("slot");
        new SwapSlotAlias().putToAliasesWithArgs("swapSlot");
        waitAlias = new WaitAlias().putToAliasesWithArgs("wait");
        new YawAlias().putToAliasesWithArgs("yaw");
        new PitchAlias().putToAliasesWithArgs("pitch");
        new SetYawAlias().putToAliasesWithArgs("setYaw");
        new SetPitchAlias().putToAliasesWithArgs("setPitch");
        new AliasAlias().putToAliasesWithArgs("alias");
        new BindAlias().putToAliasesWithArgs("bind");
        new UnbindAlias().putToAliasesWithArgs("unbind");
        new SayAlias().putToAliasesWithArgs("say");
        new SendCommandAlias().putToAliasesWithArgs("sendCommand");

        //load builtin aliasesWithoutArgs
        new SwapHandAlias().putToAliasesWithoutArgs("swapHand");
        new ReloadCFGAlias().putToAliasesWithoutArgs("reloadCFG");
        new UserAlias("builtinAttack\\1").putToAliasesWithoutArgs("+attack");
        new UserAlias("builtinAttack\\0").putToAliasesWithoutArgs("-attack");
        new UserAlias("builtinUse\\1").putToAliasesWithoutArgs("+use");
        new UserAlias("builtinUse\\0").putToAliasesWithoutArgs("-use");
        new UserAlias("builtinForward\\1").putToAliasesWithoutArgs("+forward");
        new UserAlias("builtinForward\\0").putToAliasesWithoutArgs("-forward");
        new UserAlias("builtinBack\\1").putToAliasesWithoutArgs("+back");
        new UserAlias("builtinBack\\0").putToAliasesWithoutArgs("-back");
        new UserAlias("builtinLeft\\1").putToAliasesWithoutArgs("+left");
        new UserAlias("builtinLeft\\0").putToAliasesWithoutArgs("-left");
        new UserAlias("builtinRight\\1").putToAliasesWithoutArgs("+right");
        new UserAlias("builtinRight\\0").putToAliasesWithoutArgs("-right");
        new UserAlias("builtinJump\\1").putToAliasesWithoutArgs("+jump");
        new UserAlias("builtinJump\\0").putToAliasesWithoutArgs("-jump");
        new UserAlias("builtinSneak\\1").putToAliasesWithoutArgs("+sneak");
        new UserAlias("builtinSneak\\0").putToAliasesWithoutArgs("-sneak");
        new UserAlias("builtinSprint\\1").putToAliasesWithoutArgs("+sprint");
        new UserAlias("builtinSprint\\0").putToAliasesWithoutArgs("-sprint");
        new UserAlias("builtinDrop\\0").putToAliasesWithoutArgs("drop");
        new UserAlias("builtinDrop\\1").putToAliasesWithoutArgs("dropStack");

        // register command alias
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) ->
                dispatcher.register(literal("alias")
                        .then(argument("keyName", StringArgumentType.word())
                                .then(argument("args", StringArgumentType.greedyString())
                                        .suggests((context, builder) -> getSuggestions4aliasDefinitionCompletableFuture(builder))
                                        .executes(context -> {
                                            String name = StringArgumentType.getString(context, "keyName");
                                            String definition = StringArgumentType.getString(context, "args");
                                            return switch (commandAliasExecute(name, definition)) {
                                                case 1 -> {
                                                    context.getSource().sendFeedback(Text.literal("Alias " + name + " = " + definition));
                                                    yield 1;
                                                }
                                                case 2, 3 -> {
                                                    context.getSource().sendFeedback(Text.literal("Can't replace builtinAlias " + name));
                                                    yield 0;
                                                }
                                                default -> 0;
                                            };
                                        })))));
        // register command bindByAliasName
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) ->
                dispatcher.register(literal("bindByAliasName")
                        .then(argument("key", StringArgumentType.word())
                                .then(argument("aliasName", StringArgumentType.word())
                                        .suggests((context, builder) -> {
                                            Alias.aliasesWithoutArgs.keySet().forEach(builder::suggest);
                                            return builder.buildFuture();
                                        })
                                        .executes(context -> {
                                            String keyName = StringArgumentType.getString(context, "key");
                                            String aliasName = StringArgumentType.getString(context, "aliasName");
                                            return switch (commandBindByAliasNameExecute(keyName, aliasName)) {
                                                case 1 -> {
                                                    context.getSource().sendFeedback(Text.literal("§aBound key " + keyName + " to alias " + aliasName));
                                                    yield 1;
                                                }
                                                case 2, 3 -> {
                                                    context.getSource().sendFeedback(Text.literal("§cAlias " + aliasName + " does not exist!"));
                                                    yield 0;
                                                }
                                                case 4 -> {
                                                    context.getSource().sendFeedback(Text.literal("§cUnknown key: " + keyName));
                                                    yield 0;
                                                }
                                                default -> 0;
                                            };
                                        })))));
        // register command bind
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) ->
                dispatcher.register(literal("bind")
                        .then(argument("key", StringArgumentType.word())
                                .then(argument("args", StringArgumentType.greedyString())
                                        .suggests((context, builder) -> getSuggestions4aliasDefinitionCompletableFuture(builder))
                                        .executes(context -> {
                                            String keyName = StringArgumentType.getString(context, "key");
                                            String definition = StringArgumentType.getString(context, "args");
                                            return switch (commandBindExecute(keyName, definition)) {
                                                case 1 -> {
                                                    context.getSource().sendFeedback(Text.literal("§aBound key " + keyName + " to alias " + definition));
                                                    yield 1;
                                                }
                                                case 2 -> {
                                                    context.getSource().sendFeedback(Text.literal("§cUnknown key: " + keyName));
                                                    yield 0;
                                                }
                                                case 3 -> {
                                                    context.getSource().sendFeedback(Text.literal("bind " + keyName + " = " + definition));
                                                    yield 0;
                                                }
                                                default -> 0;
                                            };
                                        })))));
        // register command bind
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) ->
                dispatcher.register(literal("unbind")
                        .then(argument("key", StringArgumentType.word())
                                .executes(context -> {
                                    String keyName = StringArgumentType.getString(context, "key");
                                    return switch (commandUnbindExecute(keyName)) {
                                        case 0 -> {
                                            context.getSource().sendFeedback(Text.literal("§cUnknown key: " + keyName));
                                            yield 0;
                                        }
                                        case 1 -> {
                                            context.getSource().sendFeedback(Text.literal("§cUnbind key: " + keyName));
                                            yield 1;
                                        }
                                        default -> 0;
                                    };
                                }))));
        // register command reloadBindAlias
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) ->
                dispatcher.register(literal("reloadCFG").executes(context -> {
                    if (MinecraftClient.getInstance().player == null) return 0;
                    loadCFG();
                    return 1;
                })));

        // load cfg
        ClientPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            if (MinecraftClient.getInstance().player != null) waitAlias.run("1", "reloadCFG");
        });
    }

    public void loadCFG() {
        try {
            if (cfgPath.toFile().createNewFile()) return;
        } catch (IOException e) {
            LOGGER.error("Could not create file {}", cfgPath, e);
        }
        byte[] data = null;
        try (InputStream inputStream = Files.newInputStream(cfgPath)) {
            data = new byte[inputStream.available()];
            while (inputStream.available() > 0) inputStream.read(data);
        } catch (IOException e) {
            LOGGER.error("Failed to open cfg file", e);
        }
        if (data == null) return;
        String cfg = new String(data);
        assert MinecraftClient.getInstance().player != null;
        cfg.lines().forEach(line -> {
            try {
                if (line.startsWith("alias ")) {
                    String string = line.substring("alias ".length());
                    int i = string.indexOf(' ');
                    String substring = string.substring(0, i);
                    commandAliasExecute(substring, string.substring(i + 1));
                } else if (line.startsWith("bind ")) {
                    String string = line.substring("bind ".length());
                    int i = string.indexOf(' ');
                    String substring = string.substring(0, i);
                    commandBindExecute(substring, string.substring(i + 1));
                } else if (line.startsWith("bindByAliasName ")) {
                    String string = line.substring("bindByAliasName ".length());
                    int i = string.indexOf(' ');
                    String substring = string.substring(0, i);
                    commandBindByAliasNameExecute(substring, string.substring(i + 1));
                } else if (line.startsWith("unbind ")) {
                    String string = line.substring("unbind ".length());
                    if (string.indexOf(' ') == -1)
                        commandUnbindExecute(string);
                } else {
                    BindAliasPlusClient.LOGGER.warn("Unknown command: {}", line);
                }
            } catch (Exception e) {
                BindAliasPlusClient.LOGGER.warn("Failed to load CFG file", e);
            }
        });

    }

    private int commandUnbindExecute(String keyName) {
        InputUtil.Key key = parseKey(keyName);
        if (key == null) return 0;
        BINDING_PLUS.remove(key);
        return 1;
    }

    private int commandBindExecute(String keyName, String args) {
        if (commandBindByAliasNameExecute(keyName, args) == 1) return 1;
        final StringBuilder aliasName = new StringBuilder();
        final StringBuilder aliasName1 = new StringBuilder();
        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        final Random rng = new Random();
        for (int i = 0; i < 16; i++)
            aliasName.append(CHARACTERS.charAt(rng.nextInt(CHARACTERS.length())));
        for (int i = 0; i < 16; i++)
            aliasName1.append(CHARACTERS.charAt(rng.nextInt(CHARACTERS.length())));

        InputUtil.Key key = parseKey(keyName);
        if (key == null) return 2;

        Alias.aliasesWithoutArgs_fromBindCommand.put(String.valueOf(aliasName), new UserAlias(args));
        String oppositeDefinition = Alias.getOppositeDefinition(args);
        if (!oppositeDefinition.isBlank())
            Alias.aliasesWithoutArgs_fromBindCommand.put(String.valueOf(aliasName1), new UserAlias(oppositeDefinition));
        BINDING_PLUS.put(key, new KeyBindingPlus(aliasName.toString(), oppositeDefinition.isBlank() ? "" : aliasName1.toString()));
        return 3;
    }

    private int commandAliasExecute(String aliasName, String definition) {
        if (Alias.aliasesWithArgs_notSuggested.containsKey(aliasName) || Alias.aliasesWithArgs.containsKey(aliasName))
            return 2;
        AliasWithoutArgs<?> aliasWithoutArgs = Alias.aliasesWithoutArgs.get(aliasName);
        if (aliasWithoutArgs != null && !(aliasWithoutArgs instanceof UserAlias)) return 3;
        Alias.aliasesWithoutArgs.put(aliasName, new UserAlias(definition));
        return 1;
    }

    private int commandBindByAliasNameExecute(String keyName, String aliasName) {

        boolean flag0 = true;//t -> +-aliasName binding pattern
        boolean flag = true;//t -> +aliasName or it doesn't contain +- and would be triggered when pressing down as default
        boolean flag1 = true;//t -> aliasName stays the same, else subString(1)
        AliasWithoutArgs<?> alias = Alias.aliasesWithoutArgs.get(aliasName);
        if (alias == null) {
            flag1 = false;
            if ((aliasName.startsWith("+")) || aliasName.startsWith("-")) {
                alias = Alias.aliasesWithoutArgs.get(aliasName.substring(1));
                if (alias == null) return 2;
                else if (aliasName.startsWith("-")) flag = false;
            } else {
                flag0 = false;
                return 3;
            }
        } else {
            flag = !aliasName.startsWith("-");
            flag0 = flag || aliasName.startsWith("-");
        }

        InputUtil.Key key = parseKey(keyName);
        if (key == null) return 4;

        String aliasNameFinal = flag1 ? aliasName : aliasName.substring(1);
        String aliasNameFinalExtra = flag ? (flag1 ? "-" + aliasNameFinal.substring(1) : "-" + aliasNameFinal) : (flag1 ? "+" + aliasNameFinal.substring(1) : "+" + aliasNameFinal);
        if (flag0) {
            AliasWithoutArgs<?> aliasWithoutArgs = Alias.aliasesWithoutArgs.get(aliasNameFinalExtra);
            if (aliasWithoutArgs == null) aliasNameFinalExtra = "";
        } else aliasNameFinalExtra = "";

        BINDING_PLUS.put(key, flag ? new KeyBindingPlus(aliasNameFinal, aliasNameFinalExtra) : new KeyBindingPlus(aliasNameFinalExtra, aliasNameFinal));

        return 1;
    }

    private static CompletableFuture<Suggestions> getSuggestions4aliasDefinitionCompletableFuture(SuggestionsBuilder builder) {
        String soFar = builder.getRemaining();
        if (soFar.isBlank()) {
            SuggestionsBuilder finalBuilder = builder;
            Alias.aliasesWithoutArgs.forEach((name, alias) -> finalBuilder.suggest(name));
            Alias.aliasesWithArgs.forEach((name, alias) -> finalBuilder.suggest(name));
            return builder.buildFuture();
        }
        int a = soFar.lastIndexOf(Alias.divider4AliasArgs);
        int n = soFar.lastIndexOf(Alias.divider4AliasDefinition);
        if (n < a /* it's under an arg's args, don't need to provide alias keyName suggests*/)
            return builder.buildFuture();
        String currentToken = soFar.substring(n + 1);

        builder = builder.createOffset(builder.getStart() + n + 1);

        SuggestionsBuilder finalBuilder = builder;
        Alias.aliasesWithoutArgs.keySet().forEach(alias -> {
            if (alias.startsWith(currentToken)) finalBuilder.suggest(alias, Text.literal("alias without args"));
        });
        Alias.aliasesWithArgs.keySet().forEach(alias -> {
            if (alias.startsWith(currentToken)) finalBuilder.suggest(alias, Text.literal("alias with args"));
        });

        return builder.buildFuture();
    }

    private InputUtil.Key parseKey(String name) {
        InputUtil.Key key = null;
        try {
            key = InputUtil.fromTranslationKey("key.keyboard." + name.toLowerCase());
        } catch (Exception ignored) {
        }
        if (key == null) {
            if (name.toLowerCase().startsWith("mouse")) {
                try {
                    int button = Integer.parseInt(name.substring(5));
                    return InputUtil.Type.MOUSE.createFromCode(button - 1);
                } catch (Exception e) {
                    BindAliasPlusClient.LOGGER.warn("Invalid key definition: {}", name);
                }
            }
        }
        return key;
    }
}