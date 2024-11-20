package com.shraddha.ranjantasker.controller;

import com.shraddha.ranjantasker.entity.Users;
import com.shraddha.ranjantasker.entity.UsersCategory;
import com.shraddha.ranjantasker.services.UsersCategoryService;
import com.shraddha.ranjantasker.services.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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
        usersService.addNew(users);
        return "redirect:/dashboard/";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/";
    }
}
