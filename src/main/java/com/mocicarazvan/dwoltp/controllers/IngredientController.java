package com.mocicarazvan.dwoltp.controllers;


import com.mocicarazvan.dwoltp.dtos.body.IngredientBody;
import com.mocicarazvan.dwoltp.models.Ingredient;
import com.mocicarazvan.dwoltp.services.IngredientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")
@RequiredArgsConstructor
public class IngredientController {

    private final IngredientService ingredientService;

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable Long id) {
        return ResponseEntity.ok(ingredientService.getById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Ingredient> createIngredient(@Valid @RequestBody IngredientBody body) {
        return ResponseEntity.ok(ingredientService.create(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> updateIngredient(@PathVariable Long id, @Valid @RequestBody IngredientBody body) {
        return ResponseEntity.ok(ingredientService.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable Long id) {
        ingredientService.deleteById(id);
        return ResponseEntity.noContent()
                .build();
    }

    @GetMapping
    public ResponseEntity<Page<Ingredient>> getIngrediente(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "null") String sortField,
            @RequestParam(defaultValue = "true") Boolean ascending,
            @RequestParam(required = false, defaultValue = "") String numeQuery,
            @RequestParam(required = false, defaultValue = "-1") Short stocMin,
            @RequestParam(required = false, defaultValue = "-1") Short stocMax,
            @RequestParam(required = false, defaultValue = "-1") Short stocLunarMin,
            @RequestParam(required = false, defaultValue = "-1") Short stocLunarMax,
            @RequestParam(required = false, defaultValue = "-1") Long furnizorId
    ) {
        return ResponseEntity.ok(ingredientService.getPageable(page, size, sortField, ascending, numeQuery, stocMin, stocMax, stocLunarMin, stocLunarMax, furnizorId));
    }
}
