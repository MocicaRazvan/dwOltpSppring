package com.mocicarazvan.dwoltp.services;

import com.mocicarazvan.dwoltp.dtos.body.CofetarieBody;
import com.mocicarazvan.dwoltp.enums.CofetarieTip;
import com.mocicarazvan.dwoltp.mappers.CofetarieMapper;
import com.mocicarazvan.dwoltp.models.Cofetarie;
import com.mocicarazvan.dwoltp.models.Locatie;
import com.mocicarazvan.dwoltp.repositories.CofetarieRepository;
import com.mocicarazvan.dwoltp.services.common.BaseServiceWithDependency;
import com.mocicarazvan.dwoltp.services.common.GetModel;
import org.springframework.data.domain.Page;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;


@Service
public class CofetarieService extends BaseServiceWithDependency<Long, Long, Cofetarie
        , CofetarieBody, Cofetarie, CofetarieRepository, Locatie
        > {
    public CofetarieService(CofetarieRepository repository, CofetarieMapper mapper, GetModel<Locatie, Long> dependencyGetter
    ) {
        super(repository, mapper, "cofetarie", dependencyGetter);
    }


    @Override
    public Cofetarie setDependency(CofetarieBody cofetarieBody, Locatie dependency) {
        Cofetarie c = mapper.fromBodyToModel(cofetarieBody);
        c.setLocatie(dependency);
        return c;
    }

    @Override
    public Cofetarie setDependency(CofetarieBody cofetarieBody, Locatie dependency, Long aLong) {
        Cofetarie c = getModelById(aLong);
        mapper.updateModelFromBody(cofetarieBody, c);
        c.setLocatie(dependency);
        return c;
    }


    public Page<Cofetarie> getPageable(int page, int size, String sortField, boolean ascending, String numeQuery, String locatieNumeQuery, CofetarieTip tip, Long locatieId) {

        return getPageable(page, size, sortField, ascending, (pr) ->
                repository.findAllByCustom(numeQuery, locatieNumeQuery, tip, locatieId, pr));
    }

    @Override
    public Pair<Boolean, String> existsByUniqueFieldUpdate(CofetarieBody cofetarieBody, Long aLong) {
        return Pair.of(repository.existsAllByNumeAndIdNot(cofetarieBody.getNume(), aLong), "nume");
    }

    @Override
    public Pair<Boolean, String> existsByUniqueFieldCreate(CofetarieBody cofetarieBody) {
        return Pair.of(repository.existsAllByNume(cofetarieBody.getNume()), "nume");
    }
}
