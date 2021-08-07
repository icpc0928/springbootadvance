package com.leo.springbootadvanced.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//拋錯,Response 404   (可以Google 常用HTTP狀態碼 自行定義拋錯之後要轉發的錯誤頁面)
@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException() {
    }

    public BookNotFoundException(String message) {
        super(message);
    }

    public BookNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
