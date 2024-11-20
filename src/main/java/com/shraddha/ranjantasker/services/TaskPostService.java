package com.shraddha.ranjantasker.services;

import com.shraddha.ranjantasker.entity.TaskPost;
import com.shraddha.ranjantasker.repository.TaskPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskPostService {

    private final TaskPostRepository taskPostRepository;

    @Autowired
    public TaskPostService(TaskPostRepository taskPostRepository) {
        this.taskPostRepository = taskPostRepository;
    }

    public TaskPost addNew(TaskPost taskPost) {
        return taskPostRepository.save(taskPost);
    }
}
