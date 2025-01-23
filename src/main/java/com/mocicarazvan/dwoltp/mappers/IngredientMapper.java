package com.mocicarazvan.dwoltp.mappers;

import com.mocicarazvan.dwoltp.dtos.body.IngredientBody;
import com.mocicarazvan.dwoltp.mappers.common.DtosModelMapper;
import com.mocicarazvan.dwoltp.models.Ingredient;
import org.springframework.stereotype.Component;


@Component
public class IngredientMapper implements DtosModelMapper<IngredientBody, Ingredient, Ingredient> {
    @Override
    public Ingredient fromBodyToModel(IngredientBody ingredientBody) {
        return Ingredient.builder()
                .nume(ingredientBody.getNume())
                .stoc(ingredientBody.getStoc())
                .stocLunar(ingredientBody.getStocLunar())
                .build();
    }

    @Override
    public Ingredient fromModelToResponse(Ingredient ingredient) {
        return ingredient;
    }
}
