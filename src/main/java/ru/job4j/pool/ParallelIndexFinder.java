package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelIndexFinder extends RecursiveTask<Integer> {

    private final int[] array;
    private final int from;
    private final int to;
    private final int value;

    public ParallelIndexFinder(int[] array, int value, int from, int to) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.value = value;
    }

    public static int indexOf(int[] array, int value) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelIndexFinder(array, value, 0, array.length - 1));
    }

    @Override
    protected Integer compute() {
        if (to + 1 - from < 10) {
            int rsl = -1;
            for (int i = from; i <= to; i++) {
                if (array[i] == value) {
                    rsl = i;
                    break;
                }
            }
            return rsl;
        }
        int mid = (from + to) / 2;
        ParallelIndexFinder left = new ParallelIndexFinder(array, value, from, mid);
        ParallelIndexFinder right = new ParallelIndexFinder(array, value, mid + 1, to);
        left.fork();
        right.fork();
        int leftRsl = left.join();
        int rightRsl = right.join();
        return ParallelIndexFinder.choice(leftRsl, rightRsl);
    }

    private static int choice(int left, int right) {
        if (right >= 0 && left < 0) {
            return right;
        }
            return left;
    }
}
