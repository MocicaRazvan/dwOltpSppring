package com.mocicarazvan.dwoltp.controllers;


import com.mocicarazvan.dwoltp.dtos.body.FurnizorBody;
import com.mocicarazvan.dwoltp.models.Furnizor;
import com.mocicarazvan.dwoltp.services.FurnizorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/furnizor")
@RequiredArgsConstructor
public class FurnizorController {

    private final FurnizorService furnizorService;

    @GetMapping("/{id}")
    public ResponseEntity<Furnizor> getFurnizorById(@PathVariable Long id) {
        return ResponseEntity.ok(furnizorService.getById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Furnizor> createFurnizor(@Valid @RequestBody FurnizorBody body) {
        return ResponseEntity.ok(furnizorService.create(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Furnizor> updateFurnizor(@PathVariable Long id, @Valid @RequestBody FurnizorBody body) {
        return ResponseEntity.ok(furnizorService.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFurnizor(@PathVariable Long id) {
        furnizorService.deleteById(id);
        return ResponseEntity.noContent()
                .build();
    }

    @GetMapping
    public ResponseEntity<Page<Furnizor>> getFurnizori(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "null") String sortField,
            @RequestParam(defaultValue = "true") Boolean ascending,
            @RequestParam(required = false, defaultValue = "") String numeQuery,
            @RequestParam(required = false, defaultValue = "") String repQuery
    ) {
        return ResponseEntity.ok(furnizorService.getPageable(page, size, sortField, ascending, numeQuery, repQuery));
    }
}
