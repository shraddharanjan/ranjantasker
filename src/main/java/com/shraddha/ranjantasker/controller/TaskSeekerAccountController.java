package com.shraddha.ranjantasker.controller;

import com.shraddha.ranjantasker.entity.TaskSeekerAccount;
import com.shraddha.ranjantasker.entity.Users;
import com.shraddha.ranjantasker.repository.UsersRepository;
import com.shraddha.ranjantasker.services.TaskSeekerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/task-seeker-account")
public class TaskSeekerAccountController {

    private TaskSeekerAccountService taskSeekerAccountService;
    private UsersRepository usersRepository;

    @Autowired
    public TaskSeekerAccountController(TaskSeekerAccountService taskSeekerAccountService, UsersRepository usersRepository) {
        this.taskSeekerAccountService = taskSeekerAccountService;
        this.usersRepository = usersRepository;
    }

    @GetMapping("/")
        public String TaskSeekerAccount(Model model) {
            TaskSeekerAccount taskSeekerAccount = new TaskSeekerAccount();
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                Users user = usersRepository.findByEmail(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("User mot found"));
            }
            return "task-seeker-account";
        }
}
