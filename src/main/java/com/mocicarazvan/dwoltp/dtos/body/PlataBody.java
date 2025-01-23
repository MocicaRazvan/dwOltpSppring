package com.mocicarazvan.dwoltp.dtos.body;

import com.mocicarazvan.dwoltp.enums.PlataTip;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlataBody {

    @Positive
    @NotNull
    private Long chelnerId;

    @Positive
    @NotNull
    private Long ComandaId;

    @NotNull
    private PlataTip tip;

    @NotNull
    private LocalDate dataPlata;

    @Positive
    private Double suma;
}
