package com.mocicarazvan.dwoltp.repositories;

import com.mocicarazvan.dwoltp.dtos.repositories.ProdusPretFinal;
import com.mocicarazvan.dwoltp.dtos.repositories.ProdusPromotie;
import com.mocicarazvan.dwoltp.enums.ProdusTip;
import com.mocicarazvan.dwoltp.models.Produs;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ProdusRepository extends JpaRepository<Produs, Long> {
    boolean existsAllByNumeAndIdNot(String nume, Long id);

    boolean existsAllByNume(String nume);

    @Query("""
                                select  p from Produs p
                                where
                                    (:numeQuery is null or upper(p.nume) like upper(concat('%', :numeQuery, '%')))
                                    and (:pretMin is null or p.pret >= :pretMin)
                                    and (:pretMax is null or p.pret <= :pretMax)
                                    and (:tip is null or p.tip = :tip)
                                    and (:gramajMin is null or p.gramaj >= :gramajMin)
                                    and (:gramajMax is null or p.gramaj <= :gramajMax)
                                    and (:ingredientIds is null or
                                                exists (select 1 from ProdusIngredient pi where pi.ingredient.id in :ingredientIds and pi.produs = p)
                                                            )
            """)
    Page<Produs> findAllByCustom(String numeQuery, BigDecimal pretMin, BigDecimal pretMax,
                                 ProdusTip tip, Double gramajMin, Double gramajMax,
                                 List<Long> ingredientIds, Pageable pageable
    );

    @Query("""
            SELECT new com.mocicarazvan.dwoltp.dtos.repositories.ProdusPretFinal(
                       p,
                       cast( p.pret * (1 - COALESCE((
                           select max(pr.discount) from
                          Promotie pr
                          where p.id = pr.produs.id and pr.perioadaStart <= :perioadaStart
                          and pr.perioadaFinal >= :perioadaFinal
                          and pr.perioadaStart = (
                              select max(pr2.perioadaStart) from Promotie pr2
                              where pr2.produs.id=p.id
                              and pr.perioadaFinal >= :perioadaFinal)
                       ), 0)) as bigdecimal )
                   )
            FROM Produs p
            WHERE p.id IN :produseIds
            """)
    List<ProdusPretFinal> findAllByIdsInWithPromotie(List<Long> produseIds, LocalDate perioadaStart, LocalDate perioadaFinal);

    @Query("""
            select new com.mocicarazvan.dwoltp.dtos.repositories.ProdusPromotie(p, pr)
            from Produs p
            left join Promotie pr
                                on p.id = pr.produs.id and pr.perioadaStart >= :perioadaStart
                                and pr.perioadaFinal >= :perioadaFinal
                                and pr.perioadaStart = (
                                select max(pr2.perioadaStart) from Promotie pr2
                                where pr2.produs.id=p.id
                                and pr.perioadaFinal >= :perioadaFinal
                                 )
            where p.id = :produsId
            """)
    ProdusPromotie getProdusWithPromotie(Long produsId, LocalDate perioadaStart, LocalDate perioadaFinal);


    @Query("""
                  select new com.mocicarazvan.dwoltp.dtos.repositories.ProdusPromotie(p, pr) from Produs p
                                            left join Promotie pr
                                                        on p.id = pr.produs.id and pr.perioadaStart >= :perioadaStart
                                                                    and pr.perioadaFinal >= :perioadaFinal
                                                                    and pr.perioadaStart = (
                                                                        select max(pr2.perioadaStart) from Promotie pr2
                                                                                    where pr2.produs.id=p.id
                                                                                    and pr.perioadaFinal >= :perioadaFinal
                                                                                )
                                            where
                                                (:numeQuery is null or upper(p.nume) like upper(concat('%', :numeQuery, '%')))
                                                and (:pretMin is null or p.pret >= :pretMin)
                                                and (:pretMax is null or p.pret <= :pretMax)
                                                and (:tip is null or p.tip = :tip)
                                                and (:gramajMin is null or p.gramaj >= :gramajMin)
                                                and (:gramajMax is null or p.gramaj <= :gramajMax)
                                                and (:ingredientIds is null or
                                                            exists (select 1 from ProdusIngredient pi where pi.ingredient.id in :ingredientIds and pi.produs = p)
                                                                        )
            """)
    Page<ProdusPromotie> findAllByCustomWithPromotie
            (
                    String numeQuery, BigDecimal pretMin, BigDecimal pretMax,
                    ProdusTip tip, Double gramajMin, Double gramajMax,
                    List<Long> ingredientIds,
                    LocalDate perioadaStart, LocalDate perioadaFinal,
                    Pageable pageable
            );
}