package com.shraddha.ranjantasker.services;

import com.shraddha.ranjantasker.entity.RequesterAccount;
import com.shraddha.ranjantasker.entity.Users;
import com.shraddha.ranjantasker.repository.RequesterAccountRepository;
import com.shraddha.ranjantasker.repository.UsersRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RequesterAccountService {

    private final RequesterAccountRepository requesterAccountRepository;
    private final UsersRepository usersRepository;


    public RequesterAccountService(RequesterAccountRepository requesterAccountRepository, UsersRepository usersRepository) {
        this.requesterAccountRepository = requesterAccountRepository;
        this.usersRepository = usersRepository;
    }

    public Optional<RequesterAccount> getOne(Integer id) {
        return requesterAccountRepository.findById(id);
    }

    public RequesterAccount addNew(RequesterAccount requesterAccount) {
        return requesterAccountRepository.save(requesterAccount);
    }

    public RequesterAccount getCurrentRequesterAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUsername = authentication.getName();
            Users users = usersRepository.findByEmail(currentUsername).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            Optional<RequesterAccount> requesterAccount = getOne(users.getUserId());
            return requesterAccount.orElse(null);
        } else return null;
    }
}
