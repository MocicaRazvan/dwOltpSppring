package com.mocicarazvan.dwoltp.services;


import com.mocicarazvan.dwoltp.dtos.body.ClientBody;
import com.mocicarazvan.dwoltp.mappers.ClientMapper;
import com.mocicarazvan.dwoltp.models.Client;
import com.mocicarazvan.dwoltp.models.Cofetarie;
import com.mocicarazvan.dwoltp.repositories.ClientRepository;
import com.mocicarazvan.dwoltp.services.common.BaseService;
import com.mocicarazvan.dwoltp.services.common.GetModel;
import org.springframework.data.domain.Page;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

@Service
public class ClientService extends BaseService
        <Long, Client, ClientBody, Client, ClientRepository> {
    public ClientService(ClientRepository repository, ClientMapper mapper, GetModel<Cofetarie, Long> dependencyGetter) {
        super(repository, mapper, "client");
    }


    public Page<Client> getPageable(int page, int size, String sortField, boolean ascending, String numeQuery, String prenumeQuery, String emailQuery) {
        return getPageable(page, size, sortField, ascending, (pr) -> repository.findAllByCustom(numeQuery, prenumeQuery, emailQuery, pr));
    }

    @Override
    public Pair<Boolean, String> existsByUniqueFieldUpdate(ClientBody angajatBody, Long id) {
        return Pair.of(repository.existsAllByEmailAndIdNot(angajatBody.getEmail(), id), "email");
    }

    @Override
    public Pair<Boolean, String> existsByUniqueFieldCreate(ClientBody angajatBody) {
        return Pair.of(repository.existsAllByEmail(angajatBody.getEmail()), "email");
    }
}
