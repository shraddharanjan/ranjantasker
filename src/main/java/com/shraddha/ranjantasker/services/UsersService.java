package com.shraddha.ranjantasker.services;

import com.shraddha.ranjantasker.entity.RequesterAccount;
import com.shraddha.ranjantasker.entity.TaskSeekerAccount;
import com.shraddha.ranjantasker.entity.Users;
import com.shraddha.ranjantasker.repository.RequesterAccountRepository;
import com.shraddha.ranjantasker.repository.TaskSeekerAccountRepository;
import com.shraddha.ranjantasker.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final TaskSeekerAccountRepository taskSeekerAccountRepository;
    private final RequesterAccountRepository requesterAccountRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersService(UsersRepository usersRepository, TaskSeekerAccountRepository taskSeekerAccountRepository, RequesterAccountRepository requesterAccountRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.taskSeekerAccountRepository = taskSeekerAccountRepository;
        this.requesterAccountRepository = requesterAccountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Users addNew(Users users){
        users.setActive(true);
        users.setRegistrationDate(new Date(System.currentTimeMillis()));
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        Users savedUser = usersRepository.save(users);
        int userCategoryId = users.getUserCategoryId().getUserCategoryId();
        if (userCategoryId == 1){
            requesterAccountRepository.save(new RequesterAccount(savedUser));
        }
        else {
            taskSeekerAccountRepository.save(new TaskSeekerAccount(savedUser));
        }
        return usersRepository.save(users);
    }

    public Optional<Users> getUserByEmail(String email) {
        return usersRepository.findByEmail(email);
    }
}
