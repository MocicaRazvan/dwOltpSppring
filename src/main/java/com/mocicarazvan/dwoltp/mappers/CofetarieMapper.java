package com.mocicarazvan.dwoltp.mappers;

import com.mocicarazvan.dwoltp.dtos.body.CofetarieBody;
import com.mocicarazvan.dwoltp.mappers.common.DtosModelMapper;
import com.mocicarazvan.dwoltp.models.Cofetarie;
import org.springframework.stereotype.Component;


@Component
public class CofetarieMapper implements DtosModelMapper<CofetarieBody, Cofetarie, Cofetarie> {
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
}
