package fr.hedgehog.genericdiscordbot.commands.drop;

import discord4j.core.object.entity.Message;
import fr.hedgehog.genericdiscordbot.commands.GenericCommand;
import reactor.core.publisher.Mono;

public class HelloWorld implements GenericCommand {

    @Override
    public String getCommandCaller() {
        return "hi";
    }

    @Override
    public Mono<Void> execute(Message message) {
        return Mono.just(message)
                .flatMap(Message::getChannel)
                .flatMap(channel -> channel.createMessage("Hi!"))
                .then();
    }
}
