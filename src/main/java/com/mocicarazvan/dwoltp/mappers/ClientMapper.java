package com.mocicarazvan.dwoltp.mappers;

import com.mocicarazvan.dwoltp.dtos.body.ClientBody;
import com.mocicarazvan.dwoltp.mappers.common.DtosModelMapper;
import com.mocicarazvan.dwoltp.models.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper implements DtosModelMapper<ClientBody, Client, Client> {
    @Override
    public Client fromBodyToModel(ClientBody clientBody) {
        return Client.builder()
                .nume(clientBody.getNume())
                .prenume(clientBody.getPrenume())
                .email(clientBody.getEmail())
                .telefon(clientBody.getTelefon())
                .build();
    }

    @Override
    public Client fromModelToResponse(Client client) {
        return client;
    }

    @Override
    public void updateModelFromBody(ClientBody clientBody, Client client) {
        client.setNume(clientBody.getNume());
        client.setPrenume(clientBody.getPrenume());
        client.setEmail(clientBody.getEmail());
        client.setTelefon(clientBody.getTelefon());
    }

    @Override
    public void updateModelFromOldModel(Client modelToBeChanged, Client modelToChangeFrom) {
        modelToBeChanged.setNume(modelToChangeFrom.getNume());
        modelToBeChanged.setPrenume(modelToChangeFrom.getPrenume());
        modelToBeChanged.setEmail(modelToChangeFrom.getEmail());
        modelToBeChanged.setTelefon(modelToChangeFrom.getTelefon());
    }
}
