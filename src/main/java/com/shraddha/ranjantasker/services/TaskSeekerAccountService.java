package com.shraddha.ranjantasker.services;

import com.shraddha.ranjantasker.entity.TaskSeekerAccount;
import com.shraddha.ranjantasker.repository.TaskSeekerAccountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskSeekerAccountService {

    private final TaskSeekerAccountRepository taskSeekerAccountRepository;

    public TaskSeekerAccountService(TaskSeekerAccountRepository taskSeekerAccountRepository) {
        this.taskSeekerAccountRepository = taskSeekerAccountRepository;
    }

    public Optional<TaskSeekerAccount> getOne(Integer id) {
        return taskSeekerAccountRepository.findById(id);
    }

    public TaskSeekerAccount addNew(TaskSeekerAccount taskSeekerAccount) {
        return taskSeekerAccountRepository.save(taskSeekerAccount);
    }
}
