package com.mocicarazvan.dwoltp.dtos.repositories;

import com.mocicarazvan.dwoltp.enums.ProdusTip;
import com.mocicarazvan.dwoltp.models.Produs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public
class ProdusPretFinal {
    private Long id;
    private String nume;
    private ProdusTip tip;
    private Double gramaj;
    private BigDecimal pretFinal;


    public ProdusPretFinal(Produs produs, BigDecimal pretFinal) {
        this.id = produs.getId();
        this.nume = produs.getNume();
        this.tip = produs.getTip();
        this.gramaj = produs.getGramaj();
        this.pretFinal = pretFinal;
    }
}
