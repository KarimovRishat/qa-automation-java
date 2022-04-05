package com.tcs.edu.decorator;

import java.time.Instant;

/**
 * <p>Класс для выдачи текущей даты и времени<p/>
 * @author Каримов Ришат
 */
public class TimestampMessageDecorator {
    /**
     * <p>Глобальная переменная, присваивающая порядковые номера сообщениям</p>
     */
    public static int messageCount = 0;

    /**
     * <p>Метод возвращает массив типа String<p/>
     * @param message - string message (сообщение со строковым типом)
     * @return - возвращает переменную decoratedMessage (текущая дата и время + сообщение)
     * Переменная decoratedMessage использует форматирование строк по шаблону
     */
    public static String decorate(String message) {
        messageCount++;
        final var decoratedMessage = String.format("%d %s %s", messageCount, Instant.now(), message);
        return decoratedMessage;
    }
}
