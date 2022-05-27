package com.tcs.edu.interfaces;

import com.tcs.edu.domain.Message;
import com.tcs.edu.enums.Severity;

import java.util.Collection;
import java.util.UUID;

public interface MessageServiceSaveInterface {
    UUID log(Message message);

    Message findByPrimaryKey(UUID key);

    Collection<Message> findAll();

    Collection<Message> findBySeverity(Severity severity);
}
