package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public final class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public synchronized String getContent(Predicate<Integer> predicate) {
        String output = "";
        try (BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = reader.read()) != -1) {
                if (predicate.test(data)) {
                    output += (char) data;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    public synchronized String getContentWithUnicode() {
        return getContent(data -> true);
    }

    public String getContentWithoutUnicode() {
        return getContent(data -> data < 0x80);
    }
}
