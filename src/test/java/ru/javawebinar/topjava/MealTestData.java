package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int NOT_FOUND = 11;
    public static final int USER_MEAL_1_ID = START_SEQ + 2;
    public static final int USER_MEAL_2_ID = START_SEQ + 3;
    public static final int USER_MEAL_3_ID = START_SEQ + 4;
    public static final int USER_MEAL_4_ID = START_SEQ + 5;
    public static final int USER_MEAL_5_ID = START_SEQ + 6;
    public static final int USER_MEAL_6_ID = START_SEQ + 7;
    public static final int USER_MEAL_7_ID = START_SEQ + 8;
    public static final int ADMIN_MEAL_1_ID = START_SEQ + 9;
    public static final int ADMIN_MEAL_2_ID = START_SEQ + 10;

    public static final Meal USER_MEAL_1 = new Meal(USER_MEAL_1_ID, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 510);
    public static final Meal USER_MEAL_2 = new Meal(USER_MEAL_2_ID, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000);
    public static final Meal USER_MEAL_3 = new Meal(USER_MEAL_3_ID, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500);
    public static final Meal USER_MEAL_4 = new Meal(USER_MEAL_4_ID, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100);
    public static final Meal USER_MEAL_5 = new Meal(USER_MEAL_5_ID, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 500);
    public static final Meal USER_MEAL_6 = new Meal(USER_MEAL_6_ID, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 1000);
    public static final Meal USER_MEAL_7 = new Meal(USER_MEAL_7_ID, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 510);
    public static final Meal ADMIN_MEAL_1 = new Meal(ADMIN_MEAL_1_ID, LocalDateTime.of(2020, Month.JANUARY, 31, 14, 0), "Админ ланч", 510);
    public static final Meal ADMIN_MEAL_2 = new Meal(ADMIN_MEAL_2_ID, LocalDateTime.of(2020, Month.JANUARY, 31, 21, 0), "Админ ужин", 1500);

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(2020, Month.JULY, 27, 15, 36), "Новая еда", 551);
    }

    public static Meal getUpdatedUser() {
        Meal updated = new Meal(USER_MEAL_1);
        updated.setCalories(updated.getCalories() + 1);
        updated.setDescription("UpdatedDescriptionForUser");
        return updated;
    }

    public static Meal getUpdatedAdmin() {
        Meal updated = new Meal(ADMIN_MEAL_1);
        updated.setCalories(updated.getCalories() + 2);
        updated.setDescription("UpdatedDescriptionForAdmin");
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingFieldByFieldElementComparator().isEqualTo(expected);
    }
}
