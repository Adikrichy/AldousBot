package org.aldouscloud.aldousbot.controller;

import lombok.RequiredArgsConstructor;
import org.aldouscloud.aldousbot.entity.TelegramUser;
import org.aldouscloud.aldousbot.repository.TelegramRepository;
import org.aldouscloud.aldousbot.service.TelegramUserService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class TelegramUserController {
    private final TelegramRepository telegramRepository;

    @GetMapping
    @Transactional(readOnly = true)
    public List<TelegramUser> getAllUsers(){
        return telegramRepository.findAll();
    }

    @GetMapping("/{chatId}")
    @Transactional(readOnly = true)
    public TelegramUser getUser(@PathVariable Long chatId){
        return telegramRepository.findById(chatId).orElse(null);
    }
}
