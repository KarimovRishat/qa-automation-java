package com.tcs.edu.decorator;

/**
 * <p>Класс для определения и отправки визуального отображения уровня важности сообщений<p/>
 *
 * @author Каримов Ришат
 */
public class SeverityDecorator {
    /**
     * <p>Метод определяет и возвращает метку для отображения уровня важности сообщения<p/>
     *
     * @param - severityLevel - символьное отображение важности со строковым типом
     */
    public static String severityLevel(Severity severity) {
        String severityString = null;
        switch (severity) {
            case MINOR:
                severityString = "()";
                break;
            case REGULAR:
                severityString = "(!)";
                break;
            case MAJOR:
                severityString = "(!!!)";
                break;
        }
        return severityString;
    }
}
