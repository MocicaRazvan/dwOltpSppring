package com.mocicarazvan.dwoltp.controllers;


import com.mocicarazvan.dwoltp.models.Judet;
import com.mocicarazvan.dwoltp.services.GetJudet;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/judet")
public class JudetController {
    private final GetJudet getJudet;

    @GetMapping("/{id}")
    public ResponseEntity<Judet> getJudetById(@PathVariable Long id) {
        return ResponseEntity.ok(getJudet.getModelById(id));
    }

    @GetMapping
    public ResponseEntity<Iterable<Judet>> getJudete(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "null") String sortField,
            @RequestParam(defaultValue = "true") Boolean ascending

    ) {
        return ResponseEntity.ok(getJudet.getModelsPage(page, size, sortField, ascending));
    }
}
