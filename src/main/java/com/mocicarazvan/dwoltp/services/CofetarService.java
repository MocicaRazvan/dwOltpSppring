package com.mocicarazvan.dwoltp.services;


import com.mocicarazvan.dwoltp.dtos.body.CofetarBody;
import com.mocicarazvan.dwoltp.mappers.CofetarMapper;
import com.mocicarazvan.dwoltp.models.Angajat;
import com.mocicarazvan.dwoltp.models.Cofetar;
import com.mocicarazvan.dwoltp.models.Cofetarie;
import com.mocicarazvan.dwoltp.repositories.CofetarRepository;
import com.mocicarazvan.dwoltp.services.common.BaseServiceWithDependency;
import com.mocicarazvan.dwoltp.services.common.GetModel;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

@Service
public class CofetarService extends BaseServiceWithDependency
        <Long, Long, Cofetar, CofetarBody, Cofetar, CofetarRepository, Cofetarie> {
    public CofetarService(CofetarRepository repository, CofetarMapper mapper, GetModel<Cofetarie, Long> dependencyGetter,
                          AngajatService angajatService) {
        super(repository, mapper, "cofetar", dependencyGetter);
        this.angajatService = angajatService;
    }

    private final AngajatService angajatService;

    @Override
    @Transactional
    public Cofetar setDependency(CofetarBody cofetarBody, Cofetarie dependency) {
        Angajat angajat = angajatService.create(cofetarBody);
        Cofetar a = mapper.fromBodyToModel(cofetarBody);
        a.setAngajat(angajat);
        return a;
    }

    @Override
    public Cofetar setDependency(CofetarBody cofetarBody, Cofetarie dependency, Long aLong) {
        Cofetar c = getModelById(aLong);
        mapper.updateModelFromBody(cofetarBody, c);
        c.setAngajat(angajatService.setDependency(cofetarBody, dependency, aLong));
        return c;
    }


    public Page<Cofetar> getPageable(int page, int size, String sortField, boolean ascending, String numeQuery, String prenumeQuery, String emailQuery, Long cofetarieId, String specializareQuery) {
        return getPageable(page, size, sortField, ascending, (pr) -> repository.findAllByCustom(numeQuery, prenumeQuery, emailQuery, cofetarieId, specializareQuery, pr));
    }

    @Override
    public Pair<Boolean, String> existsByUniqueFieldUpdate(CofetarBody cofetarBody, Long aLong) {
        return angajatService.existsByUniqueFieldUpdate(cofetarBody, aLong);
    }

    @Override
    public Pair<Boolean, String> existsByUniqueFieldCreate(CofetarBody cofetarBody) {
        return angajatService.existsByUniqueFieldCreate(cofetarBody);
    }

}
