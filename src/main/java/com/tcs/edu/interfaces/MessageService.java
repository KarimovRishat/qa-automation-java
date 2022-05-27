package com.tcs.edu.interfaces;

import com.tcs.edu.domain.LogException;
import com.tcs.edu.enums.Doubling;
import com.tcs.edu.enums.MessageOrder;
import com.tcs.edu.domain.Message;

/**
 * Класс, описывающий интерфейс типа MessageService
 *
 * @author Каримов Ришат
 */

public interface MessageService {
    void log(Message... messages) throws LogException;

    void log(MessageOrder order, Message... messages) throws LogException;

    void log(MessageOrder order, Doubling doubling, Message... messages) throws LogException;
}
