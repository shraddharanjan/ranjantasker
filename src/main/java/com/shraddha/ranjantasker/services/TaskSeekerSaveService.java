package com.shraddha.ranjantasker.services;

import com.shraddha.ranjantasker.entity.TaskPost;
import com.shraddha.ranjantasker.entity.TaskSeekerAccount;
import com.shraddha.ranjantasker.entity.TaskSeekerSave;
import com.shraddha.ranjantasker.repository.TaskSeekerSaveRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskSeekerSaveService {

    private final TaskSeekerSaveRepository taskSeekerSaveRepository;

    public TaskSeekerSaveService(TaskSeekerSaveRepository taskSeekerSaveRepository) {
        this.taskSeekerSaveRepository = taskSeekerSaveRepository;
    }

    public List<TaskSeekerSave> getCandidatesTask(TaskSeekerAccount userAccountId) {
        return taskSeekerSaveRepository.findByUserId(userAccountId);
    }

    public List<TaskSeekerSave> getTaskCandidates(TaskPost task) {
        return taskSeekerSaveRepository.findByTask(task);
    }
}
