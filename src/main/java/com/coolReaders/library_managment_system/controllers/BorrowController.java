package com.coolReaders.library_managment_system.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coolReaders.library_managment_system.exceptions.definedexceptions.ResourceNotFoundException;
import com.coolReaders.library_managment_system.models.Book;
import com.coolReaders.library_managment_system.models.Patron;
import com.coolReaders.library_managment_system.repositories.BookRepository;
import com.coolReaders.library_managment_system.repositories.PatronRepository;
import com.coolReaders.library_managment_system.responses.ApiResponse;
import com.coolReaders.library_managment_system.services.BorrowService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/borrow")
public class BorrowController {

    @Autowired
    private BorrowService borrowService  ; 

    @PostMapping("/{bookId}/borrow/{patronId}")
    public ResponseEntity<ApiResponse<Book>> borrowABook(@PathVariable long patronId, @PathVariable long bookId) {
        Book borrowedBook = borrowService.borrowABook(patronId , bookId) ; 
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Borrowed succesfuly", borrowedBook)) ;   
    }

    // the return is just remove the record and decresase
    
    @PostMapping("/{bookId}/return/{patronId}")
        public ResponseEntity<ApiResponse<Book>> returnABook(@PathVariable long patronId, @PathVariable long bookId) {
    Book returnedBook = borrowService.returnABook(patronId, bookId);
    return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse<>("Returned successfully", returnedBook));
}

    





}