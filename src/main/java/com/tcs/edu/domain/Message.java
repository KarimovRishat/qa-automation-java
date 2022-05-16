package com.tcs.edu.domain;

import com.tcs.edu.enums.Severity;

import java.util.Objects;

/**
 * Класс, описывающий объекты типа Message
 *
 * @author Каримов Ришат
 */

public class Message {

    private Severity severity;
    private String body;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return severity == message.severity && Objects.equals(body, message.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(severity, body);
    }

    @Override
    public String toString() {
        return "Message{" +
                "severity=" + severity +
                ", body='" + body + '\'' +
                '}';
    }
}
