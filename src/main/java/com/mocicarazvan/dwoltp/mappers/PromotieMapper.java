package com.mocicarazvan.dwoltp.mappers;

import com.mocicarazvan.dwoltp.dtos.body.PromotieBody;
import com.mocicarazvan.dwoltp.mappers.common.DtosModelMapper;
import com.mocicarazvan.dwoltp.models.Promotie;
import org.springframework.stereotype.Component;


@Component
public class PromotieMapper implements DtosModelMapper<PromotieBody, Promotie, Promotie> {
    @Override
    public Promotie fromBodyToModel(PromotieBody promotieBody) {
        return Promotie.builder()
                .perioadaFinal(promotieBody.getPerioadaFinal())
                .perioadaStart(promotieBody.getPerioadaStart())
                .discount(promotieBody.getDiscount())
                .build();
    }

    @Override
    public Promotie fromModelToResponse(Promotie promotie) {
        return promotie;
    }
}
