package fr.hedgehog.genericdiscordbot.configs;

import fr.hedgehog.genericdiscordbot.commands.helpers.Helper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.InvocationTargetException;

@Configuration
public class Config {

    @Bean
    public CommandCache getCommands() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        return new CommandCache();
    }
}
