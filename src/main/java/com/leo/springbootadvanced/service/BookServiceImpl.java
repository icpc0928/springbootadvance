package com.leo.springbootadvanced.service;


import com.leo.springbootadvanced.domain.Book;
import com.leo.springbootadvanced.domain.BookRepository;
import com.leo.springbootadvanced.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    /**
     * 獲取一個書單信息
     * @param id
     * @return
     * @throws BookNotFoundException
     */
    @Override
    public Book getBookById(Long id) throws BookNotFoundException{
        Book book;
        try {
            book = bookRepository.findById(id).get();
        }catch(NoSuchElementException e){
            throw new BookNotFoundException("書單信息不存在");
        }

        return book;
    }
}
