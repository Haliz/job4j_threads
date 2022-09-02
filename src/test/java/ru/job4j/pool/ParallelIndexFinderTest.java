package ru.job4j.pool;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ParallelIndexFinderTest {

    @Test
    void whenLess10() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertThat(ParallelIndexFinder.indexOf(arr, 6)).isEqualTo(5);
    }

    @Test
    void whenLess10NoValue() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertThat(ParallelIndexFinder.indexOf(arr, 10)).isEqualTo(-1);
    }

    @Test
    void whenMore10() {
        int[] arr = {1, 2, 3, 4, 5, 13, 7, 8, 9, 10, 11, 12, 6, 14};
        assertThat(ParallelIndexFinder.indexOf(arr, 6)).isEqualTo(12);
    }

    @Test
    void whenMore10TwoValue() {
        int[] arr = {1, 2, 3, 4, 5, 13, 7, 8, 9, 10, 11, 12, 13, 14};
        assertThat(ParallelIndexFinder.indexOf(arr, 13)).isEqualTo(5);
    }

    @Test
    void whenMore100() {
        int[] arr = new int[150];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr.length - i;
        }
        assertThat(ParallelIndexFinder.indexOf(arr, 50)).isEqualTo(100);
    }
}