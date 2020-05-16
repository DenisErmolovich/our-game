package by.ourgame.bot.api;

import by.ourgame.bot.api.config.BotConfig;
import by.ourgame.bot.api.dto.Message;
import by.ourgame.bot.api.dto.MessageEntity;
import by.ourgame.bot.api.dto.Update;
import by.ourgame.bot.api.dto.request.GetUpdatesRequest;
import by.ourgame.bot.api.method.GetUpdatesMethod;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

@Slf4j
public abstract class TelegramReactiveBot {
    private BotConfig config;
    private GetUpdatesMethod getUpdatesMethod;

    public TelegramReactiveBot(BotConfig config, GetUpdatesMethod getUpdatesMethod) {
        this.config = config;
        this.getUpdatesMethod = getUpdatesMethod;
    }

    public abstract void processStartCommand(Update update);

    public abstract void processHelpCommand(Update update);

    public abstract void processSettingsCommand(Update update);

    public abstract void processNotSupportedCommand(Update update);

    public abstract Map<String, Consumer<Update>> getCustomCommandProcessorMap();

    public abstract void processCallbackQuery(Update update);

    public abstract void processUpdateIfNotCommandAndNotCallbackQuery(Update update);

    public void startLongPooling() {
        var getUpdatesRequest = GetUpdatesRequest.builder()
                .timeout(config.getTimeOut())
                .build();
        getUpdatesMethod
                .perform(getUpdatesRequest)
                .doOnNext(updates -> log.info("New updates: {}", updates))
                .repeat()
                .switchMap(Flux::fromIterable)
                .subscribe(update -> processUpdate(update, getUpdatesRequest));
    }

    private void processUpdate(Update update, GetUpdatesRequest getUpdatesRequest) {
        getCommandProcessor(update)
                .or(() -> getIfNotCommandProcessor(update))
                .ifPresent(processor -> processor.accept(update));
        var offset = update.getUpdateId() + 1;
        getUpdatesRequest.setOffset(offset);
    }

    private Optional<Consumer<Update>> getCommandProcessor(Update update) {
        return retrieveCommand(update)
                .flatMap(command -> getCommandProcessor(command, update));
    }

    private Optional<Consumer<Update>> getCommandProcessor(String command, Update update) {
        return Optional.ofNullable(getCommandProcessorMap().get(command))
                .or(() -> Optional.of(this::processNotSupportedCommand));
    }

    private Optional<Consumer<Update>> getIfNotCommandProcessor(Update update) {
        return Optional.ofNullable(update.getCallbackQuery())
                .map(callbackQuery -> (Consumer<Update>) this::processCallbackQuery)
                .or(() -> Optional.of(this::processUpdateIfNotCommandAndNotCallbackQuery));
    }

    private Map<String, Consumer<Update>> getCommandProcessorMap() {
        var processorMap = new HashMap<String, Consumer<Update>>();
        processorMap.put("/start", this::processStartCommand);
        processorMap.put("/help", this::processHelpCommand);
        processorMap.put("/settings", this::processSettingsCommand);
        processorMap.putAll(getCustomCommandProcessorMap());
        return processorMap;
    }

    private Optional<String> retrieveCommand(Update update) {
        return Optional.ofNullable(update.getMessage())
                .map(Message::getEntities)
                .flatMap(messageEntities -> messageEntities
                        .stream()
                        .filter(messageEntity -> messageEntity.getType().equals("bot_command"))
                        .findFirst()
                        .map(messageEntity -> retrieveCommand(update, messageEntity)));
    }

    private String retrieveCommand(Update update, MessageEntity messageEntity) {
        return update.getMessage()
                .getText()
                .substring(messageEntity.getOffset(), messageEntity.getLength())
                .toLowerCase()
                .split("@")[0];
    }
}
