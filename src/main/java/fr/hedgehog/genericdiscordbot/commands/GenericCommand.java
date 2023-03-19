package fr.hedgehog.genericdiscordbot.commands;

import discord4j.core.object.entity.Message;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface GenericCommand {

    public String getCommandCaller();

    public <T> Mono<T> execute(Message message);

}
