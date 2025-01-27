package com.mocicarazvan.dwoltp.mappers;

import com.mocicarazvan.dwoltp.dtos.body.CofetarBody;
import com.mocicarazvan.dwoltp.mappers.common.DtosModelMapper;
import com.mocicarazvan.dwoltp.models.Cofetar;
import org.springframework.stereotype.Component;


@Component
public class CofetarMapper implements DtosModelMapper<CofetarBody, Cofetar, Cofetar> {
    private final AngajatMapper angajatMapper;

    public CofetarMapper(AngajatMapper angajatMapper) {
        this.angajatMapper = angajatMapper;
    }

    @Override
    public Cofetar fromBodyToModel(CofetarBody chelnerBody) {
        return Cofetar.builder()
                .specializare(chelnerBody.getSpecializare())
                .build();
    }

    @Override
    public Cofetar fromModelToResponse(Cofetar chelner) {
        return chelner;
    }

    @Override
    public void updateModelFromBody(CofetarBody cofetarBody, Cofetar cofetar) {
        cofetar.setSpecializare(cofetarBody.getSpecializare());
    }

    @Override
    public void updateModelFromOldModel(Cofetar modelToBeChanged, Cofetar modelToChangeFrom) {
        angajatMapper.updateModelFromOldModel(modelToBeChanged.getAngajat(), modelToChangeFrom.getAngajat());
        modelToBeChanged.setSpecializare(modelToChangeFrom.getSpecializare());
    }
}
