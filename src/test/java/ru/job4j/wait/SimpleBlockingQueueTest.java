package ru.job4j.wait;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class SimpleBlockingQueueTest {

    @Test
    public void offerAndPoolTest() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        List<Integer> list = new ArrayList<>();
        Thread offerTread = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                queue.offer(i);
            }
        });
        Thread poolTread = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                list.add(queue.poll());
            }
        });
        offerTread.start();
        poolTread.start();
        offerTread.join();
        poolTread.join();
        assertThat(list, is(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)));
    }
}