package fr.hedgehog.genericdiscordbot.configs;

import fr.hedgehog.genericdiscordbot.dispatchers.implementations.Dispatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.ApplicationScope;

@Configuration
@EnableAutoConfiguration
@RequiredArgsConstructor
public class Config {

//    @Bean
//    @ApplicationScope
//    public CommandConfig getCommands(){
//        return new CommandConfig();
//    }
//
//    @Bean
//    public Dispatcher getDispatcher(){
//        return new Dispatcher(this.getCommands());
//    }
}
