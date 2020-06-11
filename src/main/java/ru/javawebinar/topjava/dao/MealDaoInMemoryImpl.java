package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDaoInMemoryImpl implements MealDao {

    Map<Integer, Meal> meals = new ConcurrentHashMap<>();
    AtomicInteger id = new AtomicInteger(0);

    public MealDaoInMemoryImpl() {
        addMeal(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "zavxzx", 500));
//        addMeal(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
//        addMeal(new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
//        addMeal(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
//        addMeal(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
//        addMeal(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
//        addMeal(new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    @Override
    public void addMeal(Meal meal) {
        if (meal.getId() == 0) meal.setId(id.incrementAndGet());
        meals.put(meal.getId(), meal);
    }

    @Override
    public void deleteMeal(int mealId) {
        meals.remove(mealId);
    }

    @Override
    public void updateMeal(int mealId, Meal meal) {
        meal.setId(mealId);
        meals.merge(mealId, meal, (oldMeal, newMeal) -> newMeal);
    }

    @Override
    public List<Meal> getAllMeals() {
        return new ArrayList<>(meals.values());
    }

    @Override
    public Meal getMealById(int mealId) {
        return meals.get(mealId);
    }
}
