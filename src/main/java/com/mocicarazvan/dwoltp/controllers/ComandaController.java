package com.mocicarazvan.dwoltp.controllers;


import com.mocicarazvan.dwoltp.dtos.body.ComandaBody;
import com.mocicarazvan.dwoltp.enums.PlataTip;
import com.mocicarazvan.dwoltp.models.Comanda;
import com.mocicarazvan.dwoltp.services.ComandaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/comanda")
@RequiredArgsConstructor
public class ComandaController {

    private final ComandaService comandaService;

    @GetMapping("/{id}")
    public ResponseEntity<Comanda> getComandaById(@PathVariable Long id) {
        return ResponseEntity.ok(comandaService.getById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Comanda> createComanda(@Valid @RequestBody ComandaBody body) {
        return ResponseEntity.ok(comandaService.create(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comanda> updateComanda(@PathVariable Long id, @Valid @RequestBody ComandaBody body) {
        return ResponseEntity.ok(comandaService.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComanda(@PathVariable Long id) {
        comandaService.deleteById(id);
        return ResponseEntity.noContent()
                .build();
    }

    @GetMapping
    public ResponseEntity<Page<Comanda>> getComenzi(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "null") String sortField,
            @RequestParam(defaultValue = "true") Boolean ascending,
            @RequestParam(required = false) Double minSuma,
            @RequestParam(required = false) Double maxSuma,
            @RequestParam(required = false) LocalDate minDataOnorare,
            @RequestParam(required = false) LocalDate maxDataOnorare,
            @RequestParam(required = false, defaultValue = "-1") Long clientId,
            @RequestParam(required = false, defaultValue = "-1") Long soferId,
            @RequestParam(required = false, defaultValue = "-1") Long nrPlati,
            @RequestParam(required = false, defaultValue = "-1") Long locatieId,
            @RequestParam(required = false) List<Long> produseIds,
            @RequestParam(required = false) List<PlataTip> tipPlati
    ) {
        return ResponseEntity.ok(comandaService.getPageable(page, size, sortField, ascending, minSuma, maxSuma, minDataOnorare, maxDataOnorare, clientId, soferId, nrPlati, locatieId, produseIds, tipPlati));
    }
}
