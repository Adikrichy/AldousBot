package org.aldouscloud.aldousbot.service;

import lombok.RequiredArgsConstructor;
import org.aldouscloud.aldousbot.entity.TelegramUser;
import org.aldouscloud.aldousbot.repository.TelegramRepository;
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

    private final TelegramRepository telegramRepository;

    @Override
    public void onUpdateReceived(Update update){
        if(update.hasMessage() && update.getMessage().hasText()){
            String chatId = update.getMessage().getChatId().toString();
            String text = update.getMessage().getText();
            String usernameFromTg = update.getMessage().getFrom().getUserName();

            TelegramUser user = new TelegramUser(
                    Long.parseLong(chatId),
                    usernameFromTg,
                    text
            );
            telegramRepository.save(user);

            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText("You said:"+text);

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
