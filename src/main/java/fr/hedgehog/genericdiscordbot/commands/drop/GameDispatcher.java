package fr.hedgehog.genericdiscordbot.commands.drop;

import discord4j.core.object.entity.Message;
import fr.hedgehog.genericdiscordbot.commands.GenericCommand;
import fr.hedgehog.genericdiscordbot.configs.GameCache;
import fr.hedgehog.genericdiscordbot.dispatchers.GenericDispatcher;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GameDispatcher implements GenericCommand, GenericDispatcher {

    private GameCache games;

    @Override
    public String getCommandCaller() {
        return "game";
    }

    @Override
    public Mono<Void> execute(Message message) {
        return Mono.just(message)
                .map(this::dispatch).doOnSuccess(System.out::println).doOnError(System.out::println)
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

    @PostConstruct
    private void setGameDispatcher() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        this.setGames(new GameCache());
    }


}
