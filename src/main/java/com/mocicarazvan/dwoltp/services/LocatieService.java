package com.mocicarazvan.dwoltp.services;

import com.mocicarazvan.dwoltp.dtos.body.LocatieBody;
import com.mocicarazvan.dwoltp.mappers.LocatieDtoModelMapper;
import com.mocicarazvan.dwoltp.models.Locatie;
import com.mocicarazvan.dwoltp.models.Oras;
import com.mocicarazvan.dwoltp.repositories.LocatieRepository;
import com.mocicarazvan.dwoltp.services.common.BaseServiceWithDependency;
import com.mocicarazvan.dwoltp.services.common.GetModel;
import com.mocicarazvan.dwoltp.utils.ParamsUtils;
import com.mocicarazvan.dwoltp.utils.QueryFunctionsUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Page<Locatie> getPageable(int page, int size, String sortField, boolean ascending, String numeStradaQuery, Long orasId, Long judetId, Long zonaId) {
        if (ParamsUtils.isLongPositive(orasId)) {
            return getPageable(page, size, sortField, ascending, (pr) ->
                    QueryFunctionsUtils.getFunction(List.of(numeStradaQuery),
                            List.of(() -> repository.findAllByNumeStradaContainingIgnoreCaseAndOras_Id(numeStradaQuery, orasId, pr)),
                            () -> repository.findAllByNumeStradaContainingIgnoreCase(numeStradaQuery, pr)
                    ));
        }

        if (ParamsUtils.isLongPositive(judetId)) {
            return getPageable(page, size, sortField, ascending, (pr) ->
                    QueryFunctionsUtils.getFunction(List.of(numeStradaQuery),
                            List.of(() -> repository.findAllByNumeStradaContainingIgnoreCaseAndOras_Judet_Id(numeStradaQuery, judetId, pr)),
                            () -> repository.findAllByNumeStradaContainingIgnoreCase(numeStradaQuery, pr)
                    ));
        }

        if (ParamsUtils.isLongPositive(zonaId)) {
            return getPageable(page, size, sortField, ascending, (pr) ->
                    QueryFunctionsUtils.getFunction(List.of(numeStradaQuery),
                            List.of(() -> repository.findAllByNumeStradaContainingIgnoreCaseAndOrasJudetZona_Id(numeStradaQuery, zonaId, pr)),
                            () -> repository.findAllByNumeStradaContainingIgnoreCase(numeStradaQuery, pr)
                    ));
        }

        return getPageable(page, size, sortField, ascending, (pr) ->
                QueryFunctionsUtils.getFunction(List.of(numeStradaQuery),
                        List.of(() -> repository.findAllByNumeStradaContainingIgnoreCase(numeStradaQuery, pr)),
                        () -> repository.findAll(pr)
                ));
    }
}
