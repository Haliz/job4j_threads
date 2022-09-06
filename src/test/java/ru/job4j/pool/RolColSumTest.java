package ru.job4j.pool;

import org.junit.jupiter.api.Test;
import java.util.concurrent.ExecutionException;
import static org.assertj.core.api.Assertions.*;

class RolColSumTest {
    int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
    };
    Sums[] expected = {
            new Sums(6, 12),
            new Sums(15, 15),
            new Sums(24, 18)
    };

    @Test
    void sum() {
        assertThat(RolColSum.sum(matrix)).isEqualTo(expected);
    }

    @Test
    void asyncSum() throws ExecutionException, InterruptedException {
        assertThat(RolColSum.asyncSum(matrix)).isEqualTo(expected);
    }
}