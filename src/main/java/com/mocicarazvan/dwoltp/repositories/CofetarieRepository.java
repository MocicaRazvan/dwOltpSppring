package com.mocicarazvan.dwoltp.repositories;

import com.mocicarazvan.dwoltp.enums.CofetarieTip;
import com.mocicarazvan.dwoltp.models.Cofetarie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CofetarieRepository extends JpaRepository<Cofetarie, Long> {

    @Query("""
                        select c from Cofetarie c
                        where upper(c.nume) like upper(concat('%', :numeQuery, '%'))
                        and (:tip is null or c.tip = :tip)
                        and (:locatieId = -1 or c.locatie.id = :locatieId)
                        and (:locatieNume is null or upper(c.locatie.numeStrada) like upper(concat('%', :locatieNume, '%'))
                        )
            """)
    Page<Cofetarie> findAllByCustom(String numeQuery, String locatieNume,
                                    CofetarieTip tip, Long locatieId, Pageable pageable);

    boolean existsAllByNume(String nume);

    boolean existsAllByNumeAndIdNot(String nume, Long id);
}