package fr.hedgehog.genericdiscordbot.listeners.implementations;

import discord4j.core.event.domain.message.MessageUpdateEvent;
import fr.hedgehog.genericdiscordbot.configs.CommandConfig;
import fr.hedgehog.genericdiscordbot.dispatchers.implementations.Dispatcher;
import fr.hedgehog.genericdiscordbot.listeners.EventListener;
import fr.hedgehog.genericdiscordbot.listeners.MessageListener;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MessageUpdateListener extends MessageListener implements EventListener<MessageUpdateEvent> {

    public MessageUpdateListener(Dispatcher dispatcher, CommandConfig commands) {
        super(dispatcher, commands);
    }

    @Override
    public Class<MessageUpdateEvent> getEventType() {
        return MessageUpdateEvent.class;
    }

    @Override
    public Mono<Void> execute(MessageUpdateEvent event) {
        return Mono.just(event)
                .filter(MessageUpdateEvent::isContentChanged)
                .flatMap(MessageUpdateEvent::getMessage)
                .flatMap(super::processCommand);
    }
}
