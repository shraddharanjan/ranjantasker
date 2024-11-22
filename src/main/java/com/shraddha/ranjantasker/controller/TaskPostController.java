package com.shraddha.ranjantasker.controller;

import com.shraddha.ranjantasker.entity.RequesterAccount;
import com.shraddha.ranjantasker.entity.RequesterTasksDto;
import com.shraddha.ranjantasker.entity.TaskPost;
import com.shraddha.ranjantasker.entity.Users;
import com.shraddha.ranjantasker.services.TaskPostService;
import com.shraddha.ranjantasker.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;

@Controller
public class TaskPostController {

    private final UsersService usersService;
    private final TaskPostService taskPostService;

    @Autowired
    public TaskPostController(UsersService usersService, TaskPostService taskPostService) {
        this.usersService = usersService;
        this.taskPostService = taskPostService;
    }

    @GetMapping("/dashboard/")
    public String searchTasks(Model model){
        Object currentUserProfile = usersService.getCurrentUserProfile();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)){
            String currentUsername = authentication.getName();
            model.addAttribute("username", currentUsername);
            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("Requester"))){
                List<RequesterTasksDto> requesterTasks = taskPostService.getRequesterTasks(((RequesterAccount) currentUserProfile).getUserAccountId());
                model.addAttribute("taskPost", requesterTasks);

            }
        }
        model.addAttribute("user", currentUserProfile);
        return "dashboard";

    }

    @GetMapping("/dashboard/add")
    public String addTasks(Model model){
        model.addAttribute("taskPost", new TaskPost());
        model.addAttribute("user", usersService.getCurrentUserProfile());
        return "add-tasks";
    }

    @PostMapping("/dashboard/addNew")
    public String addNew(TaskPost taskPost, Model model) {
        Users user = usersService.getCurrentUser();
        if (user!= null){
            taskPost.setPostedById(user);
        }

        taskPost.setPostedDate(new Date());
        model.addAttribute("taskPost", taskPost);
        TaskPost saved = taskPostService.addNew(taskPost);
        return "redirect:/dashboard/";
    }
}
