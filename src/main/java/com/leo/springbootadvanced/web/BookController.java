package com.leo.springbootadvanced.web;

import com.leo.springbootadvanced.domain.Book;
import com.leo.springbootadvanced.exception.BookNotFoundException;
import com.leo.springbootadvanced.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;


    @GetMapping("/{id}")
    public String getBook(@PathVariable Long id, Model model){
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "book";
    }
}
