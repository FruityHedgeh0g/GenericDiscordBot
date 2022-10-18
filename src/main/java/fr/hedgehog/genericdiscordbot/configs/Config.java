package fr.hedgehog.genericdiscordbot.configs;

import fr.hedgehog.genericdiscordbot.commands.gameModule.configs.GameCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.InvocationTargetException;

@Configuration
public class Config {

    @Bean
    public CommandCache getCommands() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return new CommandCache();
    }

    @Bean
    public GameCache getGames() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return new GameCache();
    }
}
