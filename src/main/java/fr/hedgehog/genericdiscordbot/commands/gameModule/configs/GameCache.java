package fr.hedgehog.genericdiscordbot.commands.gameModule.configs;

import fr.hedgehog.genericdiscordbot.commands.helpers.Helper;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class GameCache {
    public GameCache() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        this.setGames(new HashMap<>());

        Class[] classes = Helper.getClasses("fr.hedgehog.genericdiscordbot.commands.gameModule.games");
        for (Class clazz : classes) {
            Object o = Class.forName(clazz.getName()).getDeclaredConstructor().newInstance();
            for(Method m : o.getClass().getMethods()) {
                if ("getCommandCaller".equals(m.getName())) {
                    String data = (String)m.invoke(o, null);
                    this.getGames().put(data, clazz);
                }
            }
        }
    }

    private Map<String, Class> games;
}
