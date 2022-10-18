package fr.hedgehog.genericdiscordbot.commands.gameModule;

import discord4j.core.object.entity.Message;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public interface GenericGameCommand {
    public Mono<Void> searchPlayers(Message message);

    public void createRoom();

}
