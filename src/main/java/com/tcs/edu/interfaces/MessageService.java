package com.tcs.edu.interfaces;

import com.tcs.edu.enums.Doubling;
import com.tcs.edu.enums.MessageOrder;
import com.tcs.edu.domain.Message;

/**
 * Класс, описывающий интерфейс типа MessageService
 *
 * @author Каримов Ришат
 */

public interface MessageService {
    void print(Message... messages);

    void print(MessageOrder order, Message... messages);

    void print(MessageOrder order, Doubling doubling, Message... messages);
}
