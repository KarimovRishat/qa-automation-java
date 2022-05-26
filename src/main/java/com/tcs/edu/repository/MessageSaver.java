package com.tcs.edu.repository;

import com.tcs.edu.domain.LogException;
import com.tcs.edu.domain.Message;
import com.tcs.edu.enums.Severity;
import com.tcs.edu.service.ValidatedService;

import java.util.Collection;
import java.util.UUID;

public class MessageSaver extends ValidatedService implements MessageSaverInterface {
    private MessageRepository messageRepository = new HashMapMessageRepository();

    public UUID log(Message message) {
        try {
            super.isArgsValid(message);
        } catch (IllegalArgumentException e) {
            throw new LogException(e);
        }
        return messageRepository.create(message);
    }

    @Override
    public Message findByPrimaryKey(UUID key) {
        return messageRepository.findByPrimaryKey(key);
    }

    @Override
    public Collection<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public Collection<Message> findBySeverity(Severity severity) {
        return messageRepository.findBySeverity(severity);
    }
}
