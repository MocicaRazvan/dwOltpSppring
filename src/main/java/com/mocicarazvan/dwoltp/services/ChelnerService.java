package com.mocicarazvan.dwoltp.services;


import com.mocicarazvan.dwoltp.dtos.body.ChelnerBody;
import com.mocicarazvan.dwoltp.mappers.ChelnerMapper;
import com.mocicarazvan.dwoltp.models.Angajat;
import com.mocicarazvan.dwoltp.models.Chelner;
import com.mocicarazvan.dwoltp.models.Cofetarie;
import com.mocicarazvan.dwoltp.repositories.ChelnerRepository;
import com.mocicarazvan.dwoltp.services.common.BaseServiceWithDependency;
import com.mocicarazvan.dwoltp.services.common.GetModel;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

@Service
public class ChelnerService extends BaseServiceWithDependency
        <Long, Long, Chelner, ChelnerBody, Chelner, ChelnerRepository, Cofetarie> {
    public ChelnerService(ChelnerRepository repository, ChelnerMapper mapper, GetModel<Cofetarie, Long> dependencyGetter,
                          AngajatService angajatService) {
        super(repository, mapper, "chelner", dependencyGetter);
        this.angajatService = angajatService;
    }

    private final AngajatService angajatService;

    @Override
    @Transactional
    public Chelner setDependency(ChelnerBody angajatBody, Cofetarie dependency) {
        Angajat angajat = angajatService.create(angajatBody);
        Chelner a = mapper.fromBodyToModel(angajatBody);
        a.setAngajat(angajat);
        return a;
    }

    @Override
    public Chelner setDependency(ChelnerBody chelnerBody, Cofetarie dependency, Long aLong) {
        Chelner c = getModelById(aLong);
        mapper.updateModelFromBody(chelnerBody, c);
        c.setAngajat(angajatService.setDependency(chelnerBody, dependency, aLong));
        return c;
    }

    public Page<Chelner> getPageable(int page, int size, String sortField, boolean ascending, String numeQuery, String prenumeQuery, String emailQuery, Long cofetarieId, Short ziVanzator) {
        return getPageable(page, size, sortField, ascending, (pr) -> repository.findAllByCustom(numeQuery, prenumeQuery, emailQuery, cofetarieId, ziVanzator, pr));
    }

    @Override
    public Pair<Boolean, String> existsByUniqueFieldUpdate(ChelnerBody chelnerBody, Long aLong) {
        return angajatService.existsByUniqueFieldUpdate(chelnerBody, aLong);
    }

    @Override
    public Pair<Boolean, String> existsByUniqueFieldCreate(ChelnerBody chelnerBody) {
        return angajatService.existsByUniqueFieldCreate(chelnerBody);
    }
}
