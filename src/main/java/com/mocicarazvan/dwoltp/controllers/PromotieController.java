package com.mocicarazvan.dwoltp.controllers;


import com.mocicarazvan.dwoltp.dtos.body.PromotieBody;
import com.mocicarazvan.dwoltp.models.Promotie;
import com.mocicarazvan.dwoltp.services.PromotieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/promotie")
@RequiredArgsConstructor
public class PromotieController {

    private final PromotieService furnizorService;

    @GetMapping("/{id}")
    public ResponseEntity<Promotie> getPromotieById(@PathVariable Long id) {
        return ResponseEntity.ok(furnizorService.getById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Promotie> createPromotie(@Valid @RequestBody PromotieBody body) {
        return ResponseEntity.ok(furnizorService.create(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Promotie> updatePromotie(@PathVariable Long id, @Valid @RequestBody PromotieBody body) {
        return ResponseEntity.ok(furnizorService.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePromotie(@PathVariable Long id) {
        furnizorService.deleteById(id);
        return ResponseEntity.noContent()
                .build();
    }

    @GetMapping
    public ResponseEntity<Page<Promotie>> getPromotii(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "null") String sortField,
            @RequestParam(defaultValue = "true") Boolean ascending,
            @RequestParam(required = false) LocalDate perioadaStart,
            @RequestParam(required = false) LocalDate perioadaFinal,
            @RequestParam(required = false, defaultValue = "-1") Long produsId,
            @RequestParam(required = false) BigDecimal discountMin,
            @RequestParam(required = false) BigDecimal discountMax
    ) {
        return ResponseEntity.ok(furnizorService.getPageable(page, size, sortField, ascending, perioadaStart, perioadaFinal, produsId, discountMin, discountMax));
    }
}
