package com.ufood.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by pdudenkov on 03.07.2016.
 */

@Controller
@RequestMapping("/rest/api")
public class RestController {
//    @RequestMapping("/")
//    public String home(HttpServletRequest request, HttpServletResponse response) {
//        return "redirect:/contacts";
//    }

    @RequestMapping(value = "/getCalculatedMenu", method= RequestMethod.GET)
    public String getCalculatedMenu(HttpServletRequest request, HttpServletResponse response) {
        return "";
    }

    @RequestMapping(value = "/foodItems", method= RequestMethod.GET)
    public String getAllFoodItems(HttpServletRequest request, HttpServletResponse response) {
        return "";
    }

    @RequestMapping(value = "/foodItem/{id}", method= RequestMethod.GET)
    public String getFoodItem(HttpServletRequest request, HttpServletResponse response, @PathVariable String id) {
        return "";
    }

    @RequestMapping(value = "/foodItem", method= RequestMethod.POST)
    public String addFoodItem(HttpServletRequest request, HttpServletResponse response) {
        return "";
    }

    @RequestMapping(value = "/foodItem/{id}", method= RequestMethod.PUT)
    public String updateFoodItem(HttpServletRequest request, HttpServletResponse response, @PathVariable String id) {
        return "";
    }

    @RequestMapping(value = "/foodItem/{id}", method= RequestMethod.DELETE)
    public String deleteFoodItem(HttpServletRequest request, HttpServletResponse response, @PathVariable String id) {
        return "";
    }

    @RequestMapping(value = "/dishes", method= RequestMethod.GET)
    public String getAllDishes(HttpServletRequest request, HttpServletResponse response) {
        return "";
    }

    @RequestMapping(value = "/dish/{id}", method= RequestMethod.GET)
    public String getDish(HttpServletRequest request, HttpServletResponse response, @PathVariable String id) {
        return "";
    }

    @RequestMapping(value = "/dish", method= RequestMethod.POST)
    public String addDish(HttpServletRequest request, HttpServletResponse response) {
        return "";
    }

    @RequestMapping(value = "/dish/{id}", method= RequestMethod.PUT)
    public String updateDish(HttpServletRequest request, HttpServletResponse response, @PathVariable String id) {
        return "";
    }

    @RequestMapping(value = "/dish/{id}", method= RequestMethod.DELETE)
    public String deleteDish(HttpServletRequest request, HttpServletResponse response, @PathVariable String id) {
        return "";
    }

    @RequestMapping(value = "/search/foodItems/{partialName}", method= RequestMethod.GET)
    public String findFoodItems(HttpServletRequest request, HttpServletResponse response, @PathVariable String partialName) {
        return "";
    }

    @RequestMapping(value = "/search/dishes/{partialName}", method= RequestMethod.GET)
    public String findDishes(HttpServletRequest request, HttpServletResponse response, @PathVariable String partialName) {
        return "";
    }
}