package com.coolReaders.library_managment_system.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coolReaders.library_managment_system.models.Book;
import com.coolReaders.library_managment_system.repositories.BookRepository;
import com.coolReaders.library_managment_system.responses.ApiResponse;
import com.coolReaders.library_managment_system.services.BookService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private BookService bookService ; 

    @PostMapping
    public ResponseEntity<ApiResponse<Book>> addBook(@Valid @RequestBody Book book ) 
    {
        Book savedBook = bookService.saveBook(book) ;
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("Book Created Succesfully", savedBook)) ; 
    }


    @GetMapping()
    public ResponseEntity<ApiResponse<List<Book>>> getBooks(){
        List<Book> books = bookService.getBooks();
        String message = books.isEmpty() ? "No books available." : "Books retrieved successfully.";
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>(message, books)) ;   
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Book>> getBookById(@PathVariable Long id){
        Book book = bookService.getBookById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Book retrieved successfully.", book)) ;   
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Book>> updateBookById(@PathVariable Long id , @RequestBody Book bookUpdateBody )
    {
        Book updatedBook = bookService.updateBookById(id , bookUpdateBody) ; 
        return ResponseEntity.ok(new ApiResponse<>("Book updated successfully.", updatedBook));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteBookById(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.ok(new ApiResponse<>("Book with id: " + id + " deleted successfully.", null));
    }
    

    
}
