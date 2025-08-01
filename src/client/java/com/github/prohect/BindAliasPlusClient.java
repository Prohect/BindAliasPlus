package com.github.prohect;

import com.github.prohect.alias.Aliases;
import com.github.prohect.alias.UserAlias;
import com.github.prohect.alias.builtinAlias.HelloWorldAlias;
import com.github.prohect.alias.builtinAlias.LogAlias;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class BindAliasPlusClient implements ClientModInitializer {

    public static final ArrayDeque<KeyPressed> keyQueue = new ArrayDeque<>();
    public static final Map<InputUtil.Key, KeyBindingPlus> BINDING_PLUS = new HashMap<>();

    public static final Logger LOGGER = LoggerFactory.getLogger("bind-alias-plus");

    @Override
    public void onInitializeClient() {
        //load builtin alias

        //load builtin aliasesWithArgs
        Aliases.aliasesWithArgs.put("log", new LogAlias());
        //load builtin aliasesWithoutArgs
        Aliases.aliasesWithoutArgs.put("helloWorld", new HelloWorldAlias());


        //load user alias defined by user, would be ... like read a json file locally and cast them into aliases
        Aliases.aliasesWithoutArgs.put("kpw_pressed", new UserAlias("log\\\\ on key w pressed"));
        Aliases.aliasesWithoutArgs.put("kpw_released", new UserAlias("log\\\\ on key w released"));
        Aliases.aliasesWithoutArgs.put("kps_released", new UserAlias("log\\\\ on key s released"));

        //register keybinds
        BINDING_PLUS.put(InputUtil.Type.KEYSYM.createFromCode(GLFW.GLFW_KEY_W), new KeyBindingPlus(Aliases.aliasesWithoutArgs.get("kpw_pressed"), Aliases.aliasesWithoutArgs.get("kpw_released")));
        BINDING_PLUS.put(InputUtil.Type.KEYSYM.createFromCode(GLFW.GLFW_KEY_S), new KeyBindingPlus(Aliases.aliasesWithoutArgs.get("helloWorld"), Aliases.aliasesWithoutArgs.get("kps_released")));
    }
}