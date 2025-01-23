package com.mocicarazvan.dwoltp.dtos.body;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ComandaProdusBody {
    @NotNull
    @Positive
    private Long produsId;
    @NotNull
    @Positive
    private Short cantitate;
}
