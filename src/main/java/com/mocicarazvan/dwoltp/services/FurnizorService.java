package com.mocicarazvan.dwoltp.services;

import com.mocicarazvan.dwoltp.dtos.body.FurnizorBody;
import com.mocicarazvan.dwoltp.mappers.common.DtosModelMapper;
import com.mocicarazvan.dwoltp.models.Furnizor;
import com.mocicarazvan.dwoltp.repositories.FurnizorRepository;
import com.mocicarazvan.dwoltp.services.common.BaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

@Service
public class FurnizorService extends BaseService<Long, Furnizor, FurnizorBody, Furnizor, FurnizorRepository> {
    public FurnizorService(FurnizorRepository repository, DtosModelMapper<FurnizorBody, Furnizor, Furnizor> mapper) {
        super(repository, mapper, "furnizor");
    }

    @Override
    public Pair<Boolean, String> existsByUniqueFieldUpdate(FurnizorBody furnizorBody, Long id) {
        return Pair.of(repository.existsAllByNumeAndIdNot(furnizorBody.getNume(), id), "nume");
    }

    public Page<Furnizor> getPageable(int page, int size, String sortField, boolean ascending, String numeQuery, String repQuery) {
        return getPageable(page, size, sortField, ascending, (pr) -> repository.findCustom(numeQuery, repQuery, pr));
    }

    @Override
    public Pair<Boolean, String> existsByUniqueFieldCreate(FurnizorBody furnizorBody) {
        return Pair.of(repository.existsAllByNume(furnizorBody.getNume()), "nume");
    }
}
