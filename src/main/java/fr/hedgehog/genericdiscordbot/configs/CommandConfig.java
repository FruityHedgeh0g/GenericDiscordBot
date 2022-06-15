package fr.hedgehog.genericdiscordbot.configs;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.Map;

@Component
@ApplicationScope
@ConfigurationProperties(prefix = "dispatcher")
@Getter
@Setter
public class CommandConfig {

    private Map<String, String> commands;

}
