package com.shraddha.ranjantasker.services;

import com.shraddha.ranjantasker.entity.UsersCategory;
import com.shraddha.ranjantasker.repository.UsersCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersCategoryService {

    private final UsersCategoryRepository usersCategoryRepository;


    @Autowired
    public UsersCategoryService(UsersCategoryRepository usersCategoryRepository) {
        this.usersCategoryRepository = usersCategoryRepository;
    }

    public List<UsersCategory> getAll() {
        return usersCategoryRepository.findAll();
    }
}
