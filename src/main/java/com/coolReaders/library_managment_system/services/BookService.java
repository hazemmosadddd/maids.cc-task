package com.coolReaders.library_managment_system.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.coolReaders.library_managment_system.exceptions.definedexceptions.ResourceNotFoundException;
import com.coolReaders.library_managment_system.models.Book;
import com.coolReaders.library_managment_system.repositories.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository ; 
    

    @CacheEvict(value = "booksCache", allEntries = true)
    public Book saveBook(Book book ) 
    {
        // NOTE: If any error happened in the process of adding new Book 
        // it will  be handled gracefully in the global exception handler 
        Book savedBook = bookRepository.save(book); 
        return savedBook ; 
    }

    @Cacheable(value = "booksCache", key = "'allBooks'")
    public List<Book> getBooks ()
    {
        return  bookRepository.findAll() ; 
    }

    @Cacheable(value = "booksCache", key = "#id")
    public Book getBookById(Long id)
    {
        Optional<Book> bookOpt = bookRepository.findById(id); 
        if(!bookOpt.isPresent())
        throw new ResourceNotFoundException("Book does not exist.");
        
        Book book = bookOpt.get() ; 
        return book ; 

    }
    public Book updateBookById(Long id , Book bookUpdateBody)
    {
        Optional<Book> bookOpt = bookRepository.findById(id) ; 
        if (!bookOpt.isPresent())
        throw new ResourceNotFoundException("Book does not exist.");

        Book newBook = bookOpt.get() ; 
        newBook.setTitle(bookUpdateBody.getTitle());
        newBook.setAuthor(bookUpdateBody.getAuthor());
        newBook.setIsbn(bookUpdateBody.getIsbn());
        newBook.setPublicationYear(bookUpdateBody.getPublicationYear());

        Book updatedBook = bookRepository.save(newBook) ; 
        
        return updatedBook ; 

    }

    @CacheEvict(value = "booksCache", allEntries = true)
    public void deleteBookById(Long id)
    {
        Optional<Book> bookOpt = bookRepository.findById(id) ; 
        if (!bookOpt.isPresent())
        throw new ResourceNotFoundException("Book does not exist.");
        Book book = bookOpt.get() ; 
        bookRepository.delete(book);
    }

    
}
