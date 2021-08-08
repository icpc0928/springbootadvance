package com.leo.springbootadvanced.web;

import com.leo.springbootadvanced.domain.Book;
import com.leo.springbootadvanced.exception.BookNotFoundException;
import com.leo.springbootadvanced.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;



@Controller
@RequestMapping("/books")
public class BookController {

//    private final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;


    @GetMapping("/{id}")
    public String getBook(@PathVariable Long id, Model model){
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "book";
    }

    /**
     * 異常處理
     * @param request
     * @param e
     * @return
     */
//    @ExceptionHandler({Exception.class})//可指定處理部分異常,這裡使用全局異常都進來這裡處裡
//    public ModelAndView handlerException(HttpServletRequest request, Exception e) throws Exception {
//        logger.error("Request URL: {} , Exception: {}", request.getRequestURL(), e.getMessage());
//
//        //排除我們自訂ResponseStatus的異常處理 其餘的才會導向error.html
//        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null){
//            throw e;
//        }
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("url", request.getRequestURL());
//        mav.addObject("exception", e);
//        mav.setViewName("error/error"); //路徑
//        return mav;
//    }
}
