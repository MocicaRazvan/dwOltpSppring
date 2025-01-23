package com.mocicarazvan.dwoltp.controllers;


import com.mocicarazvan.dwoltp.dtos.body.CofetarBody;
import com.mocicarazvan.dwoltp.models.Cofetar;
import com.mocicarazvan.dwoltp.services.CofetarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cofetar")
@RequiredArgsConstructor
public class CofetarController {

    private final CofetarService cofetarService;

    @GetMapping("/{id}")
    public ResponseEntity<Cofetar> getCofetarById(@PathVariable Long id) {
        return ResponseEntity.ok(cofetarService.getById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Cofetar> createCofetar(@Valid @RequestBody CofetarBody body) {
        return ResponseEntity.ok(cofetarService.create(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cofetar> updateCofetar(@PathVariable Long id, @Valid @RequestBody CofetarBody body) {
        return ResponseEntity.ok(cofetarService.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCofetar(@PathVariable Long id) {
        cofetarService.deleteById(id);
        return ResponseEntity.noContent()
                .build();
    }

    @GetMapping
    public ResponseEntity<Page<Cofetar>> getCofetari(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "null") String sortField,
            @RequestParam(defaultValue = "true") Boolean ascending,
            @RequestParam(required = false, defaultValue = "") String numeQuery,
            @RequestParam(required = false, defaultValue = "") String prenumeQuery,
            @RequestParam(required = false, defaultValue = "-1") Long cofetarieId,
            @RequestParam(required = false, defaultValue = "") String specializareQuery,
            @RequestParam(required = false, defaultValue = "") String emailQuery
    ) {
        return ResponseEntity.ok(cofetarService.getPageable(page, size, sortField, ascending, numeQuery, prenumeQuery, emailQuery, cofetarieId, specializareQuery));
    }
}
