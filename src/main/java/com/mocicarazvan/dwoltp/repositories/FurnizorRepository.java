package com.mocicarazvan.dwoltp.repositories;

import com.mocicarazvan.dwoltp.models.Furnizor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FurnizorRepository extends JpaRepository<Furnizor, Long> {
    boolean existsAllByNume(String nume);

    boolean existsAllByNumeAndIdNot(String nume, Long id);


    @Query("""
                                select f from Furnizor f
                                where
                                    (:nume is null or f.nume ilike %:nume%)
                                    and (:rep is null or f.rep ilike %:rep%)
            """)
    Page<Furnizor> findCustom(String nume, String rep, Pageable pageable);
}