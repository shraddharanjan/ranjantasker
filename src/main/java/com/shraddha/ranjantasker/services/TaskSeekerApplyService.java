package com.shraddha.ranjantasker.services;

import com.shraddha.ranjantasker.entity.TaskPost;
import com.shraddha.ranjantasker.entity.TaskSeekerAccount;
import com.shraddha.ranjantasker.entity.TaskSeekerApply;
import com.shraddha.ranjantasker.repository.TaskSeekerApplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskSeekerApplyService {

    private final TaskSeekerApplyRepository taskSeekerApplyRepository;

    @Autowired
    public TaskSeekerApplyService(TaskSeekerApplyRepository taskSeekerApplyRepository) {
        this.taskSeekerApplyRepository = taskSeekerApplyRepository;
    }

    public List<TaskSeekerApply> getCandidatesTasks(TaskSeekerAccount userAccountId) {
        return taskSeekerApplyRepository.findByUserId(userAccountId);
    }

    public List<TaskSeekerApply> getTaskCandidates(TaskPost task){
        return taskSeekerApplyRepository.findByTask(task);
    }
}
