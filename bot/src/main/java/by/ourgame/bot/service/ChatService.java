package by.ourgame.bot.service;

import by.ourgame.bot.api.dto.request.ChatPermissions;
import by.ourgame.bot.api.dto.request.SetChatPermissionsRequest;
import by.ourgame.bot.api.method.SetChatPermissionsMethod;
import by.ourgame.bot.model.Game;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ChatService {
    private SetChatPermissionsMethod setChatPermissionsMethod;

    public ChatService(SetChatPermissionsMethod setChatPermissionsMethod) {
        this.setChatPermissionsMethod = setChatPermissionsMethod;
    }

    public Mono<Game> switchOnMessagesInChat(Game game) {
        var chatPermissions = ChatPermissions.builder()
                .canSendMessages(true)
                .canSendMediaMessages(true)
                .canSendOtherMessages(true)
                .canAddWebPagePreviews(true)
                .canChangeInfo(true)
                .canInviteUsers(true)
                .canPinMessages(true)
                .canSendPolls(true)
                .build();
        return modifyChatPermissions(game, chatPermissions);
    }

    public Mono<Game> switchOffMessagesInChat(Game game) {
        var chatPermissions = ChatPermissions.builder()
                .canSendMessages(false)
                .build();
        return modifyChatPermissions(game, chatPermissions);
    }

   private Mono<Game> modifyChatPermissions(Game game, ChatPermissions chatPermissions) {
        var chat = game.getChat();
        if (!chat.getType().contains("group")) {
            return Mono.just(game);
        }
        return setChatPermissionsMethod
                .perform(SetChatPermissionsRequest.builder()
                        .chatId(chat.getId().toString())
                        .permissions(chatPermissions)
                        .build())
                .doOnSuccess(isPermissionsSet -> log.info("Chat permissions has been changed: {}", chatPermissions))
                .then(Mono.just(game));
    }
}
