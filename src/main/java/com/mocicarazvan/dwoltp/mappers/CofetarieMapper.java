package com.mocicarazvan.dwoltp.mappers;

import com.mocicarazvan.dwoltp.dtos.body.CofetarieBody;
import com.mocicarazvan.dwoltp.mappers.common.DtosModelMapper;
import com.mocicarazvan.dwoltp.models.Cofetarie;
import org.springframework.stereotype.Component;


@Component
public class CofetarieMapper implements DtosModelMapper<CofetarieBody, Cofetarie, Cofetarie> {
    private final LocatieDtoModelMapper locatieDtoModelMapper;

    public CofetarieMapper(LocatieDtoModelMapper locatieDtoModelMapper) {
        this.locatieDtoModelMapper = locatieDtoModelMapper;
    }

    @Override
    public Cofetarie fromBodyToModel(CofetarieBody cofetarieBody) {
        return Cofetarie.builder()
                .tip(cofetarieBody.getTip())
                .nume(cofetarieBody.getNume())
                .build();
    }

    @Override
    public Cofetarie fromModelToResponse(Cofetarie cofetarie) {
        return cofetarie;
    }

    @Override
    public void updateModelFromBody(CofetarieBody cofetarieBody, Cofetarie cofetarie) {
        cofetarie.setNume(cofetarieBody.getNume());
        cofetarie.setTip(cofetarieBody.getTip());
    }

    @Override
    public void updateModelFromOldModel(Cofetarie modelToBeChanged, Cofetarie modelToChangeFrom) {
        locatieDtoModelMapper.updateModelFromOldModel(modelToBeChanged.getLocatie(), modelToChangeFrom.getLocatie());
        modelToBeChanged.setTip(modelToChangeFrom.getTip());
        modelToBeChanged.setNume(modelToChangeFrom.getNume());
    }
}
