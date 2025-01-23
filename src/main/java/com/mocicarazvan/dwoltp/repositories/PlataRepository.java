package com.mocicarazvan.dwoltp.repositories;

import com.mocicarazvan.dwoltp.enums.PlataTip;
import com.mocicarazvan.dwoltp.models.Plata;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface PlataRepository extends JpaRepository<Plata, Long> {


    @Query("""
            
                                select p from Plata p
                                where
                                    (:chelnerId = -1 or p.chelner.id = :chelnerId)
                                    and (:comandaId = -1 or p.comanda.id = :comandaId)
                                    and (:clientId = -1 or p.comanda.client.id = :clientId)
                                    and (:cofetarieId = -1 or p.chelner.angajat.cofetarie.id = :cofetarieId)
                                    and (:minSuma is null or p.suma >= :minSuma)
                                    and (:maxSuma is null or p.suma <= :maxSuma)
                                    and (:minDataPlata is null or p.dataPlata >= :minDataPlata)
                                    and (:maxDataPlata is null or p.dataPlata <= :maxDataPlata)
                                    and (:tip is null or p.tip = :tip)
            
            """)
    Page<Plata> findAllByCustom(Long chelnerId, Long comandaId, Long clientId,
                                Long cofetarieId, Double minSuma, Double maxSuma,
                                LocalDate minDataPlata, LocalDate maxDataPlata,
                                PlataTip tip,
                                Pageable pageable);

}