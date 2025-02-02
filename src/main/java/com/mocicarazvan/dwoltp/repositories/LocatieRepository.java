package com.mocicarazvan.dwoltp.repositories;

import com.mocicarazvan.dwoltp.models.Locatie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LocatieRepository extends JpaRepository<Locatie, Long> {
    Page<Locatie> findAllByNumeStradaContainingIgnoreCase(String numeStrada, Pageable pageable);

    Page<Locatie> findAllByNumeStradaContainingIgnoreCaseAndOras_Id(String numeStrada, Long orasId, Pageable pageable);

    Page<Locatie> findAllByNumeStradaContainingIgnoreCaseAndOras_Judet_Id(String numeStrada, Long orasJudetId, Pageable pageable);

    Page<Locatie> findAllByNumeStradaContainingIgnoreCaseAndOrasJudetZona_Id(String numeStrada, Long orasJudetZonaId, Pageable pageable);


    @Query("""
                    select l from Locatie l
                    join Oras o on l.oras.id= o.id
                    join Judet j on o.judet.id = j.id
                    where
                        (:numeStradaQuery is null or l.numeStrada ilike %:numeStradaQuery%)
                        and (:orasId = -1 or o.id = :orasId)
                        and (:judetId = -1 or j.id = :judetId)
                        and (:zonaId = -1 or j.zona.id = :zonaId)
            """)
    Page<Locatie> findAllCustom(String numeStradaQuery, Long orasId, Long judetId, Long zonaId, Pageable pageable);

}