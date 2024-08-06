package com.coolReaders.library_managment_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coolReaders.library_managment_system.models.Book;

public interface BookRepository extends JpaRepository<Book , Long > {
    
}
