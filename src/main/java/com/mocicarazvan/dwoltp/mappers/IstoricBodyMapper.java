package com.mocicarazvan.dwoltp.mappers;


import com.mocicarazvan.dwoltp.dtos.body.IstoricBody;
import com.mocicarazvan.dwoltp.mappers.common.DtosModelMapper;
import com.mocicarazvan.dwoltp.models.Istoric;
import org.springframework.stereotype.Component;

@Component
public class IstoricBodyMapper implements DtosModelMapper<IstoricBody, Istoric, Istoric> {
    private final AngajatMapper angajatMapper;

    public IstoricBodyMapper(AngajatMapper angajatMapper) {
        this.angajatMapper = angajatMapper;
    }

    @Override
    public Istoric fromBodyToModel(IstoricBody istoricBody) {
        Istoric istoric = Istoric.builder()
                .dataAngajareEnd(istoricBody.getDataAngajareEnd())
                .tipAngajat(istoricBody.getTipAngajat())
                .salariu(istoricBody.getSalariu())
                .build();
        istoric.setDataAngajareStart(istoricBody.getDataAngajareStart());
        return istoric;
    }

    @Override
    public Istoric fromModelToResponse(Istoric istoric) {
        return istoric;
    }

    @Override
    public void updateModelFromBody(IstoricBody istoricBody, Istoric istoric) {
        istoric.setDataAngajareStart(istoricBody.getDataAngajareStart());
        istoric.setDataAngajareEnd(istoricBody.getDataAngajareEnd());
        istoric.setTipAngajat(istoricBody.getTipAngajat());
        istoric.setSalariu(istoricBody.getSalariu());
    }

    @Override
    public void updateModelFromOldModel(Istoric modelToBeChanged, Istoric modelToChangeFrom) {
        angajatMapper.updateModelFromOldModel(modelToBeChanged.getAngajat(), modelToChangeFrom.getAngajat());
        modelToBeChanged.setDataAngajareEnd(modelToChangeFrom.getDataAngajareEnd());
        modelToBeChanged.setCofetarie(modelToChangeFrom.getCofetarie());
        modelToBeChanged.setTipAngajat(modelToChangeFrom.getTipAngajat());
        modelToBeChanged.setSalariu(modelToChangeFrom.getSalariu());
        modelToBeChanged.setDataAngajareStart(modelToChangeFrom.getId().getDataAngajareStart());
    }
}
