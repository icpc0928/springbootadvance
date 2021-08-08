package com.leo.springbootadvanced.web;


import com.leo.springbootadvanced.domain.User;
import com.leo.springbootadvanced.domain.UserRepository;
import com.leo.springbootadvanced.form.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String registerPage(Model model){
        model.addAttribute("userForm", new UserForm()); //讓前端能夠接到 th:object=${"userForm"}
        return "register";
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }


    @PostMapping("/register")  //Valid表示指定為這個東西需要先經過驗證 BindingResult為驗證結果
    public String register(@Valid UserForm userForm, BindingResult result){

        //檢查確認密碼
        if(!userForm.confirmPassword()){  //Field       Code               DefaultMessage
            result.rejectValue("confirmPassword", "confirmError", "與密碼不相符");
        }
        //驗證結果錯誤 (包含上面驗證確認密碼)
        if(result.hasErrors()){
            return"register";
        }

        //定義一個Interface 在UserForm內新增內部類別實作, 然後再包裝成一個方法在
        User user = userForm.convertToUser();
        userRepository.save(user);
        return "redirect:/login";
    }


    @GetMapping("/exception")
    public String testException(){
        throw new RuntimeException("測試異常處理");
    }



}
