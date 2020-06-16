package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

public class UsersUtil {
    public static final List<User> USERS = Arrays.asList(
            new User(1, "Admin", "a@gm.com", "pass", Role.ADMIN, Role.USER),
            new User(2, "User", "u@gm.com", "pass1", Role.USER)
    );
}
