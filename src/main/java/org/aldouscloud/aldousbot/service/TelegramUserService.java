package org.aldouscloud.aldousbot.service;

import org.aldouscloud.aldousbot.entity.TelegramUser;

public interface TelegramUserService {
    void handleUserMessage(Long chatId, String username, String text);
}
