package ru.kata.spring.boot_security.demo.controllers;

import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.AdminService;
import org.springframework.stereotype.Controller;
import ru.kata.spring.boot_security.demo.services.CustomUserDetailsService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    private final CustomUserDetailsService userService;
    public AdminController(AdminService adminService,
                           CustomUserDetailsService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }
    @GetMapping
    public ModelAndView adminPage() {
        ModelAndView modelAndView = new ModelAndView("admin");
        this.adminService.doAdminStuff();
        modelAndView.setViewName("admin");
        return modelAndView;
    }
    @GetMapping("/{id}")
    public ModelAndView show(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("users/show");
        modelAndView.addObject("user", userService.findOne(id));
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("users/edit");
        modelAndView.addObject("user", userService.findOne(id));
        return modelAndView;
    }

    @PatchMapping("/{id}")
    public ModelAndView update(@PathVariable("id") int id,
                               @ModelAttribute("user") @Valid User user,
                               BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("users/edit");
        } else {
            userService.update(id, user);
            modelAndView.setViewName("redirect:/users/" + id);
        }
        return modelAndView;
    }

    @DeleteMapping("/{id}")
    public ModelAndView delete(@PathVariable("id") int id) {
        userService.delete(id);
        return new ModelAndView("redirect:/users");
    }
}
