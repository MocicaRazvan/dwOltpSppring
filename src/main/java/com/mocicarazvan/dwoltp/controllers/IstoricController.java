package com.mocicarazvan.dwoltp.controllers;


import com.mocicarazvan.dwoltp.dtos.body.IstoricBody;
import com.mocicarazvan.dwoltp.models.Istoric;
import com.mocicarazvan.dwoltp.models.composedIds.IstoricId;
import com.mocicarazvan.dwoltp.services.IstoricService;
import com.mocicarazvan.dwoltp.utils.SearchStringUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/istoric")
@RequiredArgsConstructor
public class IstoricController {

    private final IstoricService istoricService;

    @GetMapping("/{angajatId}/{dataAngajareStart}")
    public ResponseEntity<Istoric> getIstoricById(@PathVariable Long angajatId, @PathVariable LocalDate dataAngajareStart) {
        return ResponseEntity.ok(istoricService.getById(new IstoricId(angajatId, dataAngajareStart)));
    }

    @PostMapping("/create")
    public ResponseEntity<Istoric> createIstoric(@Valid @RequestBody IstoricBody body) {
        return ResponseEntity.ok(istoricService.create(body));
    }

    @PutMapping("/{angajatId}/{dataAngajareStart}")
    public ResponseEntity<Istoric> updateIstoric(@PathVariable Long angajatId, @PathVariable LocalDate dataAngajareStart, @Valid @RequestBody IstoricBody body) {
        return ResponseEntity.ok(istoricService.update(
                new IstoricId(angajatId, dataAngajareStart)
                , body));
    }

    @DeleteMapping("/{angajatId}/{dataAngajareStart}")
    public ResponseEntity<Void> deleteIstoric(@PathVariable Long angajatId, @PathVariable LocalDate dataAngajareStart) {
        istoricService.deleteById(new IstoricId(angajatId, dataAngajareStart));
        return ResponseEntity.noContent()
                .build();
    }

    @GetMapping
    public ResponseEntity<Page<Istoric>> getIstorice(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "null") String sortField,
            @RequestParam(defaultValue = "true") Boolean ascending,
            @RequestParam(required = false, defaultValue = "-1") Long angajatId,
            @RequestParam(required = false, defaultValue = "-1") Long cofeatrieId,
            @RequestParam(required = false, defaultValue = "") String numeQuery,
            @RequestParam(required = false, defaultValue = "") String prenumeQuery,
            @RequestParam(required = false) Boolean isDataAngajareEndNull,
            @RequestParam(required = false) LocalDate dataAngajareStartMin,
            @RequestParam(required = false) LocalDate dataAngajareStartMax,
            @RequestParam(required = false) LocalDate dataAngajareEndMin,
            @RequestParam(required = false) LocalDate dataAngajareEndMax,
            @RequestParam(required = false) BigDecimal salariuMin,
            @RequestParam(required = false) BigDecimal salariuMax
    ) {
        return ResponseEntity.ok(istoricService.getPageable(page, size, sortField, ascending, angajatId, cofeatrieId,
                SearchStringUtils.cleanSearchQuery(numeQuery),
                SearchStringUtils.cleanSearchQuery(prenumeQuery), isDataAngajareEndNull, dataAngajareStartMin, dataAngajareStartMax, dataAngajareEndMin, dataAngajareEndMax, salariuMin, salariuMax));
    }
}
