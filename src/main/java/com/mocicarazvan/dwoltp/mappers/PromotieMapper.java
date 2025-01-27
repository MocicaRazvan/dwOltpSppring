package com.mocicarazvan.dwoltp.mappers;

import com.mocicarazvan.dwoltp.dtos.body.PromotieBody;
import com.mocicarazvan.dwoltp.mappers.common.DtosModelMapper;
import com.mocicarazvan.dwoltp.models.Promotie;
import org.springframework.stereotype.Component;


@Component
public class PromotieMapper implements DtosModelMapper<PromotieBody, Promotie, Promotie> {
    private final ProdusMapper produsMapper;

    public PromotieMapper(ProdusMapper produsMapper) {
        this.produsMapper = produsMapper;
    }

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

    @Override
    public void updateModelFromBody(PromotieBody promotieBody, Promotie promotie) {
        promotie.setPerioadaFinal(promotieBody.getPerioadaFinal());
        promotie.setPerioadaStart(promotieBody.getPerioadaStart());
        promotie.setDiscount(promotieBody.getDiscount());
    }

    @Override
    public void updateModelFromOldModel(Promotie modelToBeChanged, Promotie modelToChangeFrom) {
        produsMapper.updateModelFromOldModel(modelToBeChanged.getProdus(), modelToChangeFrom.getProdus());
        modelToBeChanged.setPerioadaStart(modelToChangeFrom.getPerioadaStart());
        modelToBeChanged.setPerioadaFinal(modelToChangeFrom.getPerioadaFinal());
        modelToBeChanged.setDiscount(modelToChangeFrom.getDiscount());
    }
}
