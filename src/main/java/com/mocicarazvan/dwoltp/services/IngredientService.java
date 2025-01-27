package com.mocicarazvan.dwoltp.services;


import com.mocicarazvan.dwoltp.dtos.body.IngredientBody;
import com.mocicarazvan.dwoltp.mappers.IngredientMapper;
import com.mocicarazvan.dwoltp.models.Furnizor;
import com.mocicarazvan.dwoltp.models.Ingredient;
import com.mocicarazvan.dwoltp.repositories.IngredientRepository;
import com.mocicarazvan.dwoltp.services.common.BaseServiceWithDependency;
import com.mocicarazvan.dwoltp.services.common.GetModel;
import org.springframework.data.domain.Page;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService extends BaseServiceWithDependency
        <Long, Long, Ingredient, IngredientBody, Ingredient, IngredientRepository, Furnizor> {
    public IngredientService(IngredientRepository repository, IngredientMapper mapper,
                             GetModel<Furnizor, Long> dependencyGetter) {
        super(repository, mapper, "ingredient", dependencyGetter);
    }


    @Override
    public Ingredient setDependency(IngredientBody angajatBody, Furnizor dependency) {
        Ingredient a = mapper.fromBodyToModel(angajatBody);
        a.setFurnizor(dependency);
        return a;
    }

    @Override
    public Ingredient setDependency(IngredientBody ingredientBody, Furnizor dependency, Long aLong) {
        Ingredient c = getModelById(aLong);
        mapper.updateModelFromBody(ingredientBody, c);
        c.setFurnizor(dependency);
        return c;
    }


    public Page<Ingredient> getPageable(int page, int size, String sortField, boolean ascending, String numeQuery, Short stocMin, Short stocMax,
                                        Short stocLunarMin, Short stocLunarMax, Long furnizorId) {
        return getPageable(page, size, sortField, ascending, (pr) -> repository.findAllByCustom(numeQuery, stocMin, stocMax, stocLunarMin, stocLunarMax, furnizorId, pr));
    }

    @Override
    public Pair<Boolean, String> existsByUniqueFieldUpdate(IngredientBody ingredientBody, Long id) {
        return Pair.of(repository.existsAllByNumeAndIdNot(ingredientBody.getNume(), id), "nume");
    }

    @Override
    public Pair<Boolean, String> existsByUniqueFieldCreate(IngredientBody ingredientBody) {
        return Pair.of(repository.existsAllByNume(ingredientBody.getNume()), "nume");
    }

    public List<Ingredient> getAllByIds(List<Long> ids) {
        return repository.findAllById(ids);
    }
}
