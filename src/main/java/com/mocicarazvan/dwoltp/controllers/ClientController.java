package com.mocicarazvan.dwoltp.controllers;


import com.mocicarazvan.dwoltp.dtos.body.ClientBody;
import com.mocicarazvan.dwoltp.models.Client;
import com.mocicarazvan.dwoltp.services.ClientService;
import com.mocicarazvan.dwoltp.utils.SearchStringUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Client> createClient(@Valid @RequestBody ClientBody body) {
        return ResponseEntity.ok(clientService.create(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @Valid @RequestBody ClientBody body) {
        return ResponseEntity.ok(clientService.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteById(id);
        return ResponseEntity.noContent()
                .build();
    }

    @GetMapping
    public ResponseEntity<Page<Client>> getClienti(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "null") String sortField,
            @RequestParam(defaultValue = "true") Boolean ascending,
            @RequestParam(required = false, defaultValue = "") String numeQuery,
            @RequestParam(required = false, defaultValue = "") String prenumeQuery,
            @RequestParam(required = false, defaultValue = "") String emailQuery
    ) {
        return ResponseEntity.ok(clientService.getPageable(page, size, sortField, ascending,
                SearchStringUtils.cleanSearchQuery(numeQuery), SearchStringUtils.cleanSearchQuery(prenumeQuery), emailQuery));
    }
}
