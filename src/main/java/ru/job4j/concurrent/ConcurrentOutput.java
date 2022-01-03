package ru.job4j.concurrent;
/**
 * Данный клас показывает запуск и работу разных потоков.
 * При многократном запуске программы потоки выводят информацию в
 * произвольном порядке.
 */

public class ConcurrentOutput {
    public static void main(String[] args) {
        Thread another = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        another.start();
        second.start();
        System.out.println(Thread.currentThread().getName());
    }
}
