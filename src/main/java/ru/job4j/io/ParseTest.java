package ru.job4j.io;

import java.io.File;

public class ParseTest {
    public static void main(String[] args) {
        File file = new File("src/main/java/ru/job4j/io/Файл.txt");
        ParseFile parseFile = new ParseFile(file);
        SafeFile safeFile = new SafeFile(new File("src/main/java/ru/job4j/io/OutFile.txt"));
        safeFile.saveContent(parseFile.getContentWithUnicode());
    }
}
