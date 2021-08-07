package com.leo.springbootadvanced.form;

import com.leo.springbootadvanced.domain.User;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

//form資料夾內的類別用來接收前端form表單所提交的東西進來判斷
public class UserForm {

    public static final String PHONE_REG = "^09[0-9]{8}$";

    //這裡定義的名稱要跟前端參數定的名稱一致 並且跟寫進資料庫定義的名稱相同,複製屬性才會正確
    //內置constraint 見最下方註解

    @NotBlank(message = "用戶名不得為空") //該值不得為空
    private String userName;

    @Length(min = 6, message = "密碼至少需要6位數")   //長度限制
    private String password;

    @Pattern(regexp = PHONE_REG, message = "請輸入正確手機號碼(09開頭)")
    private String phone;
    @Email
    private String email;
    @NotBlank
    private String confirmPassword;

    public UserForm(){

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    //檢查密碼是否一致
    public boolean confirmPassword(){
        if(this.confirmPassword == null || this.password == null)
            return false;

        return this.password.equals(this.confirmPassword);
    }

    public User convertToUser(){
        User user = new UserFormConvert().convert(this);
        return user;
    }

    private static class UserFormConvert implements FormConvert<UserForm, User>{
        @Override
        public User convert(UserForm userForm) {
            User user = new User();
            //原數據物件 複製到 目標物件
            BeanUtils.copyProperties(userForm, user);
            return user;
        }
    }




}


/**
 *  Bean Validation 中内置的 constraint
 *  https://docs.oracle.com/javaee/7/tutorial/bean-validation001.htm
 *     @Null 被注释的元素必须为 null
 *     @NotNull 被注释的元素必须不为 null
 *     @AssertTrue 被注释的元素必须为 true
 *     @AssertFalse 被注释的元素必须为 false
 *     @Min(value) 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
 *     @Max(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
 *     @DecimalMin(value) 被注释的元素必须是一个数字，其值必须大于等于指定的最小值
 *     @DecimalMax(value) 被注释的元素必须是一个数字，其值必须小于等于指定的最大值
 *     @Size(max=, min=)   被注释的元素的大小必须在指定的范围内
 *     @Digits (integer, fraction)     被注释的元素必须是一个数字，其值必须在可接受的范围内
 *     @Past 被注释的元素必须是一个过去的日期
 *     @Future 被注释的元素必须是一个将来的日期
 *     @Pattern(regex=,flag=) 被注释的元素必须符合指定的正则表达式
 *
 *     Hibernate Validator 附加的 constraint
 *     @NotBlank(message =)   验证字符串非null，且长度必须大于0
 *     @Email 被注释的元素必须是电子邮箱地址
 *     @Length(min=,max=) 被注释的字符串的大小必须在指定的范围内
 *     @NotEmpty 被注释的字符串的必须非空
 *     @Range(min=,max=,message=) 被注释的元素必须在合适的范围内
 *
 * @NotEmpty 用在集合類上面
 * @NotBlank 用在String上面
 * @NotNull 用在基本類型上
 */
