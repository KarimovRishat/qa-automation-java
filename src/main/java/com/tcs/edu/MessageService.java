package com.tcs.edu;

import com.tcs.edu.decorator.MessageOrder;
import com.tcs.edu.decorator.Severity;
import com.tcs.edu.domain.Message;
import com.tcs.edu.printer.ConsolePrinter;
import com.tcs.edu.decorator.Doubling;

import static com.tcs.edu.decorator.CutDecorator.*;
import static com.tcs.edu.decorator.SeverityDecorator.severityLevel;
import static com.tcs.edu.decorator.TimestampMessageDecorator.decorate;
import static java.util.Arrays.stream;

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
    public static void print(Message... messages) {

       for (Message currentMessage : messages) {
           if (currentMessage != null)
            {
                ConsolePrinter.print(cutter(decorate(currentMessage)));
            }
        }
    }

    /**
     * Метод сортирует массив сообщений в обратном порядке
     *
     * @param order    - порядковое значение в массиве
     * @param messages - входные данные
     */
    public static void print(MessageOrder order, Message... messages) {
        if (order == MessageOrder.DESC) {
            Message[] heep = new Message[messages.length];
            for (int count = messages.length - 1; count >= 0; count--) {
                heep[count] = messages[messages.length - 1 - count];
            }
            messages = heep;
        }
        for (Message currentMessage : messages) {
            if (currentMessage != null) {
                ConsolePrinter.print(cutter(decorate(currentMessage)));
            }

        }
    }

    /**
     * Метод осуществляет проверку на дубли, игнорируя их при выводе
     *
     * @param order-    порядковое значение в массиве
     * @param doubling- параметр дублирования
     * @param messages- входные данные
     */
    public static void print(MessageOrder order, Doubling doubling, Message... messages) {
        var doublingType = stream(messages);

        if (doubling == Doubling.DISTINCT) {
            doublingType = doublingType.distinct();
        }
        print(order, doublingType.toArray(Message[]::new));

    }

}

