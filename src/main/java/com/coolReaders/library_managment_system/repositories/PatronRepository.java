package com.coolReaders.library_managment_system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.coolReaders.library_managment_system.models.Patron;

public interface PatronRepository extends JpaRepository<Patron, Long> {
}
