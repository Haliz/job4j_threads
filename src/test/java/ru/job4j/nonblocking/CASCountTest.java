package ru.job4j.nonblocking;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class CASCountTest {

    @Test
    public void whenOneTread() {
        CASCount count = new CASCount();
        for (int i = 1; i <= 3; i++) {
            count.increment();
        }
        assertThat(count.get(), is(3));
    }

    @Test
    public void whenTwoTread() throws InterruptedException {
        CASCount count = new CASCount();
        Thread first = new Thread(
                () -> {
                    for (int i = 1; i <= 3; i++) {
                        count.increment();
                    }
                });
        Thread second = new Thread(
                () -> {
                    for (int i = 1; i <= 7; i++) {
                        count.increment();
                    }
                });
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(count.get(), is(10));
    }
}