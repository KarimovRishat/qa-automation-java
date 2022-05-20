package com.tcs.edu.service;

import com.tcs.edu.domain.LogException;
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
public class OrderedDistinctedMessageService extends ValidatedService implements MessageService {

    private static final MessageOrder DEFAULT_ORDER = MessageOrder.ASC;

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
    public void print(Message... messages) throws LogException {
        print(DEFAULT_ORDER, messages);
    }

    /**
     * Метод сортирует массив сообщений в обратном порядке
     *
     * @param order    - порядковое значение в массиве
     * @param messages - входные данные
     */
    public void print(MessageOrder order, Message... messages) throws LogException {
        Message[] heap = new Message[messages.length];
        if (order == MessageOrder.DESC) {

            for (int count = messages.length - 1; count >= 0; count--) {
                heap[count] = messages[messages.length - 1 - count];
            }
        }
        else {
            heap = messages;
        }

        for (Message m : heap) {
            try {
                super.isArgsValid(m);
                printer.print(cutter(decorator.decorate(m)));
            }
            catch (IllegalArgumentException e) {
                throw new LogException("Can`t print message:" + e.getMessage(), e);
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
    public void print(MessageOrder order, Doubling doubling, Message... messages) throws LogException {
        var doublingType = stream(messages);

        if (doubling == Doubling.DISTINCT) {
            doublingType = doublingType.distinct();
        }
        print(order, doublingType.toArray(Message[]::new));

    }

}

