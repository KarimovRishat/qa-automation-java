package com.tcs.edu.decorator;

import com.tcs.edu.domain.Message;

import java.time.Instant;

/**
 * <p>Класс для выдачи текущей даты и времени<p/>
 *
 * @author Каримов Ришат
 */
public class TimestampMessageDecorator {
    /**
     * <p>Глобальная переменная, присваивающая порядковые номера сообщениям</p>
     */
    public static int messageCount = 0;

    /**
     * <p>Метод возвращает массив типа String<p/>
     *
     * @param message - string message (сообщение со строковым типом)
     * @return - возвращает переменную decoratedMessage (текущая дата и время + сообщение)
     * Переменная decoratedMessage использует форматирование строк по шаблону (шаблон заведен
     * как переменная template)
     */
    public static String decorate(Message message) {
        messageCount++;
        String template = "%d %s %s %s";
        return String.format(template, messageCount, Instant.now(), message.getBody(),
                SeverityDecorator.severityLevel(message.getSeverity()));
    }
}
