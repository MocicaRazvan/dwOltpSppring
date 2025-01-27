package com.mocicarazvan.dwoltp.mappers;


import com.mocicarazvan.dwoltp.dtos.body.ProdusBody;
import com.mocicarazvan.dwoltp.mappers.common.DtosModelMapper;
import com.mocicarazvan.dwoltp.models.Produs;
import org.springframework.stereotype.Component;

@Component
public class ProdusMapper implements DtosModelMapper<ProdusBody, Produs, Produs> {
    @Override
    public Produs fromBodyToModel(ProdusBody produsBody) {
        return Produs.builder()
                .nume(produsBody.getNume())
                .pret(produsBody.getPret())
                .tip(produsBody.getTip())
                .gramaj(produsBody.getGramaj())
                .build();
    }

    @Override
    public Produs fromModelToResponse(Produs produs) {
        return produs;
    }

    @Override
    public void updateModelFromBody(ProdusBody produsBody, Produs produs) {
        produs.setNume(produsBody.getNume());
        produs.setPret(produsBody.getPret());
        produs.setTip(produsBody.getTip());
        produs.setGramaj(produsBody.getGramaj());
    }

    @Override
    public void updateModelFromOldModel(Produs modelToBeChanged, Produs modelToChangeFrom) {
        modelToBeChanged.setNume(modelToChangeFrom.getNume());
        modelToBeChanged.setPret(modelToChangeFrom.getPret());
        modelToBeChanged.setTip(modelToChangeFrom.getTip());
        modelToBeChanged.setGramaj(modelToChangeFrom.getGramaj());
//        modelToBeChanged.setProdusIngredients(modelToChangeFrom.getProdusIngredients());
    }
}
