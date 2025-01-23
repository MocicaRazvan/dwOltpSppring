package com.mocicarazvan.dwoltp.services;

import com.mocicarazvan.dwoltp.dtos.body.PlataBody;
import com.mocicarazvan.dwoltp.enums.PlataTip;
import com.mocicarazvan.dwoltp.mappers.common.DtosModelMapper;
import com.mocicarazvan.dwoltp.models.Chelner;
import com.mocicarazvan.dwoltp.models.Comanda;
import com.mocicarazvan.dwoltp.models.Plata;
import com.mocicarazvan.dwoltp.repositories.PlataRepository;
import com.mocicarazvan.dwoltp.services.common.BaseService2Dependencies;
import com.mocicarazvan.dwoltp.services.common.GetModel;
import org.springframework.data.domain.Page;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PlataService extends BaseService2Dependencies<Long, Long, Long, Plata, PlataBody, Plata,
        PlataRepository, Comanda, Chelner
        > {
    public PlataService(PlataRepository repository, DtosModelMapper<PlataBody, Plata, Plata> mapper, GetModel<Comanda, Long> dependencyGetter1, GetModel<Chelner, Long> dependencyGetter2) {
        super(repository, mapper, "plata", dependencyGetter1, dependencyGetter2);
    }

    @Override
    public Pair<Long, Long> getDependencyIds(PlataBody plataBody) {
        return Pair.of(plataBody.getComandaId(), plataBody.getChelnerId());
    }

    public Page<Plata> getPageable(int page, int size, String sortField, boolean ascending, Long chelnerId, Long comandaId, Long clientId,
                                   Long cofetarieId, Double minSuma, Double maxSuma,
                                   LocalDate minDataPlata, LocalDate maxDataPlata,
                                   PlataTip tip) {
        return getPageable(page, size, sortField, ascending, (pr) -> repository.findAllByCustom(chelnerId, comandaId, clientId, cofetarieId, minSuma, maxSuma, minDataPlata, maxDataPlata, tip, pr));
    }

    @Override
    public Plata setDependencies(PlataBody plataBody, Pair<Comanda, Chelner> dependencies) {
        Plata p = mapper.fromBodyToModel(plataBody);
        p.setComanda(dependencies.getFirst());
        p.setChelner(dependencies.getSecond());
        return p;
    }
}
