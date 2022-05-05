package com.tcs.edu.decorator;

import com.tcs.edu.domain.Message;

/**
 * Класс, описывающий интерфейс типа MessageDecorator
 *
 * @author Каримов Ришат
 */

public interface MessageDecorator {
    String decorate(Message message);
}
