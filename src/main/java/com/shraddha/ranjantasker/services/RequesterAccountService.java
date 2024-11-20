package com.shraddha.ranjantasker.services;

import com.shraddha.ranjantasker.entity.RequesterAccount;
import com.shraddha.ranjantasker.repository.RequesterAccountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RequesterAccountService {

    private final RequesterAccountRepository requesterAccountRepository;


    public RequesterAccountService(RequesterAccountRepository requesterAccountRepository) {
        this.requesterAccountRepository = requesterAccountRepository;
    }

    public Optional<RequesterAccount> getOne(Integer id) {
        return requesterAccountRepository.findById(id);
    }
}
