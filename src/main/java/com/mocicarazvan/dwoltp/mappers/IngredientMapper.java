package com.mocicarazvan.dwoltp.mappers;

import com.mocicarazvan.dwoltp.dtos.body.IngredientBody;
import com.mocicarazvan.dwoltp.mappers.common.DtosModelMapper;
import com.mocicarazvan.dwoltp.models.Ingredient;
import org.springframework.stereotype.Component;


@Component
public class IngredientMapper implements DtosModelMapper<IngredientBody, Ingredient, Ingredient> {
    private final FurnizorMapper furnizorMapper;

    public IngredientMapper(FurnizorMapper furnizorMapper) {
        this.furnizorMapper = furnizorMapper;
    }

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

    @Override
    public void updateModelFromBody(IngredientBody ingredientBody, Ingredient ingredient) {
        ingredient.setNume(ingredientBody.getNume());
        ingredient.setStoc(ingredientBody.getStoc());
        ingredient.setStocLunar(ingredientBody.getStocLunar());
    }

    @Override
    public void updateModelFromOldModel(Ingredient modelToBeChanged, Ingredient modelToChangeFrom) {
        modelToBeChanged.setNume(modelToChangeFrom.getNume());
        modelToBeChanged.setStoc(modelToChangeFrom.getStoc());
        modelToBeChanged.setStocLunar(modelToChangeFrom.getStocLunar());
        furnizorMapper.updateModelFromOldModel(modelToBeChanged.getFurnizor(), modelToChangeFrom.getFurnizor());
    }
}
