package com.leo.springbootadvanced.web;


import com.leo.springbootadvanced.domain.User;
import com.leo.springbootadvanced.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String registerPage(){
        return "register";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }


    @PostMapping("/register")
    public String register(@RequestParam String userName,
                               @RequestParam String password,
                               @RequestParam String email,
                               @RequestParam Integer phone){

        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);
        userRepository.save(user);

        return "redirect:/login";
    }


}
