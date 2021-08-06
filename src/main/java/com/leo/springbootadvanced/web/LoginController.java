package com.leo.springbootadvanced.web;


import com.leo.springbootadvanced.domain.User;
import com.leo.springbootadvanced.domain.UserRepository;
import com.leo.springbootadvanced.form.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;


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


    @PostMapping("/register")  //Valid表示指定為這個東西需要先經過驗證 BindingResult為驗證結果
    public String register(@Valid UserForm userForm, BindingResult result){
        //驗證結果錯誤
        if(result.hasErrors()){
            List<FieldError> fieldErrorList = result.getFieldErrors();
            for(FieldError error : fieldErrorList){
                System.out.println(error.getField() + " : " + error.getDefaultMessage() + " : " + error.getCode());
            }
            return"register";
        }

        //定義一個Interface 在UserForm內新增內部類別實作, 然後再包裝成一個方法在
        User user = userForm.convertToUser();
        userRepository.save(user);
        return "redirect:/login";
    }



}
