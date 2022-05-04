package com.tcs.edu;

import com.tcs.edu.decorator.Doubling;
import com.tcs.edu.decorator.MessageOrder;
import com.tcs.edu.decorator.Severity;
import com.tcs.edu.domain.Message;

import static com.tcs.edu.MessageService.print;


class Application {
    public static void main(String[] args) {
        Message message1 = new Message(Severity.MAJOR, "Message1");
        Message message2 = new Message(Severity.MINOR, "Message2" );
        Message message3 = new Message(Severity.REGULAR, "Message3");
        print(message1,message2,message3);
        print(MessageOrder.DESC,Doubling.DISTINCT,message2,message2,message3);
        print(MessageOrder.DESC,Doubling.DOUBLES,message2,message2,message3);
        print(MessageOrder.ASC,Doubling.DOUBLES,message2,message2,message3);
    }
}