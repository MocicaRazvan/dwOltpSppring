package com.mocicarazvan.dwoltp.mappers;

import com.mocicarazvan.dwoltp.dtos.body.SoferLivrariBody;
import com.mocicarazvan.dwoltp.mappers.common.DtosModelMapper;
import com.mocicarazvan.dwoltp.models.SoferLivrari;
import org.springframework.stereotype.Component;

@Component
public class SoferLivrariMapper implements DtosModelMapper<SoferLivrariBody, SoferLivrari, SoferLivrari> {
    private final AngajatMapper angajatMapper;

    public SoferLivrariMapper(AngajatMapper angajatMapper) {
        this.angajatMapper = angajatMapper;
    }

    @Override
    public SoferLivrari fromBodyToModel(SoferLivrariBody soferLivrariBody) {
        return SoferLivrari.builder()
                .nrLivrariZi(soferLivrariBody.getNrLivrariZi())
                .build();
    }

    @Override
    public SoferLivrari fromModelToResponse(SoferLivrari soferLivrari) {
        return soferLivrari;
    }

    @Override
    public void updateModelFromBody(SoferLivrariBody soferLivrariBody, SoferLivrari soferLivrari) {
        soferLivrari.setNrLivrariZi(soferLivrariBody.getNrLivrariZi());
    }

    @Override
    public void updateModelFromOldModel(SoferLivrari modelToBeChanged, SoferLivrari modelToChangeFrom) {
        angajatMapper.updateModelFromOldModel(modelToBeChanged.getAngajat(), modelToChangeFrom.getAngajat());
        modelToBeChanged.setNrLivrariZi(modelToChangeFrom.getNrLivrariZi());
    }
}
