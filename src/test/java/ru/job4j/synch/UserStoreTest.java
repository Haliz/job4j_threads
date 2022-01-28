package ru.job4j.synch;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class UserStoreTest {

    @Test
    public void addTrue() {
        User user = new User(1, 100);
        UserStore userStore = new UserStore();
        assertThat(userStore.add(user), is(true));
    }

    @Test
    public void addFalse() {
        User user = new User(1, 100);
        User user2 = new User(1, 200);
        UserStore userStore = new UserStore();
        userStore.add(user);
        assertThat(userStore.add(user2), is(false));
    }

    @Test
    public void updateTrue() {
        User user = new User(1, 100);
        User user2 = new User(1, 200);
        UserStore userStore = new UserStore();
        userStore.add(user);
        assertThat(userStore.update(user2), is(true));
        assertThat(user.getAmount(), is(200));
    }

    @Test
    public void updateFalse() {
        User user = new User(1, 100);
        User user2 = new User(2, 200);
        UserStore userStore = new UserStore();
        userStore.add(user);
        assertThat(userStore.update(user2), is(false));
    }

    @Test
    public void removeTrue() {
        User user = new User(1, 100);
        User user2 = new User(2, 200);
        UserStore userStore = new UserStore();
        userStore.add(user);
        userStore.add(user2);
        assertThat(userStore.delete(user), is(true));
    }

    @Test
    public void removeFalse() {
        User user = new User(1, 100);
        User user2 = new User(2, 200);
        UserStore userStore = new UserStore();
        userStore.add(user);
        userStore.add(user2);
        userStore.delete(user);
        assertThat(userStore.delete(user), is(false));
    }

    @Test
    public void transferTrue() {
        User user = new User(1, 100);
        User user2 = new User(2, 200);
        UserStore userStore = new UserStore();
        userStore.add(user);
        userStore.add(user2);
        assertThat(userStore.transfer(1, 2, 50), is(true));
        assertThat(user.getAmount(), is(50));
        assertThat(user2.getAmount(), is(250));
    }
    @Test
    public void transferFalse() {
        User user = new User(1, 100);
        User user2 = new User(2, 200);
        UserStore userStore = new UserStore();
        userStore.add(user);
        userStore.add(user2);
        assertThat(userStore.transfer(1, 3, 50), is(false));
        assertThat(user.getAmount(), is(100));
        assertThat(user2.getAmount(), is(200));
    }
}