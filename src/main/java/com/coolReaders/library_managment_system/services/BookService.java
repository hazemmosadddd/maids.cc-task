package com.coolReaders.library_managment_system.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.coolReaders.library_managment_system.exceptions.definedexceptions.ResourceNotFoundException;
import com.coolReaders.library_managment_system.models.Book;
import com.coolReaders.library_managment_system.repositories.BookRepository;
import com.coolReaders.library_managment_system.responses.ApiResponse;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository ; 
    
    public Book saveBook(Book book ) 
    {
        // NOTE: If any error happened in the process of adding new Book 
        // it will  be handled gracefully in the global exception handler 
        Book savedBook = bookRepository.save(book); 
        return savedBook ; 
    }

    public List<Book> getBooks ()
    {
        return  bookRepository.findAll() ; 
    }
    public Book getBookById(Long id)
    {
        Optional<Book> bookOpt = bookRepository.findById(id); 
        if(!bookOpt.isPresent())
            throw new ResourceNotFoundException("Book Dont Exist") ; 
        
        Book book = bookOpt.get() ; 
        return Book ; 

    }

    

    
}
