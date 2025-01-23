package com.mocicarazvan.dwoltp.mappers;


import com.mocicarazvan.dwoltp.dtos.body.IstoricBody;
import com.mocicarazvan.dwoltp.mappers.common.DtosModelMapper;
import com.mocicarazvan.dwoltp.models.Istoric;
import org.springframework.stereotype.Component;

@Component
public class IstoricBodyMapper implements DtosModelMapper<IstoricBody, Istoric, Istoric> {
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
}
