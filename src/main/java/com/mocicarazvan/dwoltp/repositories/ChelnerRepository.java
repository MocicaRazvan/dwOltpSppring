package com.mocicarazvan.dwoltp.repositories;

import com.mocicarazvan.dwoltp.models.Chelner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChelnerRepository extends JpaRepository<Chelner, Long> {
    @Query("""
                    select c from Chelner c
                    where
                        (:nume is null or c.angajat.nume ilike %:nume%)
                        and (:prenume is null or c.angajat.prenume ilike %:prenume%)
                        and (:cofetarieId = -1 or c.angajat.cofetarie.id = :cofetarieId)
                        and (:ziVanzator = -1 or c.ziVanzator = :ziVanzator)
                        and (:email is null or c.angajat.email ilike %:email%)
            """)
    Page<Chelner> findAllByCustom(String nume, String prenume, String email, Long cofetarieId, Short ziVanzator, Pageable pageable);
}