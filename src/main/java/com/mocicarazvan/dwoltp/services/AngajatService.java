package com.mocicarazvan.dwoltp.services;


import com.mocicarazvan.dwoltp.dtos.body.AngajatBody;
import com.mocicarazvan.dwoltp.mappers.AngajatMapper;
import com.mocicarazvan.dwoltp.models.Angajat;
import com.mocicarazvan.dwoltp.models.Cofetarie;
import com.mocicarazvan.dwoltp.repositories.AngajatRepository;
import com.mocicarazvan.dwoltp.services.common.BaseServiceWithDependency;
import com.mocicarazvan.dwoltp.services.common.GetModel;
import com.mocicarazvan.dwoltp.utils.WrapErrorFuture;
import org.springframework.data.domain.Page;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Service
public class AngajatService extends BaseServiceWithDependency
        <Long, Long, Angajat, AngajatBody, Angajat, AngajatRepository, Cofetarie> {


    public AngajatService(AngajatRepository repository, AngajatMapper mapper,
                          GetModel<Cofetarie, Long> dependencyGetter) {
        super(repository, mapper, "angajat", dependencyGetter);
    }

    private final Executor executor = Executors.newVirtualThreadPerTaskExecutor();


    @Override
    public Angajat setDependency(AngajatBody angajatBody, Cofetarie dependency) {
        Angajat a = mapper.fromBodyToModel(angajatBody);
        a.setCofetarie(dependency);
        return a;
    }

    @Override
    public Angajat setDependency(AngajatBody angajatBody, Cofetarie dependency, Long aLong) {
        Angajat a = getModelById(aLong);
        mapper.updateModelFromBody(angajatBody, a);
        a.setCofetarie(dependency);
        return a;
    }


    public Page<Angajat> getPageable(int page, int size, String sortField, boolean ascending, String numeQuery, String prenumeQuery, String emailQuery, Long cofetarieId) {
        return getPageable(page, size, sortField, ascending, (pr) -> repository.findAllByCustom(numeQuery, prenumeQuery, emailQuery, cofetarieId, pr));
    }

    @Override
    public Pair<Boolean, String> existsByUniqueFieldUpdate(AngajatBody angajatBody, Long id) {
        return WrapErrorFuture.wrapCallable(() -> {
            CompletableFuture<Boolean> emailExists = CompletableFuture.supplyAsync(() -> repository.existsAllByEmailAndIdNot(angajatBody.getEmail(), id), executor);
            CompletableFuture<Boolean> telefonExists = CompletableFuture.supplyAsync(() -> repository.existsAllByTelefonAndIdNot(angajatBody.getTelefon(), id), executor);
            return getEmailTelefonException(emailExists, telefonExists);
        }, "angajat-service");
    }

    @Override
    public Pair<Boolean, String> existsByUniqueFieldCreate(AngajatBody angajatBody) {
        return WrapErrorFuture.wrapCallable(() -> {
            CompletableFuture<Boolean> emailExists = CompletableFuture.supplyAsync(() -> repository.existsAllByEmail(angajatBody.getEmail()), executor);
            CompletableFuture<Boolean> telefonExists = CompletableFuture.supplyAsync(() -> repository.existsAllByTelefon(angajatBody.getTelefon()), executor);
            return getEmailTelefonException(emailExists, telefonExists);
        }, "angajat-service");
    }

    private Pair<Boolean, String> getEmailTelefonException(CompletableFuture<Boolean> emailExists, CompletableFuture<Boolean> telefonExists) {
        boolean email = emailExists.join();
        boolean telefon = telefonExists.join();
        if (email && telefon) {
            return Pair.of(true, "email, telefon");
        }
        if (email) {
            return Pair.of(true, "email");
        }
        if (telefon) {
            return Pair.of(true, "telefon");
        }
        return Pair.of(false, "");
    }


}
