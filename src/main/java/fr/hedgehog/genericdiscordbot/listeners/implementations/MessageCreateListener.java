package fr.hedgehog.genericdiscordbot.listeners.implementations;

import discord4j.core.event.domain.message.MessageCreateEvent;
import fr.hedgehog.genericdiscordbot.configs.CommandConfig;
import fr.hedgehog.genericdiscordbot.dispatchers.implementations.Dispatcher;
import fr.hedgehog.genericdiscordbot.listeners.EventListener;
import fr.hedgehog.genericdiscordbot.listeners.MessageListener;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MessageCreateListener extends MessageListener implements EventListener<MessageCreateEvent> {

    public MessageCreateListener(Dispatcher dispatcher, CommandConfig commands) {
        super(dispatcher, commands);
    }

    @Override
    public Class<MessageCreateEvent> getEventType() {
        return MessageCreateEvent.class;
    }

    @Override
    public Mono<Void> execute(MessageCreateEvent event) {
        return processCommand(event.getMessage());
    }
}
