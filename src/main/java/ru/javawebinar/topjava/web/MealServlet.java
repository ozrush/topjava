package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoInMemoryImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    public static String UPDATE_MEAL = "/meal.jsp";
    public static String LIST_MEALS = "meals.jsp";
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private MealDao dao;

    public MealServlet() {
        super();
        dao = new MealDaoInMemoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";
        Meal meal;
        int mealId;
        switch (action) {
            case "update":
                mealId = Integer.parseInt(request.getParameter("mealId") == null ? "0" : request.getParameter("mealId"));
                meal = dao.getMealById(mealId);
                request.setAttribute("meal", meal);
                request.getRequestDispatcher(UPDATE_MEAL).forward(request, response);
                break;
            case "delete":
                mealId = Integer.parseInt(request.getParameter("mealId"));
                dao.deleteMeal(mealId);
                response.sendRedirect("meals");
                break;
            default:
                refreshList(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String date = request.getParameter("mealDateTime");
        String description = request.getParameter("mealDescription");
        int calories = Integer.parseInt(request.getParameter("mealCalories"));
        Meal meal = new Meal(LocalDateTime.parse(date, formatter), description, calories);
        String mealId = request.getParameter("mealId");
        if (mealId == null || mealId.isEmpty()) {
            dao.addMeal(meal);
        } else {
            dao.updateMeal(Integer.parseInt(mealId), meal);
        }
        refreshList(request, response);
    }

    public void refreshList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<MealTo> mealToList = MealsUtil.filteredByStreams(dao.getAllMeals(), LocalTime.MIN, LocalTime.MAX, 2000);
        request.setAttribute("meals", mealToList);
        request.getRequestDispatcher(LIST_MEALS).forward(request, response);
    }
}
