package com.tcs.edu;

import com.tcs.edu.decorator.DecoratingMessageService;
import com.tcs.edu.decorator.Doubling;
import com.tcs.edu.decorator.MessageOrder;
import com.tcs.edu.decorator.Severity;
import com.tcs.edu.domain.Message;


class Application {
    public static void main(String[] args) {
        DecoratingMessageService service = new DecoratingMessageService();
        Message message1 = new Message(Severity.MAJOR, "Message1");
        Message message2 = new Message(Severity.MINOR, "Message2");
        Message message3 = new Message(Severity.REGULAR, "Message3");
        service.print(message1, message2, message3);
        service.print(MessageOrder.DESC, Doubling.DISTINCT, message1, message2, message3);
        service.print(MessageOrder.DESC, Doubling.DOUBLES, message2, message2, message3);
        service.print(MessageOrder.ASC, Doubling.DOUBLES, message2, message2, message3);
    }
}