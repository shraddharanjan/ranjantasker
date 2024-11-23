package com.shraddha.ranjantasker.controller;

import com.shraddha.ranjantasker.entity.*;
import com.shraddha.ranjantasker.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TaskSeekerApplyController {

    private final TaskPostService taskPostService;

    private final UsersService usersService;
    private final TaskSeekerApplyService taskSeekerApplyService;
    private final TaskSeekerSaveService taskSeekerSaveService;
    private final RequesterAccountService requesterAccountService;
    private final TaskSeekerAccountService taskSeekerAccountService;


    @Autowired
    public TaskSeekerApplyController(TaskPostService taskPostService, UsersService usersService, TaskSeekerApplyService taskSeekerApplyService, TaskSeekerSaveService taskSeekerSaveService, RequesterAccountService requesterAccountService, TaskSeekerAccountService taskSeekerAccountService) {
        this.taskPostService = taskPostService;
        this.usersService = usersService;
        this.taskSeekerApplyService = taskSeekerApplyService;
        this.taskSeekerSaveService = taskSeekerSaveService;
        this.requesterAccountService = requesterAccountService;
        this.taskSeekerAccountService = taskSeekerAccountService;
    }

    @GetMapping("task-details-apply/{id}")
    public String display(@PathVariable("id") int id, Model model) {
        TaskPost taskDetails = taskPostService.getOne(id);
        List<TaskSeekerApply> taskSeekerApplyList = taskSeekerApplyService.getTaskCandidates(taskDetails);
        List<TaskSeekerSave> taskSeekerSaveList = taskSeekerSaveService.getTaskCandidates(taskDetails);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("Requester"))) {
                RequesterAccount user = requesterAccountService.getCurrentRequesterAccount();
                if (user != null) {
                    model.addAttribute("applyList", taskSeekerApplyList);
                }
            }
                else {
                    TaskSeekerAccount user = taskSeekerAccountService.getCurrentSeekerAccount();
                    if (user != null) {
                        boolean exists = false;
                        boolean saved = false;
                        for (TaskSeekerApply taskSeekerApply : taskSeekerApplyList) {
                            if (taskSeekerApply.getUserId().getUserAccountId() == user.getUserAccountId()) {
                                exists = true;
                                break;
                            }
                        }
                        for (TaskSeekerSave taskSeekerSave : taskSeekerSaveList) {
                            if (taskSeekerSave.getUserId().getUserAccountId() == user.getUserAccountId()){
                                saved = true;
                                break;
                            }
                        }
                        model.addAttribute("alreadyApplied", exists);
                        model.addAttribute("alreadySaved", saved);
                    }
                }
            }
        TaskSeekerApply taskSeekerApply = new TaskSeekerApply();
        model.addAttribute("applyTask", taskSeekerApply);
        model.addAttribute("taskDetails", taskDetails);
        model.addAttribute("user", usersService.getCurrentUserProfile());
        return "task-details";
    }

}

