package by.ourgame.bot.api.client;

import by.ourgame.bot.api.config.BotConfig;
import org.springframework.web.reactive.function.client.WebClient;

public class ReactiveBotClient {
    private final String urlTemplate = "https://api.telegram.org/bot%s";
    private final BotConfig botConfig;
    private final WebClient webClient;

    public ReactiveBotClient(BotConfig botConfig) {
        this.botConfig = botConfig;
        this.webClient = WebClient.create(String.format(urlTemplate, botConfig.getToken()));
    }

    public WebClient getBaseWebClient() {
        return webClient;
    }
}
