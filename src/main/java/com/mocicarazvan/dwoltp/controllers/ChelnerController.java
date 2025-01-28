package com.mocicarazvan.dwoltp.controllers;


import com.mocicarazvan.dwoltp.dtos.body.ChelnerBody;
import com.mocicarazvan.dwoltp.models.Chelner;
import com.mocicarazvan.dwoltp.services.ChelnerService;
import com.mocicarazvan.dwoltp.utils.SearchStringUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chelner")
@RequiredArgsConstructor
public class ChelnerController {

    private final ChelnerService chelnerService;

    @GetMapping("/{id}")
    public ResponseEntity<Chelner> getChelnerById(@PathVariable Long id) {
        return ResponseEntity.ok(chelnerService.getById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Chelner> createChelner(@Valid @RequestBody ChelnerBody body) {
        return ResponseEntity.ok(chelnerService.create(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Chelner> updateChelner(@PathVariable Long id, @Valid @RequestBody ChelnerBody body) {
        return ResponseEntity.ok(chelnerService.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChelner(@PathVariable Long id) {
        chelnerService.deleteById(id);
        return ResponseEntity.noContent()
                .build();
    }

    @GetMapping
    public ResponseEntity<Page<Chelner>> getChelneri(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "null") String sortField,
            @RequestParam(defaultValue = "true") Boolean ascending,
            @RequestParam(required = false, defaultValue = "") String numeQuery,
            @RequestParam(required = false, defaultValue = "") String prenumeQuery,
            @RequestParam(required = false, defaultValue = "") String emailQuery,
            @RequestParam(required = false, defaultValue = "-1") Long cofetarieId,
            @RequestParam(required = false, defaultValue = "-1") Short ziVanzator
    ) {
        return ResponseEntity.ok(chelnerService.getPageable(page, size, sortField, ascending,
                SearchStringUtils.cleanSearchQuery(numeQuery), SearchStringUtils.cleanSearchQuery(prenumeQuery),
                SearchStringUtils.cleanSearchQuery(emailQuery),
                cofetarieId, ziVanzator));
    }
}
