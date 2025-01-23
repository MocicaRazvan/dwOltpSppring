package com.mocicarazvan.dwoltp.repositories;

import com.mocicarazvan.dwoltp.models.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsAllByEmailAndIdNot(String email, Long id);

    boolean existsAllByEmail(String email);

    @Query("""
                                select c from Client c
                                where
                                    (:nume is null or c.nume ilike %:nume%)
                                    and (:prenume is null or c.prenume ilike %:prenume%)
                                    and (:email is null or c.email ilike %:email%)
            """)
    Page<Client> findAllByCustom(String nume, String prenume, String email, Pageable pageable);
}