package com.mocicarazvan.dwoltp.dtos.body;

import com.mocicarazvan.dwoltp.enums.ProdusTip;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProdusBody {

    @NotBlank
    private String nume;

    @Positive
    private BigDecimal pret;

    @NotNull
    private ProdusTip tip;

    @Positive
    private Double gramaj;

    @Size(min = 1)
    List<Long> ingredientIds;
}
