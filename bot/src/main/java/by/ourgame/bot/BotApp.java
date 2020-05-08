package by.ourgame.bot;

import by.ourgame.bot.config.SpringContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BotApp {

    public static void main(String[] args) {
        SpringApplication.run(BotApp.class, args);
        SpringContext.getBean(OurGameButtonsBot.class).startLongPooling();
    }
}
