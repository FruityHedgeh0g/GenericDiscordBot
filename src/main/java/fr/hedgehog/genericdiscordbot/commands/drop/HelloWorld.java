package fr.hedgehog.genericdiscordbot.commands.drop;

import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.Channel;
import discord4j.core.object.entity.channel.MessageChannel;
import fr.hedgehog.genericdiscordbot.commands.GenericCommand;
import reactor.core.publisher.Mono;

import java.util.Objects;

public class HelloWorld implements GenericCommand {

    @Override
    public String getCommandCaller() {
        return "hi";
    }

    @Override
    public void execute(Message message) {
        Mono.just(message)
                .flatMap(Message::getChannel)
                .flatMap(channel -> channel.createMessage("hi"))
                .subscribe();
    }

    private Mono<Void> printToChannel(MessageChannel channel){
        System.out.println(channel.toString());
        channel.createMessage("Hi");
        return null;
    }
}
