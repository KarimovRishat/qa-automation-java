package com.tcs.edu;

import com.tcs.edu.decorator.CutDecorator;
import com.tcs.edu.decorator.Severity;
import com.tcs.edu.printer.ConsolePrinter;

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
            ConsolePrinter.print(CutDecorator.cutter(decorate(currentMessage) + " " + severityLevel(level)));
        }
    }
}
