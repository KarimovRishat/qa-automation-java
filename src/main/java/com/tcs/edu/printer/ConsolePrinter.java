package com.tcs.edu.printer;

import com.tcs.edu.Interface.Printer;

/**
 * <p>Класс для вывода сообщения<p/>
 *
 * @author Каримов Ришат
 */
public class ConsolePrinter implements Printer {
    /**
     * <p>Метод выводит массив типа String<p/>
     *
     * @param - print - string message (отдекорированное сообщение со строковым типом)
     */
    public void print(String decoratedMessage) {

        System.out.println(decoratedMessage);
    }

}

