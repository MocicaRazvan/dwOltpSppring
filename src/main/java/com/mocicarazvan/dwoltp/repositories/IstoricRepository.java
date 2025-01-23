package com.mocicarazvan.dwoltp.repositories;

import com.mocicarazvan.dwoltp.models.Istoric;
import com.mocicarazvan.dwoltp.models.composedIds.IstoricId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface IstoricRepository extends JpaRepository<Istoric, IstoricId> {


    @Query("""
            
            select i from Istoric i
            where
                (:angajatId = -1 or i.angajat.id = :angajatId)
                and (:cofeatrieId = -1 or i.cofetarie.id = :cofeatrieId)
                and (:prenumeQuery is null or i.angajat.prenume ilike %:prenumeQuery%)
                and (:numeQuery is null or i.angajat.nume ilike %:numeQuery%)
                and (:dataAngajareStartMin is null or i.id.dataAngajareStart >= :dataAngajareStartMin)
                and (:dataAngajareStartMax is null or i.id.dataAngajareStart <= :dataAngajareStartMax)
                and (:dataAngajareEndMin is null or i.dataAngajareEnd >= :dataAngajareEndMin)
                and (:dataAngajareEndMax is null or i.dataAngajareEnd <= :dataAngajareEndMax)
                and (:salariuMin is null or i.salariu >= :salariuMin)
                and (:salariuMax is null or i.salariu <= :salariuMax)
                and (
                            :isDataAngajareEndNull is null
                            or (:isDataAngajareEndNull = true and i.dataAngajareEnd is null)
                            or (:isDataAngajareEndNull = false and i.dataAngajareEnd is not null)
                        )
            
            """)
    Page<Istoric> findAllByCustom(
            Long angajatId, Long cofeatrieId,
            String prenumeQuery, String numeQuery,
            Boolean isDataAngajareEndNull,
            LocalDate dataAngajareStartMin, LocalDate dataAngajareStartMax,
            LocalDate dataAngajareEndMin, LocalDate dataAngajareEndMax,
            BigDecimal salariuMin, BigDecimal salariuMax, Pageable pageable
    );
}