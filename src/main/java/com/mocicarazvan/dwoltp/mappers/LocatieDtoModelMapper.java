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

    @Override
    public void updateModelFromBody(LocatieBody body, Locatie locatie) {
        locatie.setNr(body.getNr());
        locatie.setNumeStrada(body.getNumeStrada());
    }

    @Override
    public void updateModelFromOldModel(Locatie modelToBeChanged, Locatie modelToChangeFrom) {
//        modelToBeChanged.setOras(modelToChangeFrom.getOras());
        modelToBeChanged.setNumeStrada(modelToChangeFrom.getNumeStrada());
        modelToBeChanged.setNr(modelToChangeFrom.getNr());
    }
}
