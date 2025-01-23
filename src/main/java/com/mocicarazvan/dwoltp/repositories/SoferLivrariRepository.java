package com.mocicarazvan.dwoltp.repositories;

import com.mocicarazvan.dwoltp.models.SoferLivrari;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SoferLivrariRepository extends JpaRepository<SoferLivrari, Long> {
    @Query("""
                    select s from SoferLivrari s
                    where
                        (:nume is null or s.angajat.nume ilike %:nume%)
                        and (:prenume is null or s.angajat.prenume ilike %:prenume%)
                        and (:cofetarieId = -1 or s.angajat.cofetarie.id = :cofetarieId)
                        and (:nrLivrariZiMin = -1 or s.nrLivrariZi >= :nrLivrariZiMin)
                        and (:nrLivrariZiMax = -1 or s.nrLivrariZi <= :nrLivrariZiMax)
                        and (:email is null or s.angajat.email ilike %:email%)
            """)
    Page<SoferLivrari> findAllByCustom(String nume, String prenume, String email, Long cofetarieId, Short nrLivrariZiMin, Short nrLivrariZiMax, Pageable pageable);
}