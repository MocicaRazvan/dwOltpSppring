package com.mocicarazvan.dwoltp.repositories;

import com.mocicarazvan.dwoltp.models.Cofetar;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CofetarRepository extends JpaRepository<Cofetar, Long> {
    @Query("""
                    select c from Cofetar c
                    where
                        (:nume is null or c.angajat.nume ilike %:nume%)
                        and (:prenume is null or c.angajat.prenume ilike %:prenume%)
                        and (:cofetarieId = -1 or c.angajat.cofetarie.id = :cofetarieId)
                        and (:specializareQuery is null or c.specializare ilike %:specializareQuery%) 
                        and (:email is null or c.angajat.email ilike %:email%)
            """)
    Page<Cofetar> findAllByCustom(String nume, String prenume, String email, Long cofetarieId, String specializareQuery, Pageable pageable);

}