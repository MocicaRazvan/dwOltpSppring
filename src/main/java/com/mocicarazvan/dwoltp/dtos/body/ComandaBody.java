package com.mocicarazvan.dwoltp.dtos.body;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ComandaBody {
    @Positive
    @NotNull
    private Long clientId;

    @Positive
    private Double suma;

    @NotNull
    private LocalDate dataOnorare;

    @Positive
    @NotNull
    private Long locatieId;

    @Positive
    @NotNull
    private Long soferId;

    @Size(min = 1)
    private List<ComandaProdusBody> comandaProduse;


}
