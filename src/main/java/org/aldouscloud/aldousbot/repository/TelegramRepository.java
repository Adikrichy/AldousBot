package org.aldouscloud.aldousbot.repository;

import org.aldouscloud.aldousbot.entity.TelegramUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TelegramRepository extends JpaRepository<TelegramUser, Long> {
    Optional<TelegramUser> findByUsername(String username);
}
