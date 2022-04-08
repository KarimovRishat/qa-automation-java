package com.tcs.edu.decorator;

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
     * <p>Глобальная переменная, декларирующая размер страницы</p>
     */
    public static final int PAGE_SIZE = 2;

    /**
     * <p>Метод возвращает массив типа String<p/>
     *
     * @param message - string message (сообщение со строковым типом)
     * @return - возвращает переменную decoratedMessage (текущая дата и время + сообщение)
     * Переменная decoratedMessage использует форматирование строк по шаблону (шаблон заведен
     * как переменная template)
     * Каждые 2 сообщения текст делится на страницы
     */
    public static String decorate(String message) {
        messageCount++;
        String template = "%d %s %s";
        if (messageCount % PAGE_SIZE == 0) {
            template += "\n ______";
        }
        final var decoratedMessage = String.format(template, messageCount, Instant.now(), message);
        return decoratedMessage;
    }
}
