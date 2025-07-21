package org.aldouscloud.aldousbot.service.impl;

import lombok.RequiredArgsConstructor;
import org.aldouscloud.aldousbot.entity.Message;
import org.aldouscloud.aldousbot.entity.TelegramUser;
import org.aldouscloud.aldousbot.repository.MessageRepository;
import org.aldouscloud.aldousbot.repository.TelegramRepository;
import org.aldouscloud.aldousbot.service.TelegramUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TelegramUserServiceImpl implements TelegramUserService {
    private final TelegramRepository telegramRepository;
    private final MessageRepository messageRepository;

    @Transactional
    @Override
    public void handleUserMessage(Long chatId, String username, String text) {
        TelegramUser telegramUser = telegramRepository.findById(chatId).orElseGet(
                ()-> {
                    TelegramUser newUser = new TelegramUser();
                    newUser.setUsername(username);
                    newUser.setChatId(chatId);
                    return telegramRepository.save(newUser);
                }
        );

        if(!username.equals(telegramUser.getUsername())){
            telegramUser.setUsername(username);
            telegramRepository.save(telegramUser);
        }

        Message message = new Message();
        message.setText(text);
        message.setTimestamp(LocalDateTime.now());
        message.setUser(telegramUser);

        messageRepository.save(message);
    }
}
