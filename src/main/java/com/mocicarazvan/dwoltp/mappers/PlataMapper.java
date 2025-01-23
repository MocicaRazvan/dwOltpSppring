package com.mocicarazvan.dwoltp.mappers;


import com.mocicarazvan.dwoltp.dtos.body.PlataBody;
import com.mocicarazvan.dwoltp.mappers.common.DtosModelMapper;
import com.mocicarazvan.dwoltp.models.Plata;
import org.springframework.stereotype.Component;

@Component
public class PlataMapper implements DtosModelMapper<PlataBody, Plata, Plata> {
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
}
