package com.ufood.controller;

import com.ufood.model.*;
import static com.ufood.util.MockUtil.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by pdudenkov on 03.07.2016.
 */

@Controller
@RequestMapping("/")
public class RestController {

    @RequestMapping("/")
    public @ResponseBody
    String home(HttpServletRequest request, HttpServletResponse response) {
        return "hello, ufoodo deployed";
    }

    @RequestMapping(value = "getCalculatedMenu", method= RequestMethod.GET)
    public @ResponseBody
    Result getCalculatedMenu(HttpServletRequest request, HttpServletResponse response
    //, @RequestBody Task
    ) {
        return generateResult();
    }

    @RequestMapping(value = "foodItems", method= RequestMethod.GET)
    public @ResponseBody
    List<FoodItem> getAllFoodItems(HttpServletRequest request, HttpServletResponse response) {
        return generateFoodItemList();
    }

    @RequestMapping(value = "foodItem/{id}", method= RequestMethod.GET)
    public @ResponseBody
    FoodItem getFoodItem(HttpServletRequest request, HttpServletResponse response, @PathVariable String id) {
        return generateFoodItem();
    }

    @RequestMapping(value = "foodItem", method= RequestMethod.POST)
    public @ResponseBody
    String addFoodItem(HttpServletRequest request, HttpServletResponse response
//    , @RequestBody FoodItem
    ) {
        return "(stub)ок, молодец";
    }

    @RequestMapping(value = "foodItem/{id}", method= RequestMethod.PUT)
    public @ResponseBody
    String updateFoodItem(HttpServletRequest request, HttpServletResponse response, @PathVariable String id) {
        return "(stub)ok, updated";
    }

    @RequestMapping(value = "foodItem/{id}", method= RequestMethod.DELETE)
    public @ResponseBody
    String deleteFoodItem(HttpServletRequest request, HttpServletResponse response, @PathVariable String id) {
        return "(stub)ok, deleted";
    }

    @RequestMapping(value = "dishes", method= RequestMethod.GET)
    public @ResponseBody
    List<Dish> getAllDishes(HttpServletRequest request, HttpServletResponse response) {
        return generateDishList();
    }

    @RequestMapping(value = "dish/{id}", method= RequestMethod.GET)
    public @ResponseBody
    Dish getDish(HttpServletRequest request, HttpServletResponse response, @PathVariable String id) {
        return generateDish();
    }

    @RequestMapping(value = "dish", method= RequestMethod.POST)
    public @ResponseBody
    String addDish(HttpServletRequest request, HttpServletResponse response) {
        return "(stub)ok";
    }

    @RequestMapping(value = "dish/{id}", method= RequestMethod.PUT)
    public @ResponseBody
    String updateDish(HttpServletRequest request, HttpServletResponse response, @PathVariable String id) {
        return "(stub)ok";
    }

    @RequestMapping(value = "dish/{id}", method= RequestMethod.DELETE)
    public @ResponseBody
    String deleteDish(HttpServletRequest request, HttpServletResponse response, @PathVariable String id) {
        return "(stub)ok";
    }

    @RequestMapping(value = "search/foodItems/{partialName}", method= RequestMethod.GET)
    public @ResponseBody
    List<FoodItem> findFoodItems(HttpServletRequest request, HttpServletResponse response, @PathVariable String partialName) {
        return generateFoodItemList();
    }

    @RequestMapping(value = "search/dishes/{partialName}", method= RequestMethod.GET)
    public @ResponseBody
    List<Dish> findDishes(HttpServletRequest request, HttpServletResponse response, @PathVariable String partialName) {
        return generateDishList();
    }
}