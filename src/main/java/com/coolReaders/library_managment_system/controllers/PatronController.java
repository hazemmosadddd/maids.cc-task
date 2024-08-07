package com.coolReaders.library_managment_system.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.coolReaders.library_managment_system.models.Patron;
import com.coolReaders.library_managment_system.services.PatronService;
import com.coolReaders.library_managment_system.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/patrons")
public class PatronController {

    @Autowired
    private PatronService patronService;

    @PostMapping
    public ResponseEntity<ApiResponse<Patron>> addPatron(@Valid @RequestBody Patron patron) {
        Patron savedPatron = patronService.savePatron(patron);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("Patron created successfully", savedPatron));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Patron>>> getPatrons() {
        List<Patron> patrons = patronService.getPatrons();
        String message = patrons.isEmpty() ? "No patrons found." : "Patrons retrieved successfully.";
        return ResponseEntity.ok(new ApiResponse<>(message, patrons));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Patron>> getPatronById(@PathVariable Long id) {
        Patron patron = patronService.getPatronById(id);
        return ResponseEntity.ok(new ApiResponse<>("Patron retrieved successfully.", patron));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Patron>> updatePatronById(@PathVariable Long id, @RequestBody Patron patronUpdateBody) {
        Patron updatedPatron = patronService.updatePatronById(id, patronUpdateBody);
        return ResponseEntity.ok(new ApiResponse<>("Patron updated successfully.", updatedPatron));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> deletePatronById(@PathVariable Long id) {
        patronService.deletePatronById(id);
        return ResponseEntity.ok(new ApiResponse<>("Patron with id: " + id + " deleted successfully.", null));
    }
}
