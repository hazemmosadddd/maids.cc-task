package com.coolReaders.library_managment_system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coolReaders.library_managment_system.models.Book;
import com.coolReaders.library_managment_system.repositories.BookRepository;



@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @PostMapping
    public Book addBook( @RequestBody Book book ) 
    {

        // System.out.println(book);
        // ok now i want to put this book in a the database 
        // so we will use a repository which have the main function for crud 
        Book x = bookRepository.save(book) ; 

        return x ; 
    }


  
    

    
}
