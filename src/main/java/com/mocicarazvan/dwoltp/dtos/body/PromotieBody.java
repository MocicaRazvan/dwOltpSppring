package com.mocicarazvan.dwoltp.dtos.body;

import com.mocicarazvan.dwoltp.dtos.common.DependencyId;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class PromotieBody implements DependencyId<Long> {
    @NotNull
    private Long produsId;

    @NotNull
    private LocalDate perioadaStart;

    @NotNull
    private LocalDate perioadaFinal;

    @Positive
    private BigDecimal discount;

    @Override
    @Schema(hidden = true)
    public Long getDependencyId() {
        return produsId;
    }
}
