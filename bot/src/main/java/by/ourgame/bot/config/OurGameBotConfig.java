package by.ourgame.bot.config;

import by.ourgame.bot.api.config.BotConfig;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class OurGameBotConfig extends BotConfig {

    public OurGameBotConfig(
            @Value("${bot.token}") String token,
            @Value("${bot.timeOut}") Integer timeOut) {
        super(token, timeOut);
    }
}
