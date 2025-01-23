package com.mocicarazvan.dwoltp.mappers;

import com.mocicarazvan.dwoltp.dtos.body.AngajatBody;
import com.mocicarazvan.dwoltp.mappers.common.DtosModelMapper;
import com.mocicarazvan.dwoltp.models.Angajat;
import org.springframework.stereotype.Component;

@Component
public class AngajatMapper implements DtosModelMapper<AngajatBody, Angajat, Angajat> {
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
}
