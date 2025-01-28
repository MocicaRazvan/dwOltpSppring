package com.mocicarazvan.dwoltp.dtos.repositories;

import com.mocicarazvan.dwoltp.enums.ProdusTip;
import com.mocicarazvan.dwoltp.models.Produs;
import com.mocicarazvan.dwoltp.models.Promotie;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdusPromotie {

    private InnerProdus produs;
    private InnerPromotie promotie;

    public ProdusPromotie(
            Produs produs, Promotie promotie
    ) {
        this.produs = new InnerProdus(
                produs.getId(), produs.getNume(),
                produs.getPret(), produs.getTip(), produs.getGramaj(),
                produs.getModificat()
        );
        if (promotie == null) {
            this.promotie = null;
        } else {

            this.promotie = new InnerPromotie(
                    promotie.getId(), promotie.getPerioadaStart(), promotie.getPerioadaFinal(),
                    promotie.getDiscount()
            );
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InnerProdus {
        private Long id;
        private String nume;
        private BigDecimal pret;
        private ProdusTip tip;
        private Double gramaj;
        private LocalDateTime modficat;
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InnerPromotie {
        private long id;
        private LocalDate perioadaStart;
        private LocalDate perioadaFinal;
        private BigDecimal discount;
    }


}
