package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static ru.javawebinar.topjava.util.DateTimeUtil.atStartOfDayOrMin;
import static ru.javawebinar.topjava.util.DateTimeUtil.atStartOfNextDayOrMax;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    @Autowired
    private MealService service;

    @Autowired
    private MealRepository repository;

    @Test
    public void create() {
        Meal newMeal = getNew();
        Meal created = service.create(newMeal, USER_ID);
        Integer newId = created.getId();
        newMeal.setId(newId);
        assertMatch(created, newMeal);
        assertMatch(service.get(newId, USER_ID), newMeal);
    }

    @Test
    public void delete() {
        service.delete(USER_MEAL_1_ID, USER_ID);
        assertNull(repository.get(USER_MEAL_1_ID, USER_ID));
        service.delete(ADMIN_MEAL_1_ID, ADMIN_ID);
        assertNull(repository.get(ADMIN_MEAL_1_ID, ADMIN_ID));
    }

    @Test
    public void deleteNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.delete(NOT_FOUND, USER_ID));
    }

    @Test
    public void deleteForeign() throws Exception {
        assertThrows(NotFoundException.class, () -> service.delete(ADMIN_MEAL_1_ID, USER_ID));
        assertThrows(NotFoundException.class, () -> service.delete(USER_MEAL_1_ID, ADMIN_ID));
    }

    @Test
    public void get() {
        Meal userMeal = service.get(USER_MEAL_1_ID, USER_ID);
        assertMatch(userMeal, USER_MEAL_1);
        Meal adminMeal = service.get(ADMIN_MEAL_1_ID, ADMIN_ID);
        assertMatch(adminMeal, ADMIN_MEAL_1);
    }

    @Test
    public void getNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.get(NOT_FOUND, USER_ID));
    }

    @Test
    public void getForeign() throws Exception {
        assertThrows(NotFoundException.class, () -> service.get(ADMIN_MEAL_1_ID, USER_ID));
        assertThrows(NotFoundException.class, () -> service.get(USER_MEAL_1_ID, ADMIN_ID));
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> filtered = repository.getBetweenHalfOpen(atStartOfDayOrMin(LocalDate.of(2020, Month.JANUARY, 30)), atStartOfNextDayOrMax(LocalDate.of(2020, Month.JANUARY, 30)), USER_ID);
        assertMatch(filtered, USER_MEAL_3, USER_MEAL_2, USER_MEAL_1);
    }

    @Test
    public void update() {
        Meal updated = getUpdatedUser();
        service.update(updated, USER_ID);
        assertMatch(service.get(USER_MEAL_1_ID, USER_ID), updated);
    }

    @Test
    public void updateForeign() {
        assertThrows(NotFoundException.class, () -> service.update(getUpdatedUser(), ADMIN_ID));
        assertThrows(NotFoundException.class, () -> service.update(getUpdatedUser(), ADMIN_ID));
    }

    @Test
    public void getAll() {
        List<Meal> userAll = service.getAll(USER_ID);
        assertMatch(userAll, USER_MEAL_1, USER_MEAL_2, USER_MEAL_3, USER_MEAL_4, USER_MEAL_5, USER_MEAL_6, USER_MEAL_7);
        List<Meal> adminAll = service.getAll(ADMIN_ID);
        assertMatch(adminAll, ADMIN_MEAL_1, ADMIN_MEAL_2);
    }
}