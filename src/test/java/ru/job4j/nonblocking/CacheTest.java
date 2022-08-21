package ru.job4j.nonblocking;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CacheTest {
    private final Cache cache = new Cache();
    private final Base model = new Base(1, 1);

    @BeforeEach
    public void beforeTest() {
        model.setName("Name1");
    }

    @Test
    void add() {
        assertThat(cache.add(model)).isTrue();
        assertThat(cache.getMemory()).containsEntry(1, model);
    }

    @Test
    void updateSameModel() {
        cache.add(model);
        assertThat(cache.update(model)).isTrue();
        int version = cache.getMemory().get(1).getVersion();
        String name = cache.getMemory().get(1).getName();
        assertThat(version).isEqualTo(2);
        assertThat(name).isEqualTo("Name1");
    }

    @Test
    void updateDifferentModel() {
        cache.add(model);
        model.setName("Name2");
        assertThat(cache.update(model)).isTrue();
        int version = cache.getMemory().get(1).getVersion();
        String name = cache.getMemory().get(1).getName();
        assertThat(version).isEqualTo(2);
        assertThat(name).isEqualTo("Name2");
    }

    @Test
    void updateWhenException() {
        cache.add(model);
        Base model2 = new Base(1, 2);
        model.setName("Name2");
        assertThatThrownBy(() -> cache.update(model2))
                .isInstanceOf(OptimisticException.class);
    }

    @Test
    void whenNoUpdate() {
        cache.add(model);
        Base model2 = new Base(2, 1);
        model.setName("Name2");
        assertThat(cache.update(model2)).isFalse();
    }

    @Test
    void delete() {
        cache.add(model);
        cache.delete(model);
        assertThat(cache.getMemory()).doesNotContainKey(1);
    }
}