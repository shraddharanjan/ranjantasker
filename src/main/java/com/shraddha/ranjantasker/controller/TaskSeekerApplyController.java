package com.shraddha.ranjantasker.controller;

import com.shraddha.ranjantasker.entity.TaskPost;
import com.shraddha.ranjantasker.services.TaskPostService;
import com.shraddha.ranjantasker.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TaskSeekerApplyController {

    private final TaskPostService taskPostService;

    private final UsersService usersService;


    @Autowired
    public TaskSeekerApplyController(TaskPostService taskPostService, UsersService usersService) {
        this.taskPostService = taskPostService;
        this.usersService = usersService;
    }

    @GetMapping("task-details-apply/{id}")
    public String display(@PathVariable("id") int id, Model model) {
        TaskPost taskDetails = taskPostService.getOne(id);
        model.addAttribute("taskDetails", taskDetails);
        model.addAttribute("user", usersService.getCurrentUserProfile());
        return "task-details";
    }

}

