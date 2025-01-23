package com.mocicarazvan.dwoltp.repositories;

import com.mocicarazvan.dwoltp.models.Ingredient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    boolean existsAllByNumeAndIdNot(String attr0, Long id);

    boolean existsAllByNume(String attr0);

    @Query("""
                                select i from Ingredient i
                                where
                                    (:numeQuery is null or i.nume ilike %:numeQuery%)
                                    and (:stocMin = -1 or i.stoc >= :stocMin)
                                    and (:stocMax = -1 or i.stoc <= :stocMax)
                                    and (:stocLunarMin = -1 or i.stocLunar >= :stocLunarMin)
                                    and (:stocLunarMax = -1 or i.stocLunar <= :stocLunarMax)
                                    and (:furnizorId = -1 or i.furnizor.id = :furnizorId)
            
            """)
    Page<Ingredient> findAllByCustom(String numeQuery, Short stocMin, Short stocMax,
                                     Short stocLunarMin, Short stocLunarMax, Long furnizorId, Pageable pageable);

}