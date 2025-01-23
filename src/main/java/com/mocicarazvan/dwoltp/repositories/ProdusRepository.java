package com.mocicarazvan.dwoltp.repositories;

import com.mocicarazvan.dwoltp.enums.ProdusTip;
import com.mocicarazvan.dwoltp.models.Produs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
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
}