package com.coolReaders.library_managment_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LibraryManagmentSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagmentSystemApplication.class, args);
	}

}
