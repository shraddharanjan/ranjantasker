package com.shraddha.ranjantasker.services;

import com.shraddha.ranjantasker.entity.TaskSeekerAccount;
import com.shraddha.ranjantasker.entity.Users;
import com.shraddha.ranjantasker.repository.TaskSeekerAccountRepository;
import com.shraddha.ranjantasker.repository.UsersRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskSeekerAccountService {

    private final TaskSeekerAccountRepository taskSeekerAccountRepository;
    private final UsersRepository usersRepository;

    public TaskSeekerAccountService(TaskSeekerAccountRepository taskSeekerAccountRepository, UsersRepository usersRepository) {
        this.taskSeekerAccountRepository = taskSeekerAccountRepository;
        this.usersRepository = usersRepository;
    }

    public Optional<TaskSeekerAccount> getOne(Integer id) {
        return taskSeekerAccountRepository.findById(id);
    }

    public TaskSeekerAccount addNew(TaskSeekerAccount taskSeekerAccount) {
        return taskSeekerAccountRepository.save(taskSeekerAccount);
    }

    public TaskSeekerAccount getCurrentSeekerAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUsername = authentication.getName();
            Users users = usersRepository.findByEmail(currentUsername).orElseThrow(() -> new UsernameNotFoundException("user not found"));
            Optional<TaskSeekerAccount> seekerAccount = getOne(users.getUserId());
            return seekerAccount.orElse(null);
        } else return null;
    }
}
