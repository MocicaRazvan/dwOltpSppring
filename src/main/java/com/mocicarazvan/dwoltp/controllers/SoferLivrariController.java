package com.mocicarazvan.dwoltp.controllers;


import com.mocicarazvan.dwoltp.dtos.body.SoferLivrariBody;
import com.mocicarazvan.dwoltp.models.SoferLivrari;
import com.mocicarazvan.dwoltp.services.SoferLivrariService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sofer-livrari")
@RequiredArgsConstructor
public class SoferLivrariController {

    private final SoferLivrariService soferLivrariService;

    @GetMapping("/{id}")
    public ResponseEntity<SoferLivrari> getSoferLivrariById(@PathVariable Long id) {
        return ResponseEntity.ok(soferLivrariService.getById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<SoferLivrari> createSoferLivrari(@Valid @RequestBody SoferLivrariBody body) {
        return ResponseEntity.ok(soferLivrariService.create(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SoferLivrari> updateSoferLivrari(@PathVariable Long id, @Valid @RequestBody SoferLivrariBody body) {
        return ResponseEntity.ok(soferLivrariService.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSoferLivrari(@PathVariable Long id) {
        soferLivrariService.deleteById(id);
        return ResponseEntity.noContent()
                .build();
    }

    @GetMapping
    public ResponseEntity<Page<SoferLivrari>> getSoferi(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "null") String sortField,
            @RequestParam(defaultValue = "true") Boolean ascending,
            @RequestParam(required = false, defaultValue = "") String numeQuery,
            @RequestParam(required = false, defaultValue = "") String prenumeQuery,
            @RequestParam(required = false, defaultValue = "-1") Long cofetarieId,
            @RequestParam(required = false, defaultValue = "-1") Short nrLivrariZiMin,
            @RequestParam(required = false, defaultValue = "-1") Short nrLivrariZiMax,
            @RequestParam(required = false, defaultValue = "") String emailQuery
    ) {
        return ResponseEntity.ok(soferLivrariService.getPageable(page, size, sortField, ascending, numeQuery, prenumeQuery, emailQuery, cofetarieId, nrLivrariZiMin, nrLivrariZiMax));
    }
}
