package com.mocicarazvan.dwoltp.mappers;


import com.mocicarazvan.dwoltp.dtos.body.PlataBody;
import com.mocicarazvan.dwoltp.mappers.common.DtosModelMapper;
import com.mocicarazvan.dwoltp.models.Plata;
import org.springframework.stereotype.Component;

@Component
public class PlataMapper implements DtosModelMapper<PlataBody, Plata, Plata> {
//    private final CommandaMapper commandaMapper;
//    private final ChelnerMapper chelnerMapper;
//
//    public PlataMapper(CommandaMapper commandaMapper, ChelnerMapper chelnerMapper) {
//        this.commandaMapper = commandaMapper;
//        this.chelnerMapper = chelnerMapper;
//    }

    @Override
    public Plata fromBodyToModel(PlataBody plataBody) {
        return Plata.builder()
                .tip(plataBody.getTip())
                .suma(plataBody.getSuma())
                .dataPlata(plataBody.getDataPlata())
                .build();
    }

    @Override
    public Plata fromModelToResponse(Plata plata) {
        return plata;
    }

    @Override
    public void updateModelFromBody(PlataBody plataBody, Plata plata) {
        plata.setTip(plataBody.getTip());
        plata.setSuma(plataBody.getSuma());
        plata.setDataPlata(plataBody.getDataPlata());
    }

    @Override
    public void updateModelFromOldModel(Plata modelToBeChanged, Plata modelToChangeFrom) {
//        commandaMapper.updateModelFromOldModel(modelToBeChanged.getComanda(), modelToChangeFrom.getComanda());
//        chelnerMapper.updateModelFromOldModel(modelToBeChanged.getChelner(), modelToChangeFrom.getChelner());
        modelToBeChanged.setTip(modelToChangeFrom.getTip());
        modelToBeChanged.setSuma(modelToChangeFrom.getSuma());
        modelToBeChanged.setDataPlata(modelToChangeFrom.getDataPlata());
    }
}
