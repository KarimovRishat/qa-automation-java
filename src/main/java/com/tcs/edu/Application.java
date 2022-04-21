package com.tcs.edu;

import com.tcs.edu.decorator.MessageOrder;
import com.tcs.edu.decorator.Severity;

import static com.tcs.edu.MessageService.print;


class Application {
    public static void main(String[] args) {
        print(Severity.MAJOR, MessageOrder.ASC, "Hello world!", "test", "test23456");
        print(Severity.MAJOR, MessageOrder.DESC, "Hello world!", "123", "123134");
        print(Severity.MAJOR, MessageOrder.DESC, "Hello world!", "testtesttest");
        print(Severity.MAJOR, MessageOrder.DESC, "Hello world!", "ssssss");
        print(Severity.MAJOR, MessageOrder.ASC, "Hello world!", "RReeer");


    }
}