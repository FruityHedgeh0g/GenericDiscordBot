package fr.hedgehog.genericdiscordbot.commands;

import discord4j.core.object.entity.Message;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public interface GenericCommand {

    public String getCommandCaller();

    public Mono<Void> execute(Message message);

}
