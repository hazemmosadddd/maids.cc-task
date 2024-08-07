package com.coolReaders.library_managment_system.models;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;;

@Entity

@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ; 

    @NotNull(message = "Title cannot be blank")
    private String title ; 

    @NotNull(message = "Author cannot be null")
    private String author ; 
    

    @NotNull(message = "ISBN canot be null")
    @Size(min = 10, max = 13, message = "ISBN must be between 10 and 13 characters")
    private String isbn; 

    @ManyToMany
    @JoinTable(
        name = "borrows" , 
        joinColumns = @JoinColumn(name = "book_id") , 
        inverseJoinColumns = @JoinColumn(name = "patron_id")
    )
    private List<Patron> patrons;




    private int publicationYear ; 

      public Book() {
    }

    public Book(String title, String author, Integer publicationYear, String isbn) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }
    public long getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }
    public int getPublicationYear() {
        return publicationYear;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Book{" +
               "id=" + id +
               ", title='" + title + '\'' +
               ", author='" + author + '\'' +
               ", publicationYear=" + publicationYear +
               ", isbn='" + isbn + '\'' +
               '}';
    }

    
}
