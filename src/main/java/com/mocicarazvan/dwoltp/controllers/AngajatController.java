package com.mocicarazvan.dwoltp.controllers;


import com.mocicarazvan.dwoltp.dtos.body.AngajatBody;
import com.mocicarazvan.dwoltp.models.Angajat;
import com.mocicarazvan.dwoltp.services.AngajatService;
import com.mocicarazvan.dwoltp.utils.SearchStringUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/angajat")
@RequiredArgsConstructor
public class AngajatController {

    private final AngajatService angajatService;

    @GetMapping("/{id}")
    public ResponseEntity<Angajat> getAngajatById(@PathVariable Long id) {
        return ResponseEntity.ok(angajatService.getById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Angajat> createAngajat(@Valid @RequestBody AngajatBody body) {
        return ResponseEntity.ok(angajatService.create(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Angajat> updateAngajat(@PathVariable Long id, @Valid @RequestBody AngajatBody body) {
        return ResponseEntity.ok(angajatService.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAngajat(@PathVariable Long id) {
        angajatService.deleteById(id);
        return ResponseEntity.noContent()
                .build();
    }

    @GetMapping
    public ResponseEntity<Page<Angajat>> getAngajatti(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "null") String sortField,
            @RequestParam(defaultValue = "true") Boolean ascending,
            @RequestParam(required = false, defaultValue = "") String numeQuery,
            @RequestParam(required = false, defaultValue = "") String prenumeQuery,
            @RequestParam(required = false, defaultValue = "-1") Long cofetarieId,
            @RequestParam(required = false, defaultValue = "") String emailQuery
    ) {
        return ResponseEntity.ok(angajatService.getPageable(page, size, sortField, ascending, SearchStringUtils.cleanSearchQuery(numeQuery),
                SearchStringUtils.cleanSearchQuery(prenumeQuery), SearchStringUtils.cleanSearchQuery(emailQuery), cofetarieId));
    }
}
