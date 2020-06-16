package ru.javawebinar.topjava.web;

import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;
import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_USER_ID;

public class SecurityUtil {

    private static int authUserId = DEFAULT_USER_ID;

    public static int getAuthUserId() {
        return authUserId;
    }

    public static int authUserCaloriesPerDay() {
        return DEFAULT_CALORIES_PER_DAY;
    }

    public static void setAuthUserId(int authUserId) {
        SecurityUtil.authUserId = authUserId;
    }
}