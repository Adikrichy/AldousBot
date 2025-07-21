package org.aldouscloud.aldousbot.repository;

import org.aldouscloud.aldousbot.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
