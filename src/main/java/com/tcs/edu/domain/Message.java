package com.tcs.edu.domain;

import com.tcs.edu.enums.Severity;

import java.util.Objects;
import java.util.UUID;

/**
 * Класс, описывающий объекты типа Message
 *
 * @author Каримов Ришат
 */

public class Message {

    private Severity severity;
    private String body;
    private UUID id;

    public Message(Severity severity, String body) {
        this.severity = severity;
        this.body = body;
    }

    public Message(String body) {
        this(Severity.MINOR, body);
    }

    public Severity getSeverity() {
        return severity;
    }

    public String getBody() {
        return body;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID key) {
        this.id = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id + " - Message{" +
                "severity=" + severity +
                ", body='" + body + '\'' +
                '}';
    }
}
