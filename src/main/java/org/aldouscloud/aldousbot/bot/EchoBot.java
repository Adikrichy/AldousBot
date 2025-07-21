package org.aldouscloud.aldousbot.bot;

import lombok.RequiredArgsConstructor;
import org.aldouscloud.aldousbot.entity.TelegramUser;
import org.aldouscloud.aldousbot.repository.TelegramRepository;
import org.aldouscloud.aldousbot.service.TelegramUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor
public class EchoBot extends TelegramLongPollingBot {

    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    private final TelegramUserService telegramUserService;

    @Override
    public void onUpdateReceived(Update update){
        if(update.hasMessage() && update.getMessage().hasText()){
            String chatId = update.getMessage().getChatId().toString();
            String text = update.getMessage().getText();
            String usernameFromTg = update.getMessage().getFrom().getUserName();
            if(usernameFromTg == null){
                usernameFromTg = "Unknown";
            }

            telegramUserService.handleUserMessage(Long.parseLong(chatId), usernameFromTg, text);
            String reply = switch (text) {
                case "/start" -> "Привет, я AldousBot! Напиши мне что-нибудь";
                case "/help" -> "Команды: /start, /help, /info";
                case "/info" -> "Ты: @" + usernameFromTg + ", chatId: " + chatId;
                default -> "Ты сказал: " + text;
            };

            SendMessage message = new SendMessage(chatId, reply);
            try{
                execute(message);
            } catch (TelegramApiException e){
                e.printStackTrace();
            }
        }
    }
    @Override
    public String getBotUsername() {
        return username;
    }
    @Override
    public String getBotToken() {
        return token;
    }

}
