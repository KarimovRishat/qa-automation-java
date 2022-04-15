package com.tcs.edu.decorator;

import static com.tcs.edu.decorator.TimestampMessageDecorator.messageCount;
/**
 * <p>Класс для декларирования размера страницы и их визуального разделения<p/>
 *
 * @author Каримов Ришат
 */
public class CutDecorator {

    /**
     * <p>Метод декларирует размер страницы и устанавливает их разделитель<p/>
     */

    /**
     * <p>Глобальная переменная, декларирующая размер страницы</p>
     */
    public static final int PAGE_SIZE = 2;
    /**
     * <p>Переменная, считающая количество сообщений на странице,
     * и добавляющая разделение страниц после каждых двух сообщений</p>
     */
    public static String cutter(String cutter) {
        String templateCut = "%s";
        if (messageCount % PAGE_SIZE == 0) {
            templateCut += "\n ______";
        }
        return String.format(templateCut, cutter);
    }
}

