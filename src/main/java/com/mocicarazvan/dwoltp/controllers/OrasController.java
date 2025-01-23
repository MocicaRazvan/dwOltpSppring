package com.mocicarazvan.dwoltp.controllers;


import com.mocicarazvan.dwoltp.models.Oras;
import com.mocicarazvan.dwoltp.services.GetOras;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oras")
public class OrasController {
    private final GetOras getOras;

    @GetMapping("/{id}")
    public ResponseEntity<Oras> getOrasById(@PathVariable Long id) {
        return ResponseEntity.ok(getOras.getModelById(id));
    }

    @GetMapping
    public ResponseEntity<Iterable<Oras>> getOrase(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "null") String sortField,
            @RequestParam(defaultValue = "true") Boolean ascending

    ) {
        return ResponseEntity.ok(getOras.getModelsPage(page, size, sortField, ascending));
    }
}
