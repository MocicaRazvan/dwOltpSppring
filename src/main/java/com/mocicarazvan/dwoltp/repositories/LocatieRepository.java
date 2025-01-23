package com.mocicarazvan.dwoltp.repositories;

import com.mocicarazvan.dwoltp.models.Locatie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocatieRepository extends JpaRepository<Locatie, Long> {
    Page<Locatie> findAllByNumeStradaContainingIgnoreCase(String numeStrada, Pageable pageable);

    Page<Locatie> findAllByNumeStradaContainingIgnoreCaseAndOras_Id(String numeStrada, Long orasId, Pageable pageable);

    Page<Locatie> findAllByNumeStradaContainingIgnoreCaseAndOras_Judet_Id(String numeStrada, Long orasJudetId, Pageable pageable);

    Page<Locatie> findAllByNumeStradaContainingIgnoreCaseAndOrasJudetZona_Id(String numeStrada, Long orasJudetZonaId, Pageable pageable);

}