package com.mocicarazvan.dwoltp.controllers;


import com.mocicarazvan.dwoltp.dtos.body.CofetarieBody;
import com.mocicarazvan.dwoltp.enums.CofetarieTip;
import com.mocicarazvan.dwoltp.models.Cofetarie;
import com.mocicarazvan.dwoltp.services.CofetarieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cofeatrie")
@RequiredArgsConstructor
public class CofeatrieController {

    private final CofetarieService cofetarieService;

    @GetMapping("/{id}")
    public ResponseEntity<Cofetarie> getCofetarieById(@PathVariable Long id) {
        return ResponseEntity.ok(cofetarieService.getById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Cofetarie> createCofetarie(@Valid @RequestBody CofetarieBody body) {
        return ResponseEntity.ok(cofetarieService.create(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cofetarie> updateCofetarie(@PathVariable Long id, @Valid @RequestBody CofetarieBody body) {
        return ResponseEntity.ok(cofetarieService.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCofetarie(@PathVariable Long id) {
        cofetarieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<Cofetarie>> getCofetarii(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "null") String sortField,
            @RequestParam(defaultValue = "true") Boolean ascending,
            @RequestParam(required = false, defaultValue = "") String numeQuery,
            @RequestParam(required = false, defaultValue = "") String locatieNumeQuery,
            @RequestParam(required = false) CofetarieTip tip,
            @RequestParam(required = false, defaultValue = "-1") Long locatieId
    ) {
        return ResponseEntity.ok(cofetarieService.getPageable(page, size, sortField, ascending, numeQuery, locatieNumeQuery, tip, locatieId));
    }
}
