package com.mocicarazvan.dwoltp.services;


import com.mocicarazvan.dwoltp.dtos.body.SoferLivrariBody;
import com.mocicarazvan.dwoltp.mappers.SoferLivrariMapper;
import com.mocicarazvan.dwoltp.models.Angajat;
import com.mocicarazvan.dwoltp.models.Cofetarie;
import com.mocicarazvan.dwoltp.models.SoferLivrari;
import com.mocicarazvan.dwoltp.repositories.SoferLivrariRepository;
import com.mocicarazvan.dwoltp.services.common.BaseServiceWithDependency;
import com.mocicarazvan.dwoltp.services.common.GetModel;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

@Service
public class SoferLivrariService extends BaseServiceWithDependency
        <Long, Long, SoferLivrari, SoferLivrariBody, SoferLivrari, SoferLivrariRepository, Cofetarie> {
    public SoferLivrariService(SoferLivrariRepository repository, SoferLivrariMapper mapper, GetModel<Cofetarie, Long> dependencyGetter, AngajatService angajatService
    ) {
        super(repository, mapper, "sofer-livrari", dependencyGetter);
        this.angajatService = angajatService;
    }

    private final AngajatService angajatService;

    @Override
    @Transactional
    public SoferLivrari setDependency(SoferLivrariBody angajatBody, Cofetarie dependency) {
        Angajat angajat = angajatService.create(angajatBody);
        SoferLivrari a = mapper.fromBodyToModel(angajatBody);
        a.setAngajat(angajat);
        return a;
    }

    @Override
    public SoferLivrari setDependency(SoferLivrariBody soferLivrariBody, Cofetarie dependency, Long aLong) {
        SoferLivrari c = getModelById(aLong);
        mapper.updateModelFromBody(soferLivrariBody, c);
        c.setAngajat(angajatService.setDependency(soferLivrariBody, dependency, aLong));
        return c;
    }


    public Page<SoferLivrari> getPageable(int page, int size, String sortField, boolean ascending, String numeQuery, String prenumeQuery, String emailQuery, Long cofetarieId, Short nrLivrariZiMin, Short nrLivrariZiMax) {
        return getPageable(page, size, sortField, ascending, (pr) -> repository.findAllByCustom(numeQuery, prenumeQuery, emailQuery, cofetarieId, nrLivrariZiMin, nrLivrariZiMax, pr));
    }

    @Override
    public Pair<Boolean, String> existsByUniqueFieldUpdate(SoferLivrariBody soferLivrariBody, Long aLong) {
        return angajatService.existsByUniqueFieldUpdate(soferLivrariBody, aLong);
    }

    @Override
    public Pair<Boolean, String> existsByUniqueFieldCreate(SoferLivrariBody soferLivrariBody) {
        return angajatService.existsByUniqueFieldCreate(soferLivrariBody);
    }
}
