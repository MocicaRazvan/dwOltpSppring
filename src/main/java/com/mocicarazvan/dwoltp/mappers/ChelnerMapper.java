package com.mocicarazvan.dwoltp.mappers;

import com.mocicarazvan.dwoltp.dtos.body.ChelnerBody;
import com.mocicarazvan.dwoltp.mappers.common.DtosModelMapper;
import com.mocicarazvan.dwoltp.models.Chelner;
import org.springframework.stereotype.Component;


@Component
public class ChelnerMapper implements DtosModelMapper<ChelnerBody, Chelner, Chelner> {
    private final AngajatMapper angajatMapper;

    public ChelnerMapper(AngajatMapper angajatMapper) {
        this.angajatMapper = angajatMapper;
    }

    @Override
    public Chelner fromBodyToModel(ChelnerBody chelnerBody) {
        return Chelner.builder()
                .programStart(chelnerBody.getProgramStart())
                .programFinal(chelnerBody.getProgramFinal())
                .ziVanzator(chelnerBody.getZiVanzator())
                .build();
    }

    @Override
    public Chelner fromModelToResponse(Chelner chelner) {
        return chelner;
    }

    @Override
    public void updateModelFromBody(ChelnerBody chelnerBody, Chelner chelner) {
        chelner.setProgramStart(chelnerBody.getProgramStart());
        chelner.setProgramFinal(chelnerBody.getProgramFinal());
        chelner.setZiVanzator(chelnerBody.getZiVanzator());
    }

    @Override
    public void updateModelFromOldModel(Chelner modelToBeChanged, Chelner modelToChangeFrom) {
        angajatMapper.updateModelFromOldModel(modelToBeChanged.getAngajat(), modelToChangeFrom.getAngajat());
        modelToBeChanged.setProgramStart(modelToChangeFrom.getProgramStart());
        modelToBeChanged.setProgramFinal(modelToChangeFrom.getProgramFinal());
        modelToBeChanged.setZiVanzator(modelToChangeFrom.getZiVanzator());
    }
}
