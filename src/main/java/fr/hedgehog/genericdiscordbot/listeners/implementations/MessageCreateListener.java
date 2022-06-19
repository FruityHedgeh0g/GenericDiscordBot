package fr.hedgehog.genericdiscordbot.listeners.implementations;

import discord4j.core.event.domain.message.MessageCreateEvent;
import fr.hedgehog.genericdiscordbot.configs.CommandCache;
import fr.hedgehog.genericdiscordbot.dispatchers.Dispatcher;
import fr.hedgehog.genericdiscordbot.listeners.EventListener;
import fr.hedgehog.genericdiscordbot.listeners.MessageListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MessageCreateListener extends MessageListener implements EventListener<MessageCreateEvent> {

    @Override
    public Class<MessageCreateEvent> getEventType() {
        return MessageCreateEvent.class;
    }

    @Override
    public Mono<Void> execute(MessageCreateEvent event) {
        return processCommand(event.getMessage());
    }
}
