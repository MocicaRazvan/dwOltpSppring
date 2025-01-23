package com.mocicarazvan.dwoltp.repositories;

import com.mocicarazvan.dwoltp.models.Zona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZonaRepository extends JpaRepository<Zona, Long> {
    Page<Zona> findAllByNumeContainingIgnoreCaseOrIsoContainingIgnoreCase(String nume, String iso, Pageable pageRequest);


    Page<Zona> findAllByNumeContainingIgnoreCase(String nume, Pageable pageRequest);

    Page<Zona> findAllByIsoContainingIgnoreCase(String iso, Pageable pageRequest);
}