package com.tcs.edu;

import com.tcs.edu.decorator.MessageOrder;
import com.tcs.edu.decorator.Severity;
import com.tcs.edu.printer.ConsolePrinter;

import static com.tcs.edu.decorator.CutDecorator.*;
import static com.tcs.edu.decorator.SeverityDecorator.severityLevel;
import static com.tcs.edu.decorator.TimestampMessageDecorator.decorate;

/**
 * <p>Класс для инкрементации и отправки в принтер отдекорированных сообщений<p/>
 *
 * @author Каримов Ришат
 */
public class MessageService {
    /**
     * <p>Метод инкрементирует и передает в принтер отдекорированные сообщения<p/>
     *
     * @param - print - отдекорированное сообщение со строковым типом
     */
    public static void print(Severity level, String... messages) {

        for (String currentMessage : messages) {
            if (currentMessage != null) {
                ConsolePrinter.print(cutter(decorate(currentMessage) + " " + severityLevel(level)));
            }
        }
    }

    /**
     * Метод сортирует массив сообщений в обратном порядке
     *
     * @param level    - уровень важности
     * @param order    - порядковое значение в массиве
     * @param messages - входные данные
     */
    public static void print(Severity level, MessageOrder order, String... messages) {
        if (order == MessageOrder.DESC) {
            String[] heep = new String[messages.length];
            for (int count = messages.length - 1; count >= 0; count--) {
                heep[count] = messages[messages.length - 1 - count];
            }
            messages = heep;
        }
        for (String currentMessage : messages) {
            if (currentMessage != null) {
                ConsolePrinter.print(cutter(decorate(currentMessage) + " " + severityLevel(level)));
            }

        }
    }

}

