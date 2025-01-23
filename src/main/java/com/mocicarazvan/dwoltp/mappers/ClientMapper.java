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
}
