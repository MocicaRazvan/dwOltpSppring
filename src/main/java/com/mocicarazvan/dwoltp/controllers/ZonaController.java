package com.mocicarazvan.dwoltp.controllers;

import com.mocicarazvan.dwoltp.models.Zona;
import com.mocicarazvan.dwoltp.services.GetZona;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/zona")
@RequiredArgsConstructor
public class ZonaController {

    private final GetZona getZona;

    @GetMapping("/{id}")
    public ResponseEntity<Zona> getZonaById(@PathVariable Long id) {
        return ResponseEntity.ok(getZona.getModelById(id));
    }

    @GetMapping
    public ResponseEntity<Iterable<Zona>> getZone(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "null") String sortField,
            @RequestParam(defaultValue = "true") Boolean ascending

    ) {
        return ResponseEntity.ok(getZona.getModelsPage(page, size, sortField, ascending));
    }

}
