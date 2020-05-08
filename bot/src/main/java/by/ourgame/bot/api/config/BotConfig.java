package by.ourgame.bot.api.config;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public abstract class BotConfig {
    private String token;
    private Integer timeOut;
}
