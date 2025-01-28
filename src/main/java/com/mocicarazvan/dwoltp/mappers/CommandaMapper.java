package com.mocicarazvan.dwoltp.mappers;


import com.mocicarazvan.dwoltp.dtos.body.ComandaBody;
import com.mocicarazvan.dwoltp.mappers.common.DtosModelMapper;
import com.mocicarazvan.dwoltp.models.Comanda;
import com.mocicarazvan.dwoltp.models.Plata;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommandaMapper implements DtosModelMapper<ComandaBody, Comanda, Comanda> {
    private final ClientMapper clientMapper;
    private final LocatieDtoModelMapper locatieDtoModelMapper;
    private final SoferLivrariMapper soferLivrariMapper;
    private final PlataMapper plataMapper;

    public CommandaMapper(ClientMapper clientMapper, LocatieDtoModelMapper locatieDtoModelMapper, SoferLivrariMapper soferLivrariMapper, PlataMapper plataMapper) {
        this.clientMapper = clientMapper;
        this.locatieDtoModelMapper = locatieDtoModelMapper;
        this.soferLivrariMapper = soferLivrariMapper;
        this.plataMapper = plataMapper;
    }

    @Override
    public Comanda fromBodyToModel(ComandaBody comandaBody) {
        return Comanda.builder()
//                .suma(comandaBody.getSuma())
                .dataOnorare(comandaBody.getDataOnorare())
                .build();
    }

    @Override
    public Comanda fromModelToResponse(Comanda comanda) {
        return comanda;
    }

    @Override
    public void updateModelFromBody(ComandaBody comandaBody, Comanda comanda) {
//        comanda.setSuma(comandaBody.getSuma());
        comanda.setDataOnorare(comandaBody.getDataOnorare());
    }

    @Override
    public void updateModelFromOldModel(Comanda modelToBeChanged, Comanda modelToChangeFrom) {
        clientMapper.updateModelFromOldModel(modelToBeChanged.getClient(), modelToChangeFrom.getClient());
        modelToBeChanged.setSuma(modelToChangeFrom.getSuma());
        modelToBeChanged.setDataOnorare(modelToChangeFrom.getDataOnorare());
        locatieDtoModelMapper.updateModelFromOldModel(modelToBeChanged.getLocatie(), modelToChangeFrom.getLocatie());
        soferLivrariMapper.updateModelFromOldModel(modelToBeChanged.getSofer(), modelToChangeFrom.getSofer());
        List<Plata> plataList = modelToBeChanged.getPlati();
        plataList.clear();
        plataList.addAll(modelToChangeFrom.getPlati());
    }
}
