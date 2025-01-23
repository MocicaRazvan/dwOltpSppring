package com.mocicarazvan.dwoltp.mappers;

import com.mocicarazvan.dwoltp.dtos.body.LocatieBody;
import com.mocicarazvan.dwoltp.mappers.common.DtosModelMapper;
import com.mocicarazvan.dwoltp.models.Locatie;
import org.springframework.stereotype.Component;

@Component
public class LocatieDtoModelMapper implements DtosModelMapper<LocatieBody, Locatie, Locatie> {
    @Override
    public Locatie fromBodyToModel(LocatieBody locatieBody) {
        return Locatie.builder()
                .nr(locatieBody.getNr())
                .numeStrada(locatieBody.getNumeStrada())
                .build();
    }

    @Override
    public Locatie fromModelToResponse(Locatie locatie) {
        return locatie;
    }
}
