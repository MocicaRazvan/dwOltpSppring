package com.mocicarazvan.dwoltp.dtos.body;

import com.mocicarazvan.dwoltp.enums.AngajatTip;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class IstoricBody {

    @NotNull
    @Positive
    private Long angajatId;

    @NotNull
    private LocalDate dataAngajareStart;

    private LocalDate dataAngajareEnd;

    @NotNull
    @Positive
    private Long cofetarieId;


    @NotNull
    private AngajatTip tipAngajat;

    @NotNull
    @Positive
    private BigDecimal salariu;

}
