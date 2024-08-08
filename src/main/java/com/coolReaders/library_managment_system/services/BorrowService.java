package com.coolReaders.library_managment_system.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coolReaders.library_managment_system.exceptions.definedexceptions.ResourceNotFoundException;
import com.coolReaders.library_managment_system.models.Book;
import com.coolReaders.library_managment_system.models.Patron;
import com.coolReaders.library_managment_system.repositories.BookRepository;
import com.coolReaders.library_managment_system.repositories.PatronRepository;

@Service
public class BorrowService {

    @Autowired
    BookRepository bookRepository ; 

    @Autowired 
    PatronRepository patronRepository ; 


    public Book borrowABook(Long patronId , long bookId)
    {
        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new ResourceNotFoundException("Patron does not exist"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book does not exist"));

        if (book.getBorrowedCopies() >= book.getTotalCopies()) {
            throw new IllegalStateException("No more copies of the book are available for borrowing.");
        }

        if (patron.getBooks().contains(book)) {
            // WE WILL NOT KEEP TRACK OF THE FULL HISTORY OF BOROWING 
            // SO ONCE THE PATRON RETURNS THE BOOK WE WILL DELETE THE RECORD 
            throw new IllegalStateException("This patron has already borrowed this book.");
        }
        
        book.getPatrons().add(patron);
        book.setBorrowedCopies(book.getBorrowedCopies() + 1);
        // this will update the book table and the borrows table ? 
       return  bookRepository.save(book);
    }

    public Book returnABook(Long patronId, long bookId) {
        Patron patron = patronRepository.findById(patronId)
                .orElseThrow(() -> new ResourceNotFoundException("Patron does not exist"));
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book does not exist"));
    
        if (!book.getPatrons().contains(patron)) {
            throw new IllegalStateException("This patron does not have this book borrowed.");
        }
    
        book.getPatrons().remove(patron); // Remove the patron from the book's patrons list
        book.setBorrowedCopies(book.getBorrowedCopies() - 1); // Decrement the borrowed copies count
    
        // Assuming a Cascade.ALL or appropriate cascading on the many-to-many relationship,
        // saving the book should update all associated changes in the join table and book entity.
        return bookRepository.save(book);
    }
    
}