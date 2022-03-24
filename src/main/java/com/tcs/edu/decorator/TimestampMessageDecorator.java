package com.tcs.edu.decorator;

import java.time.Instant;
/**
 * <p>Класс для выдачи текущей даты и времени<p/>
 * @author Каримов Ришат
 */
public class TimestampMessageDecorator {
    /**
     * <p>Метод возвращает массив типа String<p/>
     * @param message - string message (сообщение со строковым типом)
     * @return - возвращает текущую дату и время + сообщение
     */
    public static String decorate(String message) {
        return Instant.now() + " " + message;
    }
}
