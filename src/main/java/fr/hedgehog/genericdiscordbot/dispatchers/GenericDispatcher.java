package fr.hedgehog.genericdiscordbot.dispatchers;

import discord4j.core.object.entity.Message;
import reactor.core.publisher.Mono;

public interface GenericDispatcher {
    public Mono<Void> dispatch(Message message);

    private Mono<Void> execute(Class commandClass, Message message){return null;}
}
