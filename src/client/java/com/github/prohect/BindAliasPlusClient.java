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
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

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
            dispatcher.register(
                    literal("alias")
                            .then(argument("name", StringArgumentType.word())
                                    .then(argument("definition", StringArgumentType.greedyString())
                                            .executes(context -> {
                                                String name = StringArgumentType.getString(context, "name");
                                                String def = StringArgumentType.getString(context, "definition");

                                                Aliases.aliasesWithoutArgs.put(name, new UserAlias(def));

                                                context.getSource().sendFeedback(
                                                        Text.literal("Alias " + name + " = " + def)
                                                );
                                                return 1;
                                            })
                                    )
                            )
            );
        });
        // 注册 /bind 命令
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(
                    literal("bind")
                            .then(argument("key", StringArgumentType.word())
                                    .then(argument("aliasName", StringArgumentType.word())
                                            .executes(context -> {
                                                String keyName = StringArgumentType.getString(context, "key");
                                                String aliasName = StringArgumentType.getString(context, "aliasName");

                                                AliasWithoutArgs alias = Aliases.aliasesWithoutArgs.get(aliasName);
                                                boolean flag = true;//t -> +aliasName binding pattern
                                                boolean flag1 = true;//t -> aliasName stays the same, else subString(1)
                                                if (alias == null) {
                                                    flag1 = false;
                                                    if ((aliasName.startsWith("+")) || aliasName.startsWith("-")) {
                                                        alias = Aliases.aliasesWithoutArgs.get(aliasName.substring(1));
                                                        if (alias == null)
                                                            context.getSource().sendFeedback(Text.literal("§cAlias " + aliasName + " does not exist!"));
                                                        else if (aliasName.startsWith("-")) flag = false;
                                                    } else return 0;
                                                } else {
                                                    flag = aliasName.startsWith("+");
                                                }

                                                // 转换按键名为 InputUtil.Key
                                                InputUtil.Key key = parseKey(keyName);
                                                if (key == null) {
                                                    context.getSource().sendFeedback(Text.literal("§cUnknown key: " + keyName));
                                                    return 0;
                                                }

                                                // 注册绑定
                                                KeyBindingPlus keyBindingPlus = BINDING_PLUS.get(key);
                                                String aliasNameFinal = flag1 ? aliasName : aliasName.substring(1);
                                                if (keyBindingPlus == null) {
                                                    if (flag)
                                                        BINDING_PLUS.put(key, new KeyBindingPlus(aliasNameFinal, ""));
                                                    else
                                                        BINDING_PLUS.put(key, new KeyBindingPlus("", aliasNameFinal));
                                                } else
                                                    BINDING_PLUS.put(key, flag ? new KeyBindingPlus(aliasNameFinal, keyBindingPlus.aliasNameOnKeyReleased()) : new KeyBindingPlus(keyBindingPlus.aliasNameOnKeyPressed(), aliasNameFinal));

                                                context.getSource().sendFeedback(Text.literal("§aBound key " + keyName + " to alias " + aliasName));
                                                return 1;
                                            })
                                    )
                            )
            );
        });

        //load builtin alias

        //load builtin aliasesWithArgs
        Aliases.aliasesWithArgs.put("log", new LogAlias());
        Aliases.aliasesWithArgs.put("builtinAttack", new AttackAlias());
        Aliases.aliasesWithArgs.put("slot", new SlotAlias());
        Aliases.aliasesWithArgs.put("builtinUse", new UseAlias());
        //load builtin aliasesWithoutArgs
        Aliases.aliasesWithoutArgs.put("helloWorld", new HelloWorldAlias());
        Aliases.aliasesWithoutArgs.put("+attack", new UserAlias("builtinAttack\\\\1"));
        Aliases.aliasesWithoutArgs.put("-attack", new UserAlias("builtinAttack\\\\0"));
        Aliases.aliasesWithoutArgs.put("+use", new UserAlias("builtinUse\\\\1"));
        Aliases.aliasesWithoutArgs.put("-use", new UserAlias("builtinUse\\\\0"));


        //load user alias defined by user, would be ... like read a json file locally and cast them into aliases
        Aliases.aliasesWithoutArgs.put("+kpw", new UserAlias("log\\\\ on key w pressed\\|helloWorld"));
        Aliases.aliasesWithoutArgs.put("-kpw", new UserAlias("log\\\\ on key w released"));
        Aliases.aliasesWithoutArgs.put("-kps", new UserAlias("log\\\\ on key s released"));

        //register keybinds
        BINDING_PLUS.put(InputUtil.Type.KEYSYM.createFromCode(GLFW.GLFW_KEY_W), new KeyBindingPlus("+kpw", "-kpw"));
        BINDING_PLUS.put(InputUtil.Type.KEYSYM.createFromCode(GLFW.GLFW_KEY_S), new KeyBindingPlus("helloWorld", "-kps"));
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