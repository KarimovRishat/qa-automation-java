package com.tcs.edu;

import com.tcs.edu.domain.Message;

/**
 * Класс, описывающий интерфейс типа MessageService
 *
 * @author Каримов Ришат
 */

public interface MessageService {
    void print(Message... messages);
}
