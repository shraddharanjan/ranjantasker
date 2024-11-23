package com.shraddha.ranjantasker.controller;

import com.shraddha.ranjantasker.entity.*;
import com.shraddha.ranjantasker.services.TaskPostService;
import com.shraddha.ranjantasker.services.TaskSeekerApplyService;
import com.shraddha.ranjantasker.services.TaskSeekerSaveService;
import com.shraddha.ranjantasker.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
public class TaskPostController {

    private final UsersService usersService;
    private final TaskPostService taskPostService;
    private final TaskSeekerApplyService taskSeekerApplyService;
    private final TaskSeekerSaveService taskSeekerSaveService;


    @Autowired
    public TaskPostController(UsersService usersService, TaskPostService taskPostService, TaskSeekerApplyService taskSeekerApplyService, TaskSeekerSaveService taskSeekerSaveService) {
        this.usersService = usersService;
        this.taskPostService = taskPostService;
        this.taskSeekerApplyService = taskSeekerApplyService;
        this.taskSeekerSaveService = taskSeekerSaveService;
    }

    @GetMapping("/dashboard/")
    public String searchTasks(Model model,
                              @RequestParam(value = "task", required = false) String task,
                              @RequestParam(value = "location", required = false) String location,
                              @RequestParam(value = "partTime", required = false) String partTime,
                              @RequestParam(value = "fullTime", required = false) String fullTime,
                              @RequestParam(value = "freelance", required = false) String freelance,
                              @RequestParam(value = "remoteOnly", required = false) String remoteOnly,
                              @RequestParam(value = "officeOnly", required = false) String officeOnly,
                              @RequestParam(value = "partialRemote", required = false) String partialRemote,
                              @RequestParam(value = "today", required = false) boolean today,
                              @RequestParam(value = "days7", required = false) boolean days7,
                              @RequestParam(value = "days30", required = false) boolean days30){
        model.addAttribute("partTime", Objects.equals(partTime, "Part-Time"));
        model.addAttribute("fullTime", Objects.equals(fullTime, "Full-Time"));
        model.addAttribute("freelance", Objects.equals(freelance, "Freelance"));
        model.addAttribute("remoteOnly", Objects.equals(remoteOnly, "Remote-Only"));
        model.addAttribute("officeOnly", Objects.equals(officeOnly, "Office-Only"));
        model.addAttribute("partialRemote", Objects.equals(partialRemote, "Partial-Remote"));
        model.addAttribute("today", today);
        model.addAttribute("days7", days7);
        model.addAttribute("days30", days30);
        model.addAttribute("task", task);
        model.addAttribute("location", location);
        LocalDate searchDate = null;
        List<TaskPost> taskPost = null;
        boolean dateSearchFlag = true;
        boolean remote = true;
        boolean type = true;

        if (days30) {
            searchDate = LocalDate.now().minusDays(30);
        } else if (days7) {
            searchDate = LocalDate.now().minusDays(7);
        } else if (today) {
            searchDate = LocalDate.now();
        } else {
            dateSearchFlag = false;
        }
        if (partTime == null && fullTime == null && freelance == null ){
            partTime = "Part-Time";
            fullTime = "Full-Time";
            freelance= "Freelance";
            remote = false;
        }
        if (officeOnly==null && remoteOnly == null && partialRemote==null){
            officeOnly = "Office-Only";
            remoteOnly = "Remote-Only";
            partialRemote = "Partial-Remote";
            type=false;
        }

        if (!dateSearchFlag && !remote && !type && !StringUtils.hasText(task) && !StringUtils.hasText(location)) {
            taskPost = taskPostService.getAll();
        } else {
            taskPost = taskPostService.search(task, location, Arrays.asList(partTime, fullTime, freelance),
                    Arrays.asList(remoteOnly, officeOnly, partialRemote), searchDate);
        }


        Object currentUserProfile = usersService.getCurrentUserProfile();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)){
            String currentUsername = authentication.getName();
            model.addAttribute("username", currentUsername);
            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("Requester"))){
                List<RequesterTasksDto> requesterTasks = taskPostService.getRequesterTasks(((RequesterAccount) currentUserProfile).getUserAccountId());
                model.addAttribute("taskPost", requesterTasks);
            } else {
                List<TaskSeekerApply> taskSeekerApplyList = taskSeekerApplyService.getCandidatesTasks((TaskSeekerAccount) currentUserProfile);
                List<TaskSeekerSave> taskSeekerSaveList = taskSeekerSaveService.getCandidatesTask((TaskSeekerAccount) currentUserProfile);
                boolean exist;
                boolean saved;

                for (TaskPost taskActivity : taskPost) {
                    exist = false;
                    saved = false;
                    for (TaskSeekerApply taskSeekerApply : taskSeekerApplyList) {
                        if (Objects.equals(taskActivity.getTaskPostId(), taskSeekerApply.getTask().getTaskPostId())){
                            taskActivity.setIsActive(true);
                            exist = true;
                            break;
                        }
                    }
                    for (TaskSeekerSave taskSeekerSave : taskSeekerSaveList) {
                        if (Objects.equals(taskActivity.getTaskPostId(), taskSeekerSave.getTask().getTaskPostId())){
                            taskActivity.setIsSaved(true);
                            saved=true;
                            break;
                        }
                    }

                    if (!exist){
                        taskActivity.setIsActive(false);
                    }
                    if (!saved){
                        taskActivity.setIsSaved(false);
                    }
                    model.addAttribute("taskPost", taskPost);
                }
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

    @PostMapping("dashboard/edit/{id}")
    public String editJob(@PathVariable("id") int id, Model model) {
        TaskPost taskPost = taskPostService.getOne(id);
        model.addAttribute("taskPost", taskPost);

        model.addAttribute("user", usersService.getCurrentUserProfile());
        return "add-tasks";
    }
}
