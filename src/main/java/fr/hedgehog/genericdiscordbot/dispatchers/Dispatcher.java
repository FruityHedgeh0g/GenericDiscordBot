package fr.hedgehog.genericdiscordbot.dispatchers;

import discord4j.core.object.entity.Message;
import fr.hedgehog.genericdiscordbot.commands.GenericCommand;
import fr.hedgehog.genericdiscordbot.configs.CommandCache;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.lang.reflect.Method;

@Service
@RequiredArgsConstructor
@Getter
public class Dispatcher implements GenericDispatcher {



    private final CommandCache commandCache;

    public Mono<Void> dispatch(Message message) {
        return Mono.just(message)
                .map(anyMessage -> anyMessage.getContent().trim().split(" "))
                .map(command -> commandCache.getCommands().get(command[1]))
                .mapNotNull(clazz -> {
                    try {
                        return execute(clazz, message);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .then();
    }

    private Mono<Void> execute(Class commandClass, Message message) throws Exception {

        Class<?> anyClass = commandClass;
        Object anyCommand = anyClass.getDeclaredConstructor().newInstance();
        for(Method m : anyCommand.getClass().getMethods()) {
            if ("execute".equals(m.getName())) {
                m.invoke(anyCommand, message);
                return null;
            }
        }

        return null;
    }
}
