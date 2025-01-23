package com.mocicarazvan.dwoltp.mappers;

import com.mocicarazvan.dwoltp.dtos.body.ChelnerBody;
import com.mocicarazvan.dwoltp.mappers.common.DtosModelMapper;
import com.mocicarazvan.dwoltp.models.Chelner;
import org.springframework.stereotype.Component;


@Component
public class ChelnerMapper implements DtosModelMapper<ChelnerBody, Chelner, Chelner> {
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
}
