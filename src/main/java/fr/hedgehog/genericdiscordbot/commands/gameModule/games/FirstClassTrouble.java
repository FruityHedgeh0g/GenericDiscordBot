package fr.hedgehog.genericdiscordbot.commands.gameModule.games;

import discord4j.core.object.component.ActionRow;
import discord4j.core.object.component.Button;
import discord4j.core.object.entity.Message;
import fr.hedgehog.genericdiscordbot.commands.GenericCommand;
import fr.hedgehog.genericdiscordbot.commands.gameModule.GenericGameCommand;
import reactor.core.publisher.Mono;

public class FirstClassTrouble implements GenericGameCommand, GenericCommand {

    @Override
    public String getCommandCaller(){ return "FCT";}

    @Override
    public Mono<Void> execute(Message message) {
        return Mono.just(message)
                .flatMap(Message::getChannel)
                .map(channel -> channel.createMessage("Push the button if you want to play FCT")
                        .withComponents(ActionRow.of(Button.success(":D","Push me plz"))))
                .then();
    }

    @Override
    public Mono<Void> searchPlayers(Message message){
        return Mono.just(message)
                .flatMap(Message::getChannel)
                .map(channel -> channel.createMessage("Push the button if you want to play FCT")
                        .withComponents(ActionRow.of(Button.success(":D","Push me plz"))))
                .then();

    }

    @Override
    public void createRoom(){
    }

}
