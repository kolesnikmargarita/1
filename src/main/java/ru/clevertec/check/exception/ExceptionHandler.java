package ru.clevertec.check.exception;

import java.io.FileWriter;
import java.io.IOException;

public class ExceptionHandler {

    public static void writeExceptionInFile(String message) {
        try (FileWriter writer = new FileWriter("result.csv")) {
            writer.write("ERROR\n" + message);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
