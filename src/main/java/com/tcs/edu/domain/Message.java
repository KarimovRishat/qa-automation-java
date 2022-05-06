package com.tcs.edu.domain;

import com.tcs.edu.Enums.Severity;

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

    public Severity getSeverity() {
        return severity;
    }

    public String getBody() {
        return body;
    }

}
