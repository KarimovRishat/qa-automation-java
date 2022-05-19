package com.tcs.edu.interfaces;

import com.tcs.edu.domain.LogException;

/**
 * Класс, описывающий интферфейс типа Printer
 *
 * @author Каримов Ришат
 */

public interface Printer {
    void print(String decoratedMessage) throws LogException;
}
