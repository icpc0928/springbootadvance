package com.leo.springbootadvanced.handler;

import com.leo.springbootadvanced.web.BookController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice //全局的Controller的攔截器
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 異常處理
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler({Exception.class})//可指定處理部分異常,這裡使用全局異常都進來這裡處裡
//    @ResponseStatus(HttpStatus.NOT_FOUND)   //也可以轉為指定的代碼 NOTFOUND等
    public ModelAndView handlerException(HttpServletRequest request, Exception e) throws Exception {
        logger.error("Request URL: {} , Exception: {}", request.getRequestURL(), e.getMessage());

        //排除我們自訂ResponseStatus的異常處理 其餘的才會導向error.html
        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null){
            throw e;
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("url", request.getRequestURL());
        mav.addObject("exception", e);
        mav.setViewName("error/error"); //路徑
        return mav;
    }
}
