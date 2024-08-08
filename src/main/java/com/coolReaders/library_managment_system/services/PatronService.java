package com.coolReaders.library_managment_system.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.coolReaders.library_managment_system.models.Patron;
import com.coolReaders.library_managment_system.repositories.PatronRepository;
import com.coolReaders.library_managment_system.exceptions.definedexceptions.ResourceNotFoundException;

@Service
public class PatronService {

    @Autowired
    private PatronRepository patronRepository;

    public Patron savePatron(Patron patron) {
        return patronRepository.save(patron);
    }

    public List<Patron> getPatrons() {
        return patronRepository.findAll();
    }

    public Patron getPatronById(Long id) {
        return patronRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Patron does not exist."));
    }

    public Patron updatePatronById(Long id, Patron patronUpdateBody) {
        Patron patron = getPatronById(id);
        patron.setName(patronUpdateBody.getName());
        patron.setEmail(patronUpdateBody.getEmail());
        return patronRepository.save(patron);
    }

    public void deletePatronById(Long id) {
        Patron patron = getPatronById(id);
        patronRepository.delete(patron);
    }
}
