package com.mocicarazvan.dwoltp.services;

import com.mocicarazvan.dwoltp.dtos.body.IstoricBody;
import com.mocicarazvan.dwoltp.mappers.common.DtosModelMapper;
import com.mocicarazvan.dwoltp.models.Angajat;
import com.mocicarazvan.dwoltp.models.Cofetarie;
import com.mocicarazvan.dwoltp.models.Istoric;
import com.mocicarazvan.dwoltp.models.composedIds.IstoricId;
import com.mocicarazvan.dwoltp.repositories.IstoricRepository;
import com.mocicarazvan.dwoltp.services.common.BaseService2Dependencies;
import com.mocicarazvan.dwoltp.services.common.GetModel;
import org.springframework.data.domain.Page;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;


@Service
public class IstoricService extends BaseService2Dependencies<IstoricId, Long, Long, Istoric, IstoricBody, Istoric,
        IstoricRepository, Angajat, Cofetarie
        > {
    public IstoricService(IstoricRepository repository, DtosModelMapper<IstoricBody, Istoric, Istoric> mapper, GetModel<Angajat, Long> dependencyGetter1, GetModel<Cofetarie, Long> dependencyGetter2) {
        super(repository, mapper, "istoric", dependencyGetter1, dependencyGetter2);
    }

    @Override
    public Pair<Long, Long> getDependencyIds(IstoricBody istoricBody) {
        return Pair.of(istoricBody.getAngajatId(), istoricBody.getCofetarieId());
    }

    @Override
    public Istoric setDependencies(IstoricBody istoricBody, Pair<Angajat, Cofetarie> dependencies) {
        Istoric istoric = mapper.fromBodyToModel(istoricBody);
        istoric.setAngajat(dependencies.getFirst());
        istoric.setCofetarie(dependencies.getSecond());
        return istoric;
    }

    @Override
    public Pair<Boolean, String> existsByUniqueFieldUpdate(IstoricBody istoricBody, IstoricId id) {
        return existsByUniqueFieldCreate(istoricBody);
    }

    @Override
    public Pair<Boolean, String> existsByUniqueFieldCreate(IstoricBody istoricBody) {
        return Pair.of(repository.existsById(new IstoricId(istoricBody.getAngajatId(), istoricBody.getDataAngajareStart())), "angajatId, dataAngajareStart");
    }

    public Page<Istoric> getPageable(int page, int size, String sortField, boolean ascending, Long angajatId, Long cofeatrieId,
                                     String prenumeQuery, String numeQuery,
                                     Boolean isDataAngajareEndNull,
                                     LocalDate dataAngajareStartMin, LocalDate dataAngajareStartMax,
                                     LocalDate dataAngajareEndMin, LocalDate dataAngajareEndMax,
                                     BigDecimal salariuMin, BigDecimal salariuMax) {
        return getPageable(page, size, sortField, ascending, (pr) -> repository.findAllByCustom(angajatId, cofeatrieId, prenumeQuery, numeQuery, isDataAngajareEndNull, dataAngajareStartMin, dataAngajareStartMax, dataAngajareEndMin, dataAngajareEndMax, salariuMin, salariuMax, pr));
    }
}
