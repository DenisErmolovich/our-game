package by.ourgame.bot.api;

import by.ourgame.bot.api.config.BotConfig;
import by.ourgame.bot.api.dto.Update;
import by.ourgame.bot.api.dto.request.GetUpdatesRequest;
import by.ourgame.bot.api.method.GetUpdatesMethod;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

public abstract class TelegramReactiveBot {
    private BotConfig config;
    private GetUpdatesMethod getUpdatesMethod;

    public TelegramReactiveBot(BotConfig config, GetUpdatesMethod getUpdatesMethod) {
        this.config = config;
        this.getUpdatesMethod = getUpdatesMethod;
    }

    public void startLongPooling() {
        var getUpdatesRequest = GetUpdatesRequest.builder()
                .timeout(config.getTimeOut())
                .build();
        getUpdatesMethod
                .perform(getUpdatesRequest)
                .repeat()
                .switchMap(Flux::fromIterable)
                .subscribe(update -> processUpdate(update, getUpdatesRequest));
    }

    public abstract Consumer<Update> getStartProcessor();

    public abstract Consumer<Update> getHelpProcessor();

    public abstract Consumer<Update> getSettingsProcessor();

    public abstract Consumer<Update> getNotSupportedCommandProcessor();

    public abstract Map<String, Consumer<Update>> getCustomCommandProcessorMap();

    public abstract void processUpdateIfNotCommand(Update update);

    private void processUpdate(Update update, GetUpdatesRequest getUpdatesRequest) {
        var offset = update.getUpdateId() + 1;
        if (!processCommand(update)) {
            processUpdateIfNotCommand(update);
        }
        getUpdatesRequest.setOffset(offset);
    }

    private boolean processCommand(Update update) {
        return retrieveCommand(update)
                .map(command -> processCommand(command, update))
                .orElse(false);
    }

    private boolean processCommand(String command, Update update) {
        Optional.ofNullable(getCommandProcessorMap().get(command))
                .ifPresentOrElse(
                    processor -> processor.accept(update),
                    () -> getNotSupportedCommandProcessor().accept(update)
                );
        return true;
    }

    private Map<String, Consumer<Update>> getCommandProcessorMap() {
        var processorMap = new HashMap<String, Consumer<Update>>();
        processorMap.put("/start", getStartProcessor());
        processorMap.put("/help", getHelpProcessor());
        processorMap.put("/settings", getSettingsProcessor());
        processorMap.putAll(getCustomCommandProcessorMap());
        return processorMap;
    }

    private Optional<String> retrieveCommand(Update update) {
        var messageEntities = update.getMessage().getEntities();
        if (messageEntities == null) {
            return Optional.empty();
        }
        return messageEntities
                .stream()
                .filter(messageEntity -> messageEntity.getType().equals("bot_command"))
                .findFirst()
                .map(messageEntity -> update
                        .getMessage()
                        .getText()
                        .substring(messageEntity.getOffset(), messageEntity.getLength()));
    }
}
