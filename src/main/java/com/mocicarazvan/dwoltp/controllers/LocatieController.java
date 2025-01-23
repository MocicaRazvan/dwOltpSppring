package com.mocicarazvan.dwoltp.controllers;


import com.mocicarazvan.dwoltp.dtos.body.LocatieBody;
import com.mocicarazvan.dwoltp.models.Locatie;
import com.mocicarazvan.dwoltp.services.LocatieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locatie")
@RequiredArgsConstructor
public class LocatieController {

    private final LocatieService locatieService;

    @GetMapping("/{id}")
    public ResponseEntity<Locatie> getLocatieById(@PathVariable Long id) {
        return ResponseEntity.ok(locatieService.getById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Locatie> createLocatie(@Valid @RequestBody LocatieBody body) {
        return ResponseEntity.ok(locatieService.create(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Locatie> updateLocatie(@PathVariable Long id, @Valid @RequestBody LocatieBody body) {
        return ResponseEntity.ok(locatieService.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocatie(@PathVariable Long id) {
        locatieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<Locatie>> getLocatii(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "null") String sortField,
            @RequestParam(defaultValue = "true") Boolean ascending,
            @RequestParam(required = false, defaultValue = "") String numeStradaQuery,
            @RequestParam(required = false, defaultValue = "-1") Long orasId,
            @RequestParam(required = false, defaultValue = "-1") Long judetId,
            @RequestParam(required = false, defaultValue = "-1") Long zonaId
    ) {
        return ResponseEntity.ok(locatieService.getPageable(page, size, sortField, ascending, numeStradaQuery, orasId, judetId, zonaId));
    }
}
