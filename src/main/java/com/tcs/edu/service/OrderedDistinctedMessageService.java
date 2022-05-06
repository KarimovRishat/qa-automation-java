package com.tcs.edu.service;

import com.tcs.edu.enums.Doubling;
import com.tcs.edu.enums.MessageOrder;
import com.tcs.edu.interfaces.MessageDecorator;
import com.tcs.edu.interfaces.MessageService;
import com.tcs.edu.domain.Message;
import com.tcs.edu.interfaces.Printer;

import static com.tcs.edu.decorator.CutDecorator.*;
import static java.util.Arrays.stream;

/**
 * <p>Класс для конкатенации и отправки в принтер отдекорированных сообщений<p/>
 *
 * @author Каримов Ришат
 */
public class OrderedDistinctedMessageService implements MessageService {

    private Printer printer;
    private MessageDecorator decorator;

    /**
     *
     * Конструктор, принимающий параметры принтера и декоратора
     *
     */
    public OrderedDistinctedMessageService(Printer printer, MessageDecorator decorator) {
        this.printer = printer;
        this.decorator = decorator;
    }

    /**
     * <p>Метод конкатенирует и передает в принтер отдекорированные сообщения<p/>
     *
     * @param - print - отдекорированное сообщение со строковым типом
     */
    public void print(Message... messages) {

        for (Message currentMessage : messages) {
            if (currentMessage != null) {
                printer.print(cutter(decorator.decorate(currentMessage)));
            }
        }
    }

    /**
     * Метод сортирует массив сообщений в обратном порядке
     *
     * @param order    - порядковое значение в массиве
     * @param messages - входные данные
     */
    public void print(MessageOrder order, Message... messages) {
        if (order == MessageOrder.DESC) {
            Message[] heep = new Message[messages.length];
            for (int count = messages.length - 1; count >= 0; count--) {
                heep[count] = messages[messages.length - 1 - count];
            }
            messages = heep;
        }
        for (Message currentMessage : messages) {
            if (currentMessage != null) {
                printer.print(cutter(decorator.decorate(currentMessage)));
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
    public void print(MessageOrder order, Doubling doubling, Message... messages) {
        var doublingType = stream(messages);

        if (doubling == Doubling.DISTINCT) {
            doublingType = doublingType.distinct();
        }
        print(order, doublingType.toArray(Message[]::new));

    }

}

