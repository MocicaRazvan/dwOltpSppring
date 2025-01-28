package com.mocicarazvan.dwoltp.repositories;

import com.mocicarazvan.dwoltp.models.Promotie;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface PromotieRepository extends JpaRepository<Promotie, Long> {
    boolean existsAllByPerioadaFinalAndPerioadaStartAndProdus_IdAndIdNot(@NotNull LocalDate perioadaFinal, @NotNull LocalDate perioadaStart, @NotNull Long produsId, Long id);

    boolean existsAllByPerioadaFinalAndPerioadaStartAndProdus_Id(@NotNull LocalDate perioadaFinal, @NotNull LocalDate perioadaStart, @NotNull Long produsId);


    @Query("""
                                select p from Promotie p
                                where
                                    (:perioadaStart is null or p.perioadaStart >= :perioadaStart)
                                    and (:perioadaFinal is null or p.perioadaFinal <= :perioadaFinal)
                                    and (:produsId = -1 or p.produs.id = :produsId)
                                   and (:discountMin is null or p.discount >= :discountMin)
                                     and (:discountMax is null or p.discount <= :discountMax)
            
            """)
    Page<Promotie> findAllByCustom(LocalDate perioadaStart, LocalDate perioadaFinal, Long produsId,
                                   BigDecimal discountMin, BigDecimal discountMax, Pageable pageable);

}