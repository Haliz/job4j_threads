package ru.job4j.synch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;

@ThreadSafe
public class UserStore {
    @GuardedBy("this")
    private final List<User> users = new ArrayList<>();

    public synchronized boolean add(User user) {
        if (!users.contains(user)) {
            return users.add(user);
        }
        return false;
    }

    public synchronized boolean update(User user) {
        if (users.contains(user)) {
            users.get(users.indexOf(user)).setAmount(user.getAmount());
            return true;
        }
        return false;
    }

    public synchronized boolean delete(User user) {
        return users.remove(user);
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User fromUser = null;
        User toUser = null;
        for (User user : users) {
            if (user.getId() == fromId) {
                fromUser = user;
            }
            if (user.getId() == toId) {
                toUser = user;
            }
            if (fromUser != null && toUser != null) {
                fromUser.setAmount(fromUser.getAmount() - amount);
                toUser.setAmount(toUser.getAmount() + amount);
                return true;
            }
        }
        return false;
    }
}
