package com.site.autosite.account;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AuthenticationController {
    
    private final AuthenticactionService authenticactionService;

    public AuthenticationController(AuthenticactionService authenticactionService) {
        this.authenticactionService = authenticactionService;
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("account", new Account());
        return "register";
    }

    @PostMapping("/register")
    public String register(Account account, Model model) {
        AuthenticationResponse response = authenticactionService.register(account);
        model.addAttribute("token", response.getToken());
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(Account account, Model model) {
        AuthenticationResponse response = authenticactionService.authenticate(account);
        model.addAttribute("token", response.getToken());
        return "redirect:/home"; // или куда вы хотите перенаправить после логина
    }

    @GetMapping("home")
    public String index(){
        return "index";
    }
}
