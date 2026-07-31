package com.github.prohect;

import static com.github.prohect.BindAlias.MOD_ID;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

import com.github.prohect.alias.Alias;
import com.github.prohect.alias.AliasWithArgs;
import com.github.prohect.alias.AliasWithoutArgs;
import com.github.prohect.alias.BuiltinAliasWithDoubleArgs;
import com.github.prohect.alias.BuiltinAliasWithIntegerArgs;
import com.github.prohect.alias.UserAlias;
import com.github.prohect.alias.builtinAlias.*;
import com.github.prohect.mcp.GameChannels;
import com.github.prohect.mcp.McpHttpServer;
import com.github.prohect.mcp.RecipeBookHelper;
import com.github.prohect.mcp.SoundCapture;
import com.github.prohect.mcp.StateTracker;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BindAliasClient implements ClientModInitializer {

    public static final BindAliasClient INSTANCE = new BindAliasClient();
    public static final Path cfgPath = FabricLoader.getInstance().getConfigDir().resolve(MOD_ID + ".cfg");

    public static final ArrayDeque<KeyPressed> KEY_QUEUE = new ArrayDeque<>();
    public static final Map<InputUtil.Key, BindAliasKeyBinding> BINDING_PLUS = new HashMap<>();

    public static final Logger LOGGER = LoggerFactory.getLogger("bind-alias");

    public static Screen currentScreen = null;
    // Silent mode flag - when true, suppresses feedback messages in chat
    public static boolean silentMode = false;

    /**
     * Tick counter — incremented every client tick via {@link ClientTickEvents#START_CLIENT_TICK}.
     */
    public static long currentTick = 0;
    /**
     * Tick at which the last {@link ClientPlayConnectionEvents#JOIN} fired, -1 if never joined.
     */
    public static long joinTick = -1;

    /*
     * put your elytra in slot 10 ( the first slot of the first row of your inventory, and firework in slot 19 ( the first slot
     * of the second row of your inventory, no need to take place of your hotbars, then you can do this
     */
    // /alias equipElytra swapSlot\10\39
    // /alias jump +jump wait\1 -jump
    // /alias +fly equipElytra jump wait\1 jump swapSlot\19 +use -use
    // /alias -fly equipElytra swapSlot\19
    // /bind mouse5 +fly

    /*
     * put your bow in slot 11 ( the second slot of the first row of your inventory then u can do this
     */
    // /alias +bow swapSlot\11 +use
    // /alias -bow -use swapSlot\11
    // /bind mouse4 +bow

    @Override
	public void onInitializeClient() {
		// load builtin alias

		// load builtin aliasesWithArgs
		new AttackAlias().putToAliasesWithArgs_notSuggested().addToScreenBlackList();
		new UseAlias().putToAliasesWithArgs_notSuggested().addToScreenBlackList();
		new ForwardAlias().putToAliasesWithArgs_notSuggested();
		new BackAlias().putToAliasesWithArgs_notSuggested();
		new LeftAlias().putToAliasesWithArgs_notSuggested();
		new RightAlias().putToAliasesWithArgs_notSuggested();
		new JumpAlias().putToAliasesWithArgs_notSuggested();
		new SneakAlias().putToAliasesWithArgs_notSuggested();
		new SprintAlias().putToAliasesWithArgs_notSuggested();
		new DropAlias().putToAliasesWithArgs_notSuggested();
		new ScreenshotAlias().putToAliasesWithArgs_notSuggested();
		new PlayerListAlias().putToAliasesWithArgs_notSuggested();
		new FreeCursorAlias().putToAliasesWithArgs_notSuggested();
		new EscAlias().putToAliasesWithArgs_notSuggested();
		new AdvancementsAlias().putToAliasesWithArgs_notSuggested();
		new DebugOverlayAlias().putToAliasesWithArgs_notSuggested();
		new LogAlias().putToAliasesWithArgs();
		new SlotAlias().putToAliasesWithArgs();
		new SwapSlotAlias().putToAliasesWithArgs();
		new WaitAlias().putToAliasesWithArgs();
		new YawAlias().putToAliasesWithArgs();
		new PitchAlias().putToAliasesWithArgs();
		new SetYawAlias().putToAliasesWithArgs();
		new SetPitchAlias().putToAliasesWithArgs();
		new AliasAlias().putToAliasesWithArgs();
		new com.github.prohect.alias.builtinAlias.BindAlias().putToAliasesWithArgs();
		new UnbindAlias().putToAliasesWithArgs();
		new SayAlias().putToAliasesWithArgs();
		new LocalSayAlias().putToAliasesWithArgs();
		new SendCommandAlias().putToAliasesWithArgs();
		new SilentAlias().putToAliasesWithArgs_notSuggested();
		new SetPerspectiveAlias().putToAliasesWithArgs_notSuggested();
		new VarAlias().putToAliasesWithArgs();
		new LockAlias().putToAliasesWithArgs_notSuggested();
		new RunAliasAlias().putToAliasesWithArgs_notSuggested();
		new OpenInventoryAlias().putToAliasesWithArgs_notSuggested();
		new ApplyRecipeAlias().putToAliasesWithArgs();

		// load builtin aliasesWithoutArgs
		new CyclePerspectiveAlias().putToAliasesWithoutArgs();
		new SwapHandAlias().putToAliasesWithoutArgs();
		new PickItemAlias().putToAliasesWithoutArgs();
		new ToggleInventoryAlias().putToAliasesWithoutArgs();
		new ShutdownAlias().putToAliasesWithoutArgs_notSuggested();
		new ReloadCFGAlias().putToAliasesWithoutArgs();
		new UnloadCFGAliasesAlias().putToAliasesWithoutArgs();
		new UnloadCFGBindsAlias().putToAliasesWithoutArgs();
		new UnloadCFGVarsAlias().putToAliasesWithoutArgs();
		new UnloadCFGAllAlias().putToAliasesWithoutArgs();
		new UnloadUserAliasesAlias().putToAliasesWithoutArgs();
		new UnloadUserBindsAlias().putToAliasesWithoutArgs();
		new UnloadUserVarsAlias().putToAliasesWithoutArgs();
		new UnloadUserAllAlias().putToAliasesWithoutArgs();
		new UserAlias("builtinAttack\\1", false, true).putToAliasesWithoutArgs("+attack");
		new UserAlias("builtinAttack\\0", false, true).putToAliasesWithoutArgs("-attack");
		new UserAlias("builtinUse\\1", false, true).putToAliasesWithoutArgs("+use");
		new UserAlias("builtinUse\\0", false, true).putToAliasesWithoutArgs("-use");
		new UserAlias("builtinForward\\1", false, true).putToAliasesWithoutArgs("+forward");
		new UserAlias("builtinForward\\0", false, true).putToAliasesWithoutArgs("-forward");
		new UserAlias("builtinBack\\1", false, true).putToAliasesWithoutArgs("+back");
		new UserAlias("builtinBack\\0", false, true).putToAliasesWithoutArgs("-back");
		new UserAlias("builtinLeft\\1", false, true).putToAliasesWithoutArgs("+left");
		new UserAlias("builtinLeft\\0", false, true).putToAliasesWithoutArgs("-left");
		new UserAlias("builtinRight\\1", false, true).putToAliasesWithoutArgs("+right");
		new UserAlias("builtinRight\\0", false, true).putToAliasesWithoutArgs("-right");
		new UserAlias("builtinJump\\1", false, true).putToAliasesWithoutArgs("+jump");
		new UserAlias("builtinJump\\0", false, true).putToAliasesWithoutArgs("-jump");
		new UserAlias("builtinSneak\\1", false, true).putToAliasesWithoutArgs("+sneak");
		new UserAlias("builtinSneak\\0", false, true).putToAliasesWithoutArgs("-sneak");
		new UserAlias("builtinSprint\\1", false, true).putToAliasesWithoutArgs("+sprint");
		new UserAlias("builtinSprint\\0", false, true).putToAliasesWithoutArgs("-sprint");
		new UserAlias("builtinDrop\\1", false, true).putToAliasesWithoutArgs("+drop");
		new UserAlias("builtinDrop\\0", false, true).putToAliasesWithoutArgs("-drop");
		new UserAlias("builtinScreenshot\\1", false, true).putToAliasesWithoutArgs("+screenshot");
		new UserAlias("builtinScreenshot\\0", false, true).putToAliasesWithoutArgs("-screenshot");
		new UserAlias("builtinPlayerList\\1", false, true).putToAliasesWithoutArgs("+playerList");
		new UserAlias("builtinPlayerList\\0", false, true).putToAliasesWithoutArgs("-playerList");
		new UserAlias("builtinFreeCursor\\1", false, true).putToAliasesWithoutArgs_notSuggested("+freeCursor");
		new UserAlias("builtinFreeCursor\\0", false, true).putToAliasesWithoutArgs_notSuggested("-freeCursor");
		new UserAlias("builtinEsc\\1", false, true).putToAliasesWithoutArgs("esc");
		new UserAlias("builtinEsc\\0", false, true).putToAliasesWithoutArgs("closeScreen");
		new UserAlias("builtinAdvancements\\1", false, true).putToAliasesWithoutArgs("+advancements");
		new UserAlias("builtinAdvancements\\0", false, true).putToAliasesWithoutArgs("-advancements");
		new UserAlias("builtinDebugOverlay\\1", false, true).putToAliasesWithoutArgs("+debugOverlay");
		new UserAlias("builtinDebugOverlay\\0", false, true).putToAliasesWithoutArgs("-debugOverlay");
		new UserAlias("builtinOpenInventory\\1", false, true).putToAliasesWithoutArgs("+openInventory");
		new UserAlias("builtinOpenInventory\\0", false, true).putToAliasesWithoutArgs("-openInventory");
		new UserAlias("builtinSilent\\1", false, true).putToAliasesWithoutArgs("+silent");
		new UserAlias("builtinSilent\\0", false, true).putToAliasesWithoutArgs("-silent");
		new UserAlias("builtinSetPerspective\\0", false, true).putToAliasesWithoutArgs("FPS");
		new UserAlias("builtinSetPerspective\\1", false, true).putToAliasesWithoutArgs("TPS");
		new UserAlias("builtinSetPerspective\\2", false, true).putToAliasesWithoutArgs("TPS2");
		// Lock aliases (lock/unlock game actions to prevent user input interference)
		// +lock\<action> / -lock\<action> — compact arg-based form with suggestions
		new LockAlias_OnLock().putToAliasesWithArgs();
		new LockAlias_Unlock().putToAliasesWithArgs();
		// Reapply alias — manually re-assert a held-down boolean alias
		new ReapplyAlias().putToAliasesWithArgs();

		// init cfg file (create if not exists)
		try {
			cfgPath.toFile().createNewFile();
		} catch (IOException e) {
			LOGGER.error("{}Could not create file {}", tickPrefix(), cfgPath, e);
		}

		// tick counter for tick-since-join logging
		ClientTickEvents.START_CLIENT_TICK.register(client -> currentTick++);

		// register CFG autoload on world join
		ClientPlayConnectionEvents.JOIN.register((handler, sender, client) -> client.execute(() -> {
			joinTick = currentTick;
			GameChannels.init();
			GameChannels.resetAll();
			StateTracker.reset();
			RecipeBookHelper.reset();
			// feed the sound channel (SoundManager does not exist yet during onInitializeClient;
			// registration is deduped by listener identity, so repeat joins are harmless)
			SoundCapture.register();
			loadCFG();
		}));

		// clear mod state on disconnect (restore locked keys, etc.)
		ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> client.execute(() -> {
			LockAlias.clearAllLocks();
			KEY_QUEUE.clear();
			silentMode = false;
		}));

		// register command alias
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher
				.register(literal("alias").then(argument("keyName", StringArgumentType.word())
						.then(argument("args", StringArgumentType.greedyString())
								.suggests(
										(context, builder) -> getSuggestions4aliasDefinitionCompletableFuture(builder))
								.executes(context -> {
									String name = StringArgumentType.getString(context, "keyName");
									String definition = StringArgumentType.getString(context, "args");
									return switch (commandAliasExecute(name, definition)) {
										case 1 -> {
											if (!silentMode) {
												context.getSource().sendFeedback(
														Text.literal("Alias " + name + " = " + definition));
											}
											yield 1;
										}
										case 2, 3 -> {
											if (!silentMode) {
												context.getSource().sendFeedback(
														Text.literal("Can't replace builtinAlias " + name));
											}
											yield 0;
										}
										default -> 0;
									};
								})))));
		// register command bindByAliasName
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher
				.register(literal("bindByAliasName").then(argument("key", StringArgumentType.word())
						.then(argument("aliasName", StringArgumentType.word()).suggests((context, builder) -> {
							Alias.aliasesWithoutArgs.keySet().forEach(builder::suggest);
							return builder.buildFuture();
						}).executes(context -> {
							String keyName = StringArgumentType.getString(context, "key");
							String aliasName = StringArgumentType.getString(context, "aliasName");
							return switch (commandBindByAliasNameExecute(keyName, aliasName)) {
								case 1 -> {
									if (!silentMode) {
										context.getSource().sendFeedback(
												Text.literal("§aBound key " + keyName + " to alias " + aliasName));
									}
									yield 1;
								}
								case 2, 3 -> {
									if (!silentMode) {
										context.getSource().sendFeedback(
												Text.literal("§cAlias " + aliasName + " does not exist!"));
									}
									yield 0;
								}
								case 4 -> {
									if (!silentMode) {
										context.getSource().sendFeedback(Text.literal("§cUnknown key: " + keyName));
									}
									yield 0;
								}
								default -> 0;
							};
						})))));
		// register command bind
		ClientCommandRegistrationCallback.EVENT.register((dispatcher,
				registryAccess) -> dispatcher.register(literal("bind").then(argument("key", StringArgumentType.word())
						.then(argument("args", StringArgumentType.greedyString())
								.suggests(
										(context, builder) -> getSuggestions4aliasDefinitionCompletableFuture(builder))
								.executes(context -> {
									String keyName = StringArgumentType.getString(context, "key");
									String definition = StringArgumentType.getString(context, "args");
									return switch (commandBindExecute(keyName, definition)) {
										case 1 -> {
											if (!silentMode) {
												context.getSource().sendFeedback(Text
														.literal("§aBound key " + keyName + " to alias " + definition));
											}
											yield 1;
										}
										case 2 -> {
											if (!silentMode) {
												context.getSource()
														.sendFeedback(Text.literal("§cUnknown key: " + keyName));
											}
											yield 0;
										}
										case 3 -> {
											if (!silentMode) {
												context.getSource().sendFeedback(
														Text.literal("bind " + keyName + " = " + definition));
											}
											yield 0;
										}
										default -> 0;
									};
								})))));
		// register command bind
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher
				.register(literal("unbind").then(argument("key", StringArgumentType.word()).executes(context -> {
					String keyName = StringArgumentType.getString(context, "key");
					return switch (commandUnbindExecute(keyName)) {
						case 0 -> {
							if (!silentMode) {
								context.getSource().sendFeedback(Text.literal("§cUnknown key: " + keyName));
							}
							yield 0;
						}
						case 1 -> {
							if (!silentMode) {
								context.getSource().sendFeedback(Text.literal("§cUnbind key: " + keyName));
							}
							yield 1;
						}
						default -> 0;
					};
				}))));
		// register command reloadBindAlias
		ClientCommandRegistrationCallback.EVENT
				.register((dispatcher, registryAccess) -> dispatcher.register(literal("reloadCFG").executes(context -> {
					if (MinecraftClient.getInstance().player == null)
						return 0;
					loadCFG();
					return 1;
				})));
		// register command unloadCFGAliases
		ClientCommandRegistrationCallback.EVENT.register(
				(dispatcher, registryAccess) -> dispatcher.register(literal("unloadCFGAliases").executes(context -> {
					if (MinecraftClient.getInstance().player == null)
						return 0;
					new UnloadCFGAliasesAlias().run("");
					if (!silentMode) {
						context.getSource().sendFeedback(Text.literal("\u00a7aUnloaded all autoloaded aliases"));
					}
					return 1;
				})));
		// register command unloadCFGBinds
		ClientCommandRegistrationCallback.EVENT.register(
				(dispatcher, registryAccess) -> dispatcher.register(literal("unloadCFGBinds").executes(context -> {
					if (MinecraftClient.getInstance().player == null)
						return 0;
					new UnloadCFGBindsAlias().run("");
					if (!silentMode) {
						context.getSource().sendFeedback(Text.literal("\u00a7aUnloaded all autoloaded keybindings"));
					}
					return 1;
				})));
		// register command unloadCFGVars
		ClientCommandRegistrationCallback.EVENT.register(
				(dispatcher, registryAccess) -> dispatcher.register(literal("unloadCFGVars").executes(context -> {
					if (MinecraftClient.getInstance().player == null)
						return 0;
					new UnloadCFGVarsAlias().run("");
					if (!silentMode) {
						context.getSource().sendFeedback(Text.literal("\u00a7aUnloaded all autoloaded variables"));
					}
					return 1;
				})));
		// register command unloadCFGAll
		ClientCommandRegistrationCallback.EVENT.register(
				(dispatcher, registryAccess) -> dispatcher.register(literal("unloadCFGAll").executes(context -> {
					if (MinecraftClient.getInstance().player == null)
						return 0;
					new UnloadCFGAllAlias().run("");
					if (!silentMode) {
						context.getSource().sendFeedback(
								Text.literal("\u00a7aUnloaded all autoloaded aliases, keybindings, and variables"));
					}
					return 1;
				})));
		// register command unloadUserAliases
		ClientCommandRegistrationCallback.EVENT.register(
				(dispatcher, registryAccess) -> dispatcher.register(literal("unloadUserAliases").executes(context -> {
					if (Minecraft.getInstance().player == null)
						return 0;
					new UnloadUserAliasesAlias().run("");
					if (!silentMode) {
						context.getSource().sendFeedback(Component.literal("§aUnloaded all runtime-defined aliases"));
					}
					return 1;
				})));
		// register command unloadUserBinds
		ClientCommandRegistrationCallback.EVENT.register(
				(dispatcher, registryAccess) -> dispatcher.register(literal("unloadUserBinds").executes(context -> {
					if (Minecraft.getInstance().player == null)
						return 0;
					new UnloadUserBindsAlias().run("");
					if (!silentMode) {
						context.getSource().sendFeedback(Component.literal("§aUnloaded all runtime-defined keybindings"));
					}
					return 1;
				})));
		// register command unloadUserVars
		ClientCommandRegistrationCallback.EVENT.register(
				(dispatcher, registryAccess) -> dispatcher.register(literal("unloadUserVars").executes(context -> {
					if (Minecraft.getInstance().player == null)
						return 0;
					new UnloadUserVarsAlias().run("");
					if (!silentMode) {
						context.getSource().sendFeedback(Component.literal("§aUnloaded all runtime-defined variables"));
					}
					return 1;
				})));
		// register command unloadUserAll
		ClientCommandRegistrationCallback.EVENT.register(
				(dispatcher, registryAccess) -> dispatcher.register(literal("unloadUserAll").executes(context -> {
					if (Minecraft.getInstance().player == null)
						return 0;
					new UnloadUserAllAlias().run("");
					if (!silentMode) {
						context.getSource().sendFeedback(
								Component.literal("§aUnloaded all runtime-defined aliases, keybindings, and variables"));
					}
					return 1;
				})));
		// register command var
		ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher
				.register(literal("var").then(argument("varName", StringArgumentType.word())
						.then(argument("source", StringArgumentType.word()).executes(context -> {
							String varName = StringArgumentType.getString(context, "varName");
							String source = StringArgumentType.getString(context, "source");
							return commandVarExecute(varName, source);
						})))));
		// register command runAlias
		ClientCommandRegistrationCallback.EVENT
				.register((dispatcher, registryAccess) -> dispatcher.register(literal("runAlias")
						.then(argument("definition", StringArgumentType.greedyString()).suggests((context, builder) -> {
							Alias.aliasesWithoutArgs.keySet().forEach(builder::suggest);
							Alias.aliasesWithArgs.keySet().forEach(builder::suggest);
							return builder.buildFuture();
						}).executes(context -> {
							String input = StringArgumentType.getString(context, "definition");
							// Use UserAlias to support chaining syntax
							// (space-separated, \ for args: "slot\2 wait\1 +forward")
							new UserAlias(input).run("");
							return 1;
						}))));

		// start MCP HTTP server for AI agent control
		McpHttpServer.start();
		ClientLifecycleEvents.CLIENT_STOPPING.register(client -> McpHttpServer.stop());
	}

    /** @return {@code "[client_tick:{client_ticks}]"} if joined, {@code "[client_tick:-1]"} otherwise. */
    public static String tickPrefix() {
        return joinTick < 0 ? "[client_tick:-1]" : "[client_tick:" + (currentTick - joinTick) + "]";
    }

    public void loadCFG() {
        LOGGER.info("{}autoload start (cfg: {})", tickPrefix(), cfgPath);
        try {
            if (cfgPath.toFile().createNewFile())
                return;
        } catch (IOException e) {
            LOGGER.error("{}Could not create file {}", tickPrefix(), cfgPath, e);
        }
        byte[] data = null;
        try (InputStream inputStream = Files.newInputStream(cfgPath)) {
            data = new byte[inputStream.available()];
            while (inputStream.available() > 0)
                inputStream.read(data);
        } catch (IOException e) {
            LOGGER.error("{}Failed to open cfg file", tickPrefix(), e);
        }
        if (data == null)
            return;
        String cfg = new String(data);
        cfg.lines().forEach(line -> {
            try {
                line = line.trim();
                if (line.startsWith("/"))
                    line = line.substring(1).trim();
                if (!(line.isBlank() || line.startsWith("#"))) {
                    if (line.startsWith("alias ")) {
                        String string = line.substring("alias ".length());
                        int i = string.indexOf(' ');
                        String substring = string.substring(0, i);
                        commandAliasExecute(substring, string.substring(i + 1), true);
                    } else if (line.startsWith("bind ")) {
                        String string = line.substring("bind ".length());
                        int i = string.indexOf(' ');
                        String substring = string.substring(0, i);
                        commandBindExecute(substring, string.substring(i + 1), true);
                    } else if (line.startsWith("bindByAliasName ")) {
                        String string = line.substring("bindByAliasName ".length());
                        int i = string.indexOf(' ');
                        String substring = string.substring(0, i);
                        commandBindByAliasNameExecute(substring, string.substring(i + 1), true);
                    } else if (line.startsWith("unbind ")) {
                        String string = line.substring("unbind ".length());
                        if (string.indexOf(' ') == -1)
                            commandUnbindExecute(string);
                    } else if (line.startsWith("var ")) {
                        String string = line.substring("var ".length());
                        int i = string.indexOf(' ');
                        if (i != -1) {
                            String varName = string.substring(0, i);
                            String source = string.substring(i + 1);
                            commandVarExecute(varName, source, true);
                        }
                    } else if (line.startsWith("runAlias ")) {
                        String aliasName = line.substring("runAlias ".length()).trim();
                        new UserAlias(aliasName).run("");
                    } else {
                        BindAliasClient.LOGGER.warn("{}Unknown command: {}", tickPrefix(), line);
                    }
                }
            } catch (Exception e) {
                BindAliasClient.LOGGER.warn("{}Failed to load CFG file", tickPrefix(), e);
            }
        });
    }

    private int commandVarExecute(String varName, String source) {
        return commandVarExecute(varName, source, false);
    }

    private int commandVarExecute(String varName, String source, boolean fromAutoload) {
        new VarAlias().run(varName + Alias.divider4AliasArgs + source, fromAutoload);

        // Check if variable was successfully created
        if (VarAlias.GENERAL_VARIABLES.containsKey(varName)) {
            if (!silentMode) {
                MinecraftClient.getInstance().player.sendMessage(
                        Text.literal("Variable '" + varName + "' set to " + VarAlias.GENERAL_VARIABLES.get(varName)), false);
            }
            return 1;
        } else {
            if (!silentMode) {
                MinecraftClient.getInstance().player
                        .sendMessage(Text.literal("\u00a7cFailed to set variable '" + varName + "'"), false);
            }
            return 0;
        }
    }

    private int commandUnbindExecute(String keyName) {
        InputUtil.Key key = parseKey(keyName);
        if (key == null)
            return 0;
        BINDING_PLUS.remove(key);
        return 1;
    }

    private int commandBindExecute(String keyName, String args) {
        return commandBindExecute(keyName, args, false);
    }

    private int commandBindExecute(String keyName, String args, boolean fromAutoload) {
        if (commandBindByAliasNameExecute(keyName, args, fromAutoload) == 1)
            return 1;
        final StringBuilder aliasName = new StringBuilder();
        final StringBuilder aliasName1 = new StringBuilder();
        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        final Random rng = new Random();
        for (int i = 0; i < 16; i++)
            aliasName.append(CHARACTERS.charAt(rng.nextInt(CHARACTERS.length())));
        for (int i = 0; i < 16; i++)
            aliasName1.append(CHARACTERS.charAt(rng.nextInt(CHARACTERS.length())));

        InputUtil.Key key = parseKey(keyName);
        if (key == null)
            return 2;

        Alias.aliasesWithoutArgs_fromBindCommand.put(String.valueOf(aliasName), new UserAlias(args, fromAutoload));
        String oppositeDefinition = Alias.getOppositeDefinition(args);
        if (!oppositeDefinition.isBlank())
            Alias.aliasesWithoutArgs_fromBindCommand.put(String.valueOf(aliasName1),
                    new UserAlias(oppositeDefinition, fromAutoload));
        BINDING_PLUS.put(key, new BindAliasKeyBinding(aliasName.toString(),
                oppositeDefinition.isBlank() ? "" : aliasName1.toString(), fromAutoload));
        return 3;
    }

    private int commandAliasExecute(String aliasName, String definition) {
        return commandAliasExecute(aliasName, definition, false);
    }

    private int commandAliasExecute(String aliasName, String definition, boolean fromAutoload) {
        if (Alias.aliasesWithArgs_notSuggested.containsKey(aliasName) || Alias.aliasesWithArgs.containsKey(aliasName)
                || Alias.aliasesWithoutArgs_notSuggested.containsKey(aliasName))
            return 2;
        AliasWithoutArgs<?> aliasWithoutArgs = Alias.aliasesWithoutArgs.get(aliasName);
        if (aliasWithoutArgs != null) {
            if (!(aliasWithoutArgs instanceof UserAlias))
                return 3;
            if (((UserAlias) aliasWithoutArgs).isPredefined())
                return 3;
        }
        Alias.aliasesWithoutArgs.put(aliasName, new UserAlias(definition, fromAutoload));
        return 1;
    }

    private int commandBindByAliasNameExecute(String keyName, String aliasName) {
        return commandBindByAliasNameExecute(keyName, aliasName, false);
    }

    private int commandBindByAliasNameExecute(String keyName, String aliasName, boolean fromAutoload) {
        boolean flag0 = true; // t -> +-aliasName binding pattern
        /*
         * t -> +aliasName or it doesn't contain +- and would be triggered when pressing down as default
         */
        boolean flag = true;
        boolean flag1 = true; // t -> aliasName stays the same, else subString(1)
        AliasWithoutArgs<?> alias = Alias.aliasesWithoutArgs.get(aliasName);
        if (alias == null) {
            flag1 = false;
            if (aliasName.startsWith("+") || aliasName.startsWith("-")) {
                alias = Alias.aliasesWithoutArgs.get(aliasName.substring(1));
                if (alias == null)
                    return 2;
                else if (aliasName.startsWith("-"))
                    flag = false;
            } else {
                flag0 = false;
                return 3;
            }
        } else {
            flag = !aliasName.startsWith("-");
            flag0 = flag || aliasName.startsWith("-");
        }

        InputUtil.Key key = parseKey(keyName);
        if (key == null)
            return 4;

        String aliasNameFinal = flag1 ? aliasName : aliasName.substring(1);
        String aliasNameFinalExtra = flag ? flag1 ? "-" + aliasNameFinal.substring(1) : "-" + aliasNameFinal
                : flag1 ? "+" + aliasNameFinal.substring(1) : "+" + aliasNameFinal;
        if (flag0) {
            AliasWithoutArgs<?> aliasWithoutArgs = Alias.aliasesWithoutArgs.get(aliasNameFinalExtra);
            if (aliasWithoutArgs == null)
                aliasNameFinalExtra = "";
        } else
            aliasNameFinalExtra = "";

        BINDING_PLUS.put(key, flag ? new BindAliasKeyBinding(aliasNameFinal, aliasNameFinalExtra, fromAutoload)
                : new BindAliasKeyBinding(aliasNameFinalExtra, aliasNameFinal, fromAutoload));

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
        if (n < a /* it's under an arg's args, don't need to provide alias keyName suggests */
        ) {
            // Provide arg value suggestions for aliases that support them
            String aliasName = soFar.substring(n + 1, a);
            String partialArg = soFar.substring(a + 1);
            if ("+lockKey".equals(aliasName) || "-lockKey".equals(aliasName)) {
                builder = builder.createOffset(builder.getStart() + a + 1);
                SuggestionsBuilder finalBuilder2 = builder;
                LockAlias.SUPPORTED_ACTIONS.forEach(action -> {
                    if (action.startsWith(partialArg))
                        finalBuilder2.suggest(action);
                });
                Alias.aliasesWithoutArgs.forEach((name, a2) -> {
                    if (name.startsWith(partialArg) && a2 instanceof UserAlias)
                        finalBuilder2.suggest(name);
                });
            }
            if ("reapply".equals(aliasName)) {
                builder = builder.createOffset(builder.getStart() + a + 1);
                SuggestionsBuilder finalBuilder2 = builder;
                ReapplyAlias.SUPPORTED_ACTIONS.forEach(action -> {
                    if (action.startsWith(partialArg))
                        finalBuilder2.suggest(action);
                });
            }
            // Suggest variable names for aliases that accept numeric args
            AliasWithArgs<?> varAlias = Alias.aliasesWithArgs.get(aliasName);
            if (varAlias != null) {
                boolean intOnly = varAlias instanceof BuiltinAliasWithIntegerArgs;
                boolean doubleOk = varAlias instanceof BuiltinAliasWithDoubleArgs;
                if (intOnly || doubleOk) {
                    builder = builder.createOffset(builder.getStart() + a + 1);
                    SuggestionsBuilder finalBuilder2 = builder;
                    VarAlias.GENERAL_VARIABLES.forEach((varName, value) -> {
                        if (!varName.startsWith(partialArg))
                            return;
                        // Integer aliases only accept Integer-typed variables
                        if (intOnly && !(value instanceof Integer))
                            return;
                        finalBuilder2.suggest(varName, Text.literal("var = " + value));
                    });
                }
            }
            return builder.buildFuture();
        }
        String currentToken = soFar.substring(n + 1);

        builder = builder.createOffset(builder.getStart() + n + 1);

        SuggestionsBuilder finalBuilder = builder;
        Alias.aliasesWithoutArgs.keySet().forEach(alias -> {
            if (alias.startsWith(currentToken))
                finalBuilder.suggest(alias, Text.literal("alias without args"));
        });
        Alias.aliasesWithArgs.keySet().forEach(alias -> {
            if (alias.startsWith(currentToken))
                finalBuilder.suggest(alias, Text.literal("alias with args"));
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
                    BindAliasClient.LOGGER.warn("{}Invalid key definition: {}", tickPrefix(), name);
                }
            }
        }
        return key;
    }
}
