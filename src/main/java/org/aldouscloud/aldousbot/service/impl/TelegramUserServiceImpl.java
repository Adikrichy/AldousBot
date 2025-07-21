package org.aldouscloud.aldousbot.service.impl;

import lombok.RequiredArgsConstructor;
import org.aldouscloud.aldousbot.entity.TelegramUser;
import org.aldouscloud.aldousbot.repository.TelegramRepository;
import org.aldouscloud.aldousbot.service.TelegramUserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TelegramUserServiceImpl implements TelegramUserService {
    private final TelegramRepository telegramRepository;

    @Override
    public void handleUserMessage(Long chatId, String username, String text) {
        TelegramUser telegramUser = telegramRepository.findById(chatId).orElse(
                new TelegramUser(chatId, username, null)
        );
        telegramUser.setUsername(username);
        telegramUser.setLastMessage(text);
        telegramRepository.save(telegramUser);
    }
}
