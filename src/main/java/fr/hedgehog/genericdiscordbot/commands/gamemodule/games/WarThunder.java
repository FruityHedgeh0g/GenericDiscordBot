package fr.hedgehog.genericdiscordbot.commands.gamemodule.games;

import discord4j.core.object.component.ActionRow;
import discord4j.core.object.component.Button;
import discord4j.core.object.entity.Message;
import fr.hedgehog.genericdiscordbot.commands.GenericCommand;
import fr.hedgehog.genericdiscordbot.commands.gamemodule.GenericGameCommand;
import reactor.core.publisher.Mono;

public class WarThunder implements GenericGameCommand, GenericCommand {

    @Override
    public String getCommandCaller() { return "WT";}

    @Override
    public Mono<Void> execute(Message message) {
        return Mono.just(message)
                .flatMap(Message::getChannel)
                .map(channel -> channel.createMessage("Push the button if you want to play Warthunder")
                        .withComponents(ActionRow.of(Button.success(":D","Push me plz"))))
                .then();
    }

    @Override
    public Mono<Void> searchPlayers(Message message){
        return Mono.just(message)
                .flatMap(Message::getChannel)
                .map(channel -> channel.createMessage("Push the button if you want to play Warthunder")
                        .withComponents(ActionRow.of(Button.success(":D","Push me plz"))))
                .then();
    }

    @Override
    public void createRoom(){
    }


}
