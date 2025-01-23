package com.mocicarazvan.dwoltp.repositories;

import com.mocicarazvan.dwoltp.models.ProdusIngredient;
import com.mocicarazvan.dwoltp.models.composedIds.ProdusIngredientId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdusIngredientRepository extends JpaRepository<ProdusIngredient, ProdusIngredientId> {
}