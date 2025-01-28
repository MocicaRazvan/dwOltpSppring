package com.mocicarazvan.dwoltp.controllers;


import com.mocicarazvan.dwoltp.dtos.body.ProdusBody;
import com.mocicarazvan.dwoltp.dtos.repositories.ProdusPromotie;
import com.mocicarazvan.dwoltp.enums.ProdusTip;
import com.mocicarazvan.dwoltp.models.Produs;
import com.mocicarazvan.dwoltp.services.ProdusService;
import com.mocicarazvan.dwoltp.utils.SearchStringUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/produs")
@RequiredArgsConstructor
public class ProdusController {

    private final ProdusService produsService;

    @GetMapping("/{id}")
    public ResponseEntity<Produs> getProdusById(@PathVariable Long id) {
        return ResponseEntity.ok(produsService.getById(id));
    }

    @GetMapping("/promotie/{id}")
    public ResponseEntity<ProdusPromotie> getProdusPromotieById(@PathVariable Long id,
                                                                @RequestParam(defaultValue = "2022-12-01") LocalDate perioadaStart,
                                                                @RequestParam(defaultValue = "#{T(java.time.LocalDate).now()}") LocalDate perioadaFinal
    ) {

        return ResponseEntity.ok(produsService.getProdusWithPromotie(id, perioadaStart, perioadaFinal));
    }

    @PostMapping("/create")
    public ResponseEntity<Produs> createProdus(@Valid @RequestBody ProdusBody body) {
        return ResponseEntity.ok(produsService.create(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produs> updateProdus(@PathVariable Long id, @Valid @RequestBody ProdusBody body) {
        return ResponseEntity.ok(produsService.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProdus(@PathVariable Long id) {
        produsService.deleteById(id);
        return ResponseEntity.noContent()
                .build();
    }

    @GetMapping
    public ResponseEntity<Page<Produs>> getProduse(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "null") String sortField,
            @RequestParam(defaultValue = "true") Boolean ascending,
            @RequestParam(required = false, defaultValue = "") String numeQuery,
            @RequestParam(required = false) BigDecimal pretMin,
            @RequestParam(required = false) BigDecimal pretMax,
            @RequestParam(required = false) ProdusTip tip,
            @RequestParam(required = false) Double gramajMin,
            @RequestParam(required = false) Double gramajMax,
            @RequestParam(required = false) List<Long> ingedienteIds
    ) {
        return ResponseEntity.ok(produsService.getPageable(page, size, sortField, ascending, numeQuery, pretMin, pretMax, tip, gramajMin, gramajMax, ingedienteIds));
    }

    @GetMapping("/promotie")
    public ResponseEntity<Page<ProdusPromotie>> getProduseWithPromotie(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "null") String sortField,
            @RequestParam(defaultValue = "true") Boolean ascending,
            @RequestParam(required = false, defaultValue = "") String numeQuery,
            @RequestParam(required = false) BigDecimal pretMin,
            @RequestParam(required = false) BigDecimal pretMax,
            @RequestParam(required = false) ProdusTip tip,
            @RequestParam(required = false) Double gramajMin,
            @RequestParam(required = false) Double gramajMax,
            @RequestParam(required = false) List<Long> ingedienteIds,
            @RequestParam(defaultValue = "2022-12-01") LocalDate perioadaStart,
            @RequestParam(defaultValue = "#{T(java.time.LocalDate).now()}") LocalDate perioadaFinal

    ) {
        return ResponseEntity.ok(produsService.getPageableWithPromotie(page, size, sortField, ascending,
                SearchStringUtils.cleanSearchQuery(numeQuery), pretMin, pretMax, tip, gramajMin, gramajMax, ingedienteIds, perioadaStart, perioadaFinal));
    }
}
