package com.tcs.edu;

import com.tcs.edu.interfaces.MessageService;
import com.tcs.edu.service.OrderedDistinctedMessageService;
import com.tcs.edu.enums.Doubling;
import com.tcs.edu.enums.MessageOrder;
import com.tcs.edu.enums.Severity;
import com.tcs.edu.decorator.TimestampMessageDecorator;
import com.tcs.edu.domain.Message;
import com.tcs.edu.printer.ConsolePrinter;


class Application {
    public static void main(String[] args) {
        MessageService service = new OrderedDistinctedMessageService(
                new ConsolePrinter(),
                new TimestampMessageDecorator()
        );
        Message message1 = new Message(Severity.MAJOR, "Message1");
        Message message2 = new Message(Severity.MINOR, "Message2");
        Message message3 = new Message(Severity.REGULAR, "Message3");
        Message message4 = new Message(Severity.MINOR, "Message4");
        service.print(MessageOrder.DESC, Doubling.DOUBLES, message1, message2, message3);
        service.print(MessageOrder.DESC, Doubling.DISTINCT, new Message(Severity.REGULAR, "Message3"));
        service.print(MessageOrder.ASC, Doubling.DISTINCT, new Message(Severity.REGULAR, "Message3"));
        service.print(MessageOrder.DESC, Doubling.DOUBLES, message1, message1, message1, message2);
        service.print(MessageOrder.DESC, Doubling.DISTINCT, message4);
    }
}