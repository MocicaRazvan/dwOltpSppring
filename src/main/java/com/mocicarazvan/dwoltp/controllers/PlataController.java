package com.mocicarazvan.dwoltp.controllers;


import com.mocicarazvan.dwoltp.dtos.body.PlataBody;
import com.mocicarazvan.dwoltp.enums.PlataTip;
import com.mocicarazvan.dwoltp.models.Plata;
import com.mocicarazvan.dwoltp.services.PlataService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/plata")
@RequiredArgsConstructor
public class PlataController {

    private final PlataService plataService;

    @GetMapping("/{id}")
    public ResponseEntity<Plata> getPlataById(@PathVariable Long id) {
        return ResponseEntity.ok(plataService.getById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Plata> createPlata(@Valid @RequestBody PlataBody body) {
        return ResponseEntity.ok(plataService.create(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plata> updatePlata(@PathVariable Long id, @Valid @RequestBody PlataBody body) {
        return ResponseEntity.ok(plataService.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlata(@PathVariable Long id) {
        plataService.deleteById(id);
        return ResponseEntity.noContent()
                .build();
    }

    @GetMapping
    public ResponseEntity<Page<Plata>> getPlati(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "null") String sortField,
            @RequestParam(defaultValue = "true") Boolean ascending,
            @RequestParam(required = false, defaultValue = "-1") Long chelnerId,
            @RequestParam(required = false, defaultValue = "-1") Long comandaId,
            @RequestParam(required = false, defaultValue = "-1") Long clientId,
            @RequestParam(required = false, defaultValue = "-1") Long cofetarieId,
            @RequestParam(required = false) Double minSuma,
            @RequestParam(required = false) Double maxSuma,
            @RequestParam(required = false) LocalDate minDataPlata,
            @RequestParam(required = false) LocalDate maxDataPlata,
            @RequestParam(required = false) PlataTip tip
    ) {
        return ResponseEntity.ok(plataService.getPageable(page, size, sortField, ascending, chelnerId, comandaId, clientId, cofetarieId, minSuma, maxSuma, minDataPlata, maxDataPlata, tip));
    }
}
