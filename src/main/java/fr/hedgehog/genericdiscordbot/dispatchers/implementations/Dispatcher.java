package fr.hedgehog.genericdiscordbot.dispatchers.implementations;

import discord4j.core.object.entity.Message;
import fr.hedgehog.genericdiscordbot.configs.CommandConfig;
import fr.hedgehog.genericdiscordbot.configs.Config;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class Dispatcher {

    private final CommandConfig commands;

    public Mono<Void> dispatch(Message message){

        Mono.just(message)
                .map(anyMessage -> anyMessage.getContent().split(" "))
                .map(command -> commands.getCommands().get(command))
                .then();

       return null;
    }

    private void execute(String command){
        try {
            Class<?> anyClass = Class.forName(command);
            Object anyCommand = anyClass.getDeclaredConstructor(String.class).newInstance("");
        }catch(Exception e){

        }
    }

}
