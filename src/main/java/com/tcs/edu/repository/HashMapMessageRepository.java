package com.tcs.edu.repository;

import com.tcs.edu.domain.Message;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HashMapMessageRepository implements MessageRepository{
    private Map<UUID, Message> messages = new HashMap<>();

    @Override
    public UUID create(Message message) {
        UUID id = UUID.randomUUID();
        messages.put(id, message);
        message.setId(id);
        return id;
    }

    @Override
    public Message findByPrimaryKey(UUID key) {
        return messages.get(key);
    }

    @Override
    public Collection<Message> findAll() {
        return messages.values();
    }
}
