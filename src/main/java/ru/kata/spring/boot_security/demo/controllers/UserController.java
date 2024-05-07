package ru.kata.spring.boot_security.demo.controllers;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.CustomUserDetailsService;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserController {
    private final CustomUserDetailsService userService;
    public UserController(CustomUserDetailsService userService) {
        this.userService = userService;
    }
    @GetMapping
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.addObject("users", userService.findAll());
        modelAndView.setViewName("users/index");
        return modelAndView;
    }
}
