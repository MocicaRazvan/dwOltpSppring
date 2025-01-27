package com.mocicarazvan.dwoltp.mappers;

import com.mocicarazvan.dwoltp.dtos.body.AngajatBody;
import com.mocicarazvan.dwoltp.mappers.common.DtosModelMapper;
import com.mocicarazvan.dwoltp.models.Angajat;
import org.springframework.stereotype.Component;

@Component
public class AngajatMapper implements DtosModelMapper<AngajatBody, Angajat, Angajat> {
    private final CofetarieMapper cofetarieMapper;

    public AngajatMapper(CofetarieMapper cofetarieMapper) {
        this.cofetarieMapper = cofetarieMapper;
    }

    @Override
    public Angajat fromBodyToModel(AngajatBody angajatBody) {
        return Angajat.builder()
                .nume(angajatBody.getNume())
                .prenume(angajatBody.getPrenume())
                .email(angajatBody.getEmail())
                .sex(angajatBody.getSex())
                .telefon(angajatBody.getTelefon())
                .build();
    }

    @Override
    public Angajat fromModelToResponse(Angajat angajat) {
        return angajat;
    }

    @Override
    public void updateModelFromBody(AngajatBody angajatBody, Angajat angajat) {
        angajat.setNume(angajatBody.getNume());
        angajat.setPrenume(angajatBody.getPrenume());
        angajat.setEmail(angajatBody.getEmail());
        angajat.setSex(angajatBody.getSex());
        angajat.setTelefon(angajatBody.getTelefon());
    }

    @Override
    public void updateModelFromOldModel(Angajat modelToBeChanged, Angajat modelToChangeFrom) {
        modelToBeChanged.setNume(modelToChangeFrom.getNume());
        modelToBeChanged.setPrenume(modelToChangeFrom.getPrenume());
        modelToBeChanged.setEmail(modelToChangeFrom.getEmail());
        modelToBeChanged.setSalariu(modelToChangeFrom.getSalariu());
        modelToBeChanged.setSex(modelToChangeFrom.getSex());
        modelToBeChanged.setCnp(modelToChangeFrom.getCnp());
        modelToBeChanged.setTelefon(modelToChangeFrom.getTelefon());
        cofetarieMapper.updateModelFromOldModel(modelToBeChanged.getCofetarie(), modelToChangeFrom.getCofetarie());

    }
}
