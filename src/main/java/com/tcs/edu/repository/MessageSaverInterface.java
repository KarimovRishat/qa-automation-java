package com.tcs.edu.repository;

import com.tcs.edu.domain.Message;

import java.util.Collection;
import java.util.UUID;

public interface MessageSaverInterface {
    UUID log(Message message);

    Message findByPrimaryKey(UUID key);

    Collection<Message> findAll();
}
