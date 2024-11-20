package com.shraddha.ranjantasker.controller;

import com.shraddha.ranjantasker.entity.RequesterAccount;
import com.shraddha.ranjantasker.entity.Users;
import com.shraddha.ranjantasker.repository.UsersRepository;
import com.shraddha.ranjantasker.services.RequesterAccountService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/requester-account")
public class RequesterAccountController {

    private final UsersRepository usersRepository;
    private final RequesterAccountService requesterAccountService;



    public RequesterAccountController(UsersRepository usersRepository, RequesterAccountService requesterAccountService) {
        this.usersRepository = usersRepository;
        this.requesterAccountService = requesterAccountService;
    }

    @GetMapping("/")
    public String requesterAccount(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)){
            String currentUsername = authentication.getName();
            Users users = usersRepository.findByEmail(currentUsername).orElseThrow(()-> new UsernameNotFoundException("Could not find user"));
            Optional<RequesterAccount> requesterAccount = requesterAccountService.getOne(users.getUserId());

            if(!requesterAccount.isEmpty())
                model.addAttribute("account", requesterAccount.get());
        }
        return "requester_account";

    }
}
