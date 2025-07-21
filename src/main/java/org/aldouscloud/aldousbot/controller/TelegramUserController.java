package org.aldouscloud.aldousbot.controller;

import lombok.RequiredArgsConstructor;
import org.aldouscloud.aldousbot.entity.TelegramUser;
import org.aldouscloud.aldousbot.repository.TelegramRepository;
import org.aldouscloud.aldousbot.service.TelegramUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class TelegramUserController {
    private final TelegramRepository telegramRepository;
    @GetMapping
    public List<TelegramUser> getAllUsers(){
        return telegramRepository.findAll();
    }
}
