package com.mocicarazvan.dwoltp.services;

import com.mocicarazvan.dwoltp.dtos.body.LocatieBody;
import com.mocicarazvan.dwoltp.mappers.LocatieDtoModelMapper;
import com.mocicarazvan.dwoltp.models.Locatie;
import com.mocicarazvan.dwoltp.models.Oras;
import com.mocicarazvan.dwoltp.repositories.LocatieRepository;
import com.mocicarazvan.dwoltp.services.common.BaseServiceWithDependency;
import com.mocicarazvan.dwoltp.services.common.GetModel;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class LocatieService extends BaseServiceWithDependency<Long, Long, Locatie,
        LocatieBody, Locatie, LocatieRepository, Oras
        > {
    public LocatieService(LocatieRepository repository, LocatieDtoModelMapper mapper, GetModel<Oras, Long> dependencyGetter) {
        super(repository, mapper, "locatie", dependencyGetter);
    }

    @Override
    public Locatie setDependency(LocatieBody locatieBody, Oras dependency) {
        Locatie l = mapper.fromBodyToModel(locatieBody);
        l.setOras(dependency);
        return l;
    }

    @Override
    public Locatie setDependency(LocatieBody body, Oras dependency, Long aLong) {
        Locatie l = getModelById(aLong);
        mapper.updateModelFromBody(body, l);
        l.setOras(dependency);
        return l;
    }


    public Page<Locatie> getPageable(int page, int size, String sortField, boolean ascending, String numeStradaQuery, Long orasId, Long judetId, Long zonaId) {
        return getPageable(page, size, sortField, ascending, pr ->
                repository.findAllCustom(
                        numeStradaQuery,
                        orasId,
                        judetId,
                        zonaId,
                        pr
                ));
    }
}
