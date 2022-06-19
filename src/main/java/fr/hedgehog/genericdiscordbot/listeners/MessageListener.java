package fr.hedgehog.genericdiscordbot.listeners;

import discord4j.core.object.entity.Message;
import fr.hedgehog.genericdiscordbot.configs.CommandCache;
import fr.hedgehog.genericdiscordbot.dispatchers.Dispatcher;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import reactor.core.publisher.Mono;

import java.util.function.Predicate;

@Getter
@Setter
//@RequiredArgsConstructor
public class MessageListener{
    @Autowired
    Dispatcher dispatcher;

    @Value("${bot.command.prefix}")
    private String prefix;
    @Value("${bot.command.caller}")
    private String caller;

    public Mono<Void> processCommand(Message eventMessage) {
        return Mono.just(eventMessage)
                .filter(isABot)
                .filter(isACall)
                .filter(hasACommand)
                .filter(isInCommands)
                .flatMap(dispatcher::dispatch)
                .then();
    }

    Predicate<Message> isABot = message -> message.getAuthor().map(user -> !user.isBot()).orElse(false);
    Predicate<Message> isACall = message -> message.getContent().trim().startsWith(prefix + caller);
    Predicate<Message> hasACommand = message -> !message.getContent().trim().split(prefix + caller + " ")[1].trim().isEmpty();
    Predicate<Message> isInCommands = message -> dispatcher.getCommands().getCommands().containsKey(message.getContent().trim()
            .split(prefix + caller + " ")[1].trim()
            .split(" ")[0].trim());
}
