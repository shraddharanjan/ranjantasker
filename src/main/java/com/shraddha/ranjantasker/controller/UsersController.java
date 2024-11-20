package com.shraddha.ranjantasker.controller;

import com.shraddha.ranjantasker.entity.Users;
import com.shraddha.ranjantasker.entity.UsersCategory;
import com.shraddha.ranjantasker.services.UsersCategoryService;
import com.shraddha.ranjantasker.services.UsersService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class UsersController {

    private final UsersCategoryService usersCategoryService;
    private final UsersService usersService;


    public UsersController(UsersCategoryService usersCategoryService, UsersService usersService) {
        this.usersCategoryService = usersCategoryService;
        this.usersService = usersService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        List<UsersCategory> usersCategories = usersCategoryService.getAll();
        model.addAttribute("getAllCategories", usersCategories);
        model.addAttribute("user", new Users());
        return "register";
    }

    @PostMapping("/register/new")
    public String userRegistration(@Valid Users users, Model model){
        //System.out.println("User: " + users);
        Optional<Users> optionalUsers = usersService.getUserByEmail((users.getEmail()));
        if (optionalUsers.isPresent()){
            model.addAttribute("error", "Email already registered, try to login or register with other email.");
            List<UsersCategory> usersCategories = usersCategoryService.getAll();
            model.addAttribute("getAllCategories", usersCategories);
            model.addAttribute("user", new Users());
            return "register";
        }
        usersService.addNew(users);
        return "dashboard";
    }
}
