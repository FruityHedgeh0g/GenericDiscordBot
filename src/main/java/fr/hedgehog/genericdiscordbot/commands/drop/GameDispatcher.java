package fr.hedgehog.genericdiscordbot.commands.drop;

import discord4j.core.object.entity.Message;
import fr.hedgehog.genericdiscordbot.commands.GenericCommand;
import fr.hedgehog.genericdiscordbot.commands.gameModule.configs.GameCache;
import fr.hedgehog.genericdiscordbot.dispatchers.GenericDispatcher;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;
import reactor.core.publisher.Mono;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RequiredArgsConstructor
@Getter
public class GameDispatcher implements GenericCommand, GenericDispatcher {

    private final GameCache games;
    //conflict de nom de méthodes entre execute GenericCommand et GenericDispatcher
    @Override
    public String getCommandCaller() {
        return "game";
    }

    @Override
    public Mono<Void> execute(Message message) {
        return Mono.just(message)
                .map(anyMessage -> dispatch(anyMessage))
                .then();

    }

    @Override
    public Mono<Void> dispatch(Message message) {
        return Mono.just(message)
                .map(anyMessage -> anyMessage.getContent().trim().split("game "))
                .map(game -> games.getGames().get(game[1]))
                .mapNotNull(clazz -> {
                    try {
                        return execute(clazz, message);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .then();
    }

    //Dispatcher genericDispatcher
    private Mono<Void> execute(Class gameClass, Message message) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> anyGameClass = gameClass;
        Object anyCommand = anyGameClass.getDeclaredConstructor().newInstance();
        for(Method m : anyCommand.getClass().getMethods()) {
            if ("execute".equals(m.getName())) {
                m.invoke(anyCommand, message);
                return null;
            }
        }

        return null;
    }


}
