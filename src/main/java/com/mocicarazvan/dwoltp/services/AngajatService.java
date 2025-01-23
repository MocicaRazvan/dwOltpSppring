package com.mocicarazvan.dwoltp.services;


import com.mocicarazvan.dwoltp.dtos.body.AngajatBody;
import com.mocicarazvan.dwoltp.mappers.AngajatMapper;
import com.mocicarazvan.dwoltp.models.Angajat;
import com.mocicarazvan.dwoltp.models.Cofetarie;
import com.mocicarazvan.dwoltp.repositories.AngajatRepository;
import com.mocicarazvan.dwoltp.services.common.BaseServiceWithDependency;
import com.mocicarazvan.dwoltp.services.common.GetModel;
import org.springframework.data.domain.Page;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

@Service
public class AngajatService extends BaseServiceWithDependency
        <Long, Long, Angajat, AngajatBody, Angajat, AngajatRepository, Cofetarie> {
    public AngajatService(AngajatRepository repository, AngajatMapper mapper, GetModel<Cofetarie, Long> dependencyGetter) {
        super(repository, mapper, "angajat", dependencyGetter);
    }

    @Override
    public Angajat setDependency(AngajatBody angajatBody, Cofetarie dependency) {
        Angajat a = mapper.fromBodyToModel(angajatBody);
        a.setCofetarie(dependency);
        return a;
    }

    public Page<Angajat> getPageable(int page, int size, String sortField, boolean ascending, String numeQuery, String prenumeQuery, String emailQuery, Long cofetarieId) {
        return getPageable(page, size, sortField, ascending, (pr) -> repository.findAllByCustom(numeQuery, prenumeQuery, emailQuery, cofetarieId, pr));
    }

    @Override
    public Pair<Boolean, String> existsByUniqueFieldUpdate(AngajatBody angajatBody, Long id) {
        return Pair.of(repository.existsAllByEmailAndIdNot(angajatBody.getEmail(), id), "email");
    }

    @Override
    public Pair<Boolean, String> existsByUniqueFieldCreate(AngajatBody angajatBody) {
        return Pair.of(repository.existsAllByEmail(angajatBody.getEmail()), "email");
    }
}
