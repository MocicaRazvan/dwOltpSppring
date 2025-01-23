package com.mocicarazvan.dwoltp.mappers;


import com.mocicarazvan.dwoltp.dtos.body.FurnizorBody;
import com.mocicarazvan.dwoltp.mappers.common.DtosModelMapper;
import com.mocicarazvan.dwoltp.models.Furnizor;
import org.springframework.stereotype.Component;

@Component
public class FurnizorMapper implements DtosModelMapper<FurnizorBody, Furnizor, Furnizor> {
    @Override
    public Furnizor fromBodyToModel(FurnizorBody furnizorBody) {
        return Furnizor.builder()
                .nume(furnizorBody.getNume())
                .rep(furnizorBody.getRep())
                .build();
    }

    @Override
    public Furnizor fromModelToResponse(Furnizor furnizor) {
        return furnizor;
    }
}
