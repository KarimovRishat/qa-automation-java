package com.tcs.edu.Interface;

import com.tcs.edu.Enums.Doubling;
import com.tcs.edu.Enums.MessageOrder;
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
