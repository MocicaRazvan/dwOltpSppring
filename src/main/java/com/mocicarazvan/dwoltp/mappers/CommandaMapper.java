package com.mocicarazvan.dwoltp.mappers;


import com.mocicarazvan.dwoltp.dtos.body.ComandaBody;
import com.mocicarazvan.dwoltp.mappers.common.DtosModelMapper;
import com.mocicarazvan.dwoltp.models.Comanda;
import org.springframework.stereotype.Component;

@Component
public class CommandaMapper implements DtosModelMapper<ComandaBody, Comanda, Comanda> {
    @Override
    public Comanda fromBodyToModel(ComandaBody comandaBody) {
        return Comanda.builder()
                .suma(comandaBody.getSuma())
                .dataOnorare(comandaBody.getDataOnorare())
                .build();
    }

    @Override
    public Comanda fromModelToResponse(Comanda comanda) {
        return comanda;
    }
}
