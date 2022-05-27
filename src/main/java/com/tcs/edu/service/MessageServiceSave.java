package com.tcs.edu.service;

import com.tcs.edu.domain.LogException;
import com.tcs.edu.domain.Message;
import com.tcs.edu.enums.Severity;
import com.tcs.edu.interfaces.MessageServiceSaveInterface;
import com.tcs.edu.repository.HashMapMessageRepository;
import com.tcs.edu.interfaces.MessageRepository;

import java.util.Collection;
import java.util.UUID;

public class MessageServiceSave extends ValidatedService implements MessageServiceSaveInterface {
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
