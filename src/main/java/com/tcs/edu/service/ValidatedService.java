package com.tcs.edu.service;

import com.tcs.edu.domain.Message;

/**
 * <p>Класс для валидации входных данных параметров</p>
 *
 * @author r.karimov
 */

public abstract class ValidatedService {

    /**
     * Метод возвращает false если message = null, или message body = null
     * @param message сообщение для валидации
     * @return возвращает true если сообщение отвечает требованиям
     */

    public boolean isArgsValid (Message message) {
        return message != null && message.getBody() != null;
    }
}
