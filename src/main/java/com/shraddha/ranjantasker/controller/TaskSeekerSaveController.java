package com.shraddha.ranjantasker.controller;

import com.shraddha.ranjantasker.entity.TaskPost;
import com.shraddha.ranjantasker.entity.TaskSeekerAccount;
import com.shraddha.ranjantasker.entity.TaskSeekerSave;
import com.shraddha.ranjantasker.entity.Users;
import com.shraddha.ranjantasker.services.TaskPostService;
import com.shraddha.ranjantasker.services.TaskSeekerAccountService;
import com.shraddha.ranjantasker.services.TaskSeekerSaveService;
import com.shraddha.ranjantasker.services.UsersService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class TaskSeekerSaveController {
    private final UsersService usersService;
    private final TaskSeekerAccountService taskSeekerAccountService;
    private final TaskPostService taskPostService;
    private final TaskSeekerSaveService taskSeekerSaveService;

    public TaskSeekerSaveController(UsersService usersService, TaskSeekerAccountService taskSeekerAccountService, TaskPostService taskPostService, TaskSeekerSaveService taskSeekerSaveService) {
        this.usersService = usersService;
        this.taskSeekerAccountService = taskSeekerAccountService;
        this.taskPostService = taskPostService;
        this.taskSeekerSaveService = taskSeekerSaveService;
    }

    @PostMapping("task-details/save/{id}")
    public String save(@PathVariable("id") int id, TaskSeekerSave taskSeekerSave) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)){
            String currentUsername = authentication.getName();
            Users user = usersService.findByEmail(currentUsername);
            Optional<TaskSeekerAccount> seekerAccount = taskSeekerAccountService.getOne(user.getUserId());
            TaskPost taskPost = taskPostService.getOne(id);

            if (seekerAccount.isPresent() && taskPost != null){
                taskSeekerSave.setTask(taskPost);
                taskSeekerSave.setUserId(seekerAccount.get());
            } else {
                throw new RuntimeException("User not found");
            }
            taskSeekerSaveService.addNew(taskSeekerSave);
        }
        return "redirect:/dashboard/";
    }

    @GetMapping("saved-tasks/")
    public String savedTasks(Model model) {
        List<TaskPost> taskPost = new ArrayList<>();
        Object currentUserProfile = usersService.getCurrentUserProfile();
        List<TaskSeekerSave> taskSeekerSaveList = taskSeekerSaveService.getCandidatesTask((TaskSeekerAccount) currentUserProfile);
        for (TaskSeekerSave taskSeekerSave : taskSeekerSaveList) {
            taskPost.add(taskSeekerSave.getTask());
        }

        model.addAttribute("taskPost", taskPost);
        model.addAttribute("user", currentUserProfile);
        return "saved-tasks";
    }
}
