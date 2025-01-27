package com.mocicarazvan.dwoltp.repositories;

import com.mocicarazvan.dwoltp.models.Angajat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AngajatRepository extends JpaRepository<Angajat, Long> {
    boolean existsAllByEmail(String email);

    boolean existsAllByTelefon(String telefon);

    boolean existsAllByEmailAndIdNot(String email, Long id);

    boolean existsAllByTelefonAndIdNot(String telefon, Long id);

    @Query("""
                    select a from Angajat a
                    where
                        (:nume is null or a.nume ilike %:nume%)
                        and (:prenume is null or a.prenume ilike %:prenume%)
                        and (:cofetarieId = -1 or a.cofetarie.id = :cofetarieId)
                        and (:email is null or a.email ilike %:email%)
            """)
    Page<Angajat> findAllByCustom(String nume, String prenume, String email, Long cofetarieId, Pageable pageable);
}