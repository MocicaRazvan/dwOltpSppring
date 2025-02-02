package com.mocicarazvan.dwoltp.services;

import com.mocicarazvan.dwoltp.dtos.body.ProdusBody;
import com.mocicarazvan.dwoltp.dtos.repositories.ProdusPretFinal;
import com.mocicarazvan.dwoltp.dtos.repositories.ProdusPromotie;
import com.mocicarazvan.dwoltp.enums.ProdusTip;
import com.mocicarazvan.dwoltp.exceptions.NotFoundException;
import com.mocicarazvan.dwoltp.mappers.common.DtosModelMapper;
import com.mocicarazvan.dwoltp.models.Ingredient;
import com.mocicarazvan.dwoltp.models.Produs;
import com.mocicarazvan.dwoltp.models.ProdusIngredient;
import com.mocicarazvan.dwoltp.repositories.ProdusRepository;
import com.mocicarazvan.dwoltp.services.common.BaseService;
import com.mocicarazvan.dwoltp.utils.TransformableWrappers;
import org.springframework.data.domain.Page;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ProdusService extends BaseService<Long, Produs, ProdusBody, Produs, ProdusRepository> {
    public ProdusService(ProdusRepository repository, DtosModelMapper<ProdusBody, Produs, Produs> mapper, IngredientService ingredientService) {
        super(repository, mapper, "produs");
        this.ingredientService = ingredientService;
    }

    private final IngredientService ingredientService;

    @Override
    public Pair<Boolean, String> existsByUniqueFieldUpdate(ProdusBody furnizorBody, Long id) {
        return Pair.of(repository.existsAllByNumeAndIdNot(furnizorBody.getNume(), id), "nume");
    }

    public Page<Produs> getPageable(int page, int size, String sortField, boolean ascending, String numeQuery, BigDecimal pretMin, BigDecimal pretMax,
                                    ProdusTip tip, Double gramajMin, Double gramajMax,
                                    List<Long> ingedienteIds) {

        return getPageable(page, size, sortField, ascending, (pr) -> repository.findAllByCustom(numeQuery, pretMin, pretMax, tip, gramajMin, gramajMax, ingedienteIds, pr));
    }

    public Page<ProdusPromotie> getPageableWithPromotie(int page, int size, String sortField, boolean ascending, String numeQuery, BigDecimal pretMin, BigDecimal pretMax,
                                                        ProdusTip tip, Double gramajMin, Double gramajMax,
                                                        List<Long> ingedienteIds, LocalDate perioadaStart, LocalDate perioadaFinal) {

        return getPageable(page, size, sortField, ascending,
                (pr) -> repository.findAllByCustomWithPromotie(numeQuery, pretMin, pretMax, tip, gramajMin, gramajMax, ingedienteIds, perioadaStart, perioadaFinal, pr
                ), m -> m);
    }

    @Override
    public Pair<Boolean, String> existsByUniqueFieldCreate(ProdusBody furnizorBody) {
        return Pair.of(repository.existsAllByNume(furnizorBody.getNume()), "nume");
    }

    @Override
    public Produs saveFromBody(ProdusBody produsBody) {
        List<Ingredient> ingredients = ingredientService.getAllByIds(produsBody.getIngredientIds());
        if (ingredients.size() != produsBody.getIngredientIds().size()) {
            throw new NotFoundException("produs", "ingredient");
        }
        Produs produs = mapper.fromBodyToModel(produsBody);
        produs.setProdusIngredients(ingredients.stream().map(ingredient -> new ProdusIngredient(produs, ingredient)).toList());
        return TransformableWrappers.of(repository.save(produs))
                .mapToValue(m -> getModelById(m.getId()));
    }

    public List<ProdusPretFinal> findAllByIdsInWithPromotie(List<Long> produseIds, LocalDate perioadaStart, LocalDate perioadaFinal) {
        return repository.findAllByIdsInWithPromotie(produseIds, perioadaStart, perioadaFinal);
    }

    public ProdusPromotie getProdusWithPromotie(Long produsId, LocalDate perioadaStart, LocalDate perioadaFinal) {
        return repository.getProdusWithPromotie(produsId, perioadaStart, perioadaFinal);
    }
}
