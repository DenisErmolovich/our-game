package by.ourgame.bot.config;

import by.ourgame.bot.api.client.ReactiveBotClient;
import by.ourgame.bot.api.config.BotConfig;
import by.ourgame.bot.api.method.EditMessageTextMethod;
import by.ourgame.bot.api.method.GetUpdatesMethod;
import by.ourgame.bot.api.method.SendMessageMethod;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BotContext {

    @Bean
    public ReactiveBotClient reactiveBotClient(BotConfig botConfig) {
        return new ReactiveBotClient(botConfig);
    }

    @Bean
    public GetUpdatesMethod getUpdatesMethod(ReactiveBotClient client) {
        return new GetUpdatesMethod(client);
    }

    @Bean
    public SendMessageMethod sendMessageMethod(ReactiveBotClient client) {
        return new SendMessageMethod(client);
    }

    @Bean
    public EditMessageTextMethod editMessageTextMethod(ReactiveBotClient client) {
        return new EditMessageTextMethod(client);
    }
}
