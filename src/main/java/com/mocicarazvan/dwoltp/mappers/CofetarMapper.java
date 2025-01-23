package com.mocicarazvan.dwoltp.mappers;

import com.mocicarazvan.dwoltp.dtos.body.CofetarBody;
import com.mocicarazvan.dwoltp.mappers.common.DtosModelMapper;
import com.mocicarazvan.dwoltp.models.Cofetar;
import org.springframework.stereotype.Component;


@Component
public class CofetarMapper implements DtosModelMapper<CofetarBody, Cofetar, Cofetar> {
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
}
