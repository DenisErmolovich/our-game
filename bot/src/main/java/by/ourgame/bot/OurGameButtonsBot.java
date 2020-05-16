package by.ourgame.bot;

import by.ourgame.bot.api.TelegramReactiveBot;
import by.ourgame.bot.api.config.BotConfig;
import by.ourgame.bot.api.dto.Update;
import by.ourgame.bot.api.method.GetUpdatesMethod;
import by.ourgame.bot.service.CallbackQueryService;
import by.ourgame.bot.service.CommandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

@Component
@Slf4j
public class OurGameButtonsBot extends TelegramReactiveBot {
    private CommandService commandService;
    private CallbackQueryService callbackQueryService;

    public OurGameButtonsBot(BotConfig config,
                             GetUpdatesMethod getUpdatesMethod,
                             CommandService commandService,
                             CallbackQueryService callbackQueryService) {
        super(config, getUpdatesMethod);
        this.commandService = commandService;
        this.callbackQueryService = callbackQueryService;
    }

    @Override
    public void processStartCommand(Update update) {
        commandService.processStartCommand(update);
    }

    @Override
    public void processHelpCommand(Update update) {
        commandService.processHelpCommand(update);
    }

    @Override
    public void processSettingsCommand(Update update) {
        commandService.processSettingsCommand(update);
    }

    @Override
    public void processNotSupportedCommand(Update update) {
        commandService.processNotSupportedCommand(update);

    }

    @Override
    public Map<String, Consumer<Update>> getCustomCommandProcessorMap() {
        var commands = new HashMap<String, Consumer<Update>>();
        commands.put("/startgame", commandService::processStartGame);
        commands.put("/finishgame", commandService::processFinishGame);
        return commands;
    }

    @Override
    public void processCallbackQuery(Update update) {
        var query = update.getCallbackQuery().getData();
        Optional.ofNullable(getInlineQueryProcessorMap().get(query))
                .ifPresent(processor -> processor.accept(update));
    }

    @Override
    public void processUpdateIfNotCommandAndNotCallbackQuery(Update update) {
    }

    public Map<String, Consumer<Update>> getInlineQueryProcessorMap() {
        var queryProcessors = new HashMap<String, Consumer<Update>>();
        queryProcessors.put("answer", callbackQueryService::processAnswerQuery);
        queryProcessors.put("allow", callbackQueryService::processAllowQuery);
        queryProcessors.put("yes", callbackQueryService::processYesQuery);
        queryProcessors.put("no", callbackQueryService::processNoQuery);
        queryProcessors.put("wait", callbackQueryService::processWaitQuery);
        queryProcessors.put("reset", callbackQueryService::processResetQuery);
        return queryProcessors;
    }
}
