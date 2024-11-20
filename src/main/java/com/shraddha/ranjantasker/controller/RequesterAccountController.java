package com.shraddha.ranjantasker.controller;

import com.shraddha.ranjantasker.entity.RequesterAccount;
import com.shraddha.ranjantasker.entity.Users;
import com.shraddha.ranjantasker.repository.UsersRepository;
import com.shraddha.ranjantasker.services.RequesterAccountService;
import com.shraddha.ranjantasker.util.FileUploadUtil;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
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
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUsername = authentication.getName();
            Users users = usersRepository.findByEmail(currentUsername).orElseThrow(() -> new UsernameNotFoundException("Could not find user"));
            Optional<RequesterAccount> requesterAccount = requesterAccountService.getOne(users.getUserId());

            if (!requesterAccount.isEmpty())
                model.addAttribute("account", requesterAccount.get());
        }
        return "requester_account";

    }

    @PostMapping("/addNew")
    public String addNew(RequesterAccount requesterAccount, @RequestParam("image") MultipartFile multipartFile, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            Users users = usersRepository.findByEmail(currentUserName).orElseThrow(() -> new UsernameNotFoundException("Could not " + "found user"));

            requesterAccount.setUserId(users);
            requesterAccount.setUserAccountId(users.getUserId());

        }
        model.addAttribute("account", requesterAccount);
        String fileName = "";
        if (!multipartFile.getOriginalFilename().equals("")) {
            fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            requesterAccount.setProfilePhoto(fileName);
        }

        RequesterAccount savedUser = requesterAccountService.addNew(requesterAccount);
        String uploadDir = "photos/requester/" + savedUser.getUserAccountId();

        try {
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "redirect:/dashboard/";
    }
}
