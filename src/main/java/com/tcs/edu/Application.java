package com.tcs.edu;

import com.tcs.edu.decorator.Doubling;
import com.tcs.edu.decorator.MessageOrder;
import com.tcs.edu.decorator.Severity;

import static com.tcs.edu.MessageService.print;


class Application {
    public static void main(String[] args) {
        print(Severity.MAJOR, MessageOrder.ASC, Doubling.DISTINCT, "Hello world!", "Hello world!", "1", "2", "3");
        print(Severity.MINOR, MessageOrder.DESC, Doubling.DOUBLES, "Hello world!", "Hello world!", "1", "2", "3", "4");
        print(Severity.MINOR, MessageOrder.DESC, Doubling.DOUBLES, "Hello world!", "test", null);
        print(Severity.MAJOR, MessageOrder.DESC, Doubling.DISTINCT, "Hello world!", "test");
        print(Severity.MAJOR, MessageOrder.ASC, Doubling.DISTINCT, "Hello world!", "test", "test", "test");


    }
}