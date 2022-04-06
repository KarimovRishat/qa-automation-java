package com.tcs.edu.printer;

import static com.tcs.edu.decorator.TimestampMessageDecorator.decorate;

/**
 * <p>Класс для вывода сообщения<p/>
 *
 * @author Каримов Ришат
 */
public class ConsolePrinter {
    /**
     * <p>Метод выводит массив типа String<p/>
     *
     * @param message - string message (сообщение со строковым типом)
     * @affects print - none (нет побочных эффектов)
     */
    public static void print(String message) {
        System.out.println(decorate(message));
    }
}
