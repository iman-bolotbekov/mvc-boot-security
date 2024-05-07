package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.web.servlet.ModelAndView;
import ru.kata.spring.boot_security.demo.services.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    @GetMapping
    public ModelAndView adminPage() {
        ModelAndView modelAndView = new ModelAndView("admin");
        this.adminService.doAdminStuff();
        modelAndView.setViewName("admin");
        return modelAndView;
    }
}
