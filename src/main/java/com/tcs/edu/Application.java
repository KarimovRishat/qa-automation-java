package com.tcs.edu;

import com.tcs.edu.interfaces.MessageService;
import com.tcs.edu.service.OrderedDistinctedMessageService;
import com.tcs.edu.enums.Doubling;
import com.tcs.edu.enums.MessageOrder;
import com.tcs.edu.enums.Severity;
import com.tcs.edu.decorator.TimestampMessageDecorator;
import com.tcs.edu.domain.Message;
import com.tcs.edu.printer.ConsolePrinter;

import static com.tcs.edu.enums.Doubling.*;
import static com.tcs.edu.enums.MessageOrder.*;


class Application {
    public static void main(String[] args) {
        MessageService service = new OrderedDistinctedMessageService(
                new ConsolePrinter(),
                new TimestampMessageDecorator()
        );
        Message message1 = new Message(Severity.MAJOR, "Message1");
        Message message2 = new Message(Severity.MAJOR, "Message1");
        Message message3 = new Message(Severity.MINOR, "Message1");
        Message message4 = new Message(Severity.MINOR, "Message2");
        Message message5 = new Message(Severity.REGULAR, "Message3");
        Message message6 = new Message(Severity.MINOR, "Message4");
        Message message7 = new Message(null);

        service.print(DESC, DISTINCT, message1, message1,
                message2, message3, null, message4, message5,message6,message7);
        service.print(DESC, DOUBLES, message1, message1,
                message2, message3, null, message4, message5,message6,message7);
        service.print(ASC, DOUBLES, message1, message1,
                message2, message3, null, message4, message5,message6,message7);
        service.print(ASC, DISTINCT, message1, message1,
                message2, message3, null, message4, message5,message6,message7);

        System.out.println(message1);
        System.out.println(message1.hashCode());
        System.out.println(message1.equals(message2));
        System.out.println(message1.equals(message4));
    }
}