package com.mocicarazvan.dwoltp.dtos.body;

import com.mocicarazvan.dwoltp.dtos.common.DependencyId;
import com.mocicarazvan.dwoltp.enums.CofetarieTip;
import jakarta.validation.constraints.NotBlank;
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
public class CofetarieBody implements DependencyId<Long> {
    @Positive
    @NotNull
    private Long locatieId;
    @NotNull
    private CofetarieTip tip;
    @NotBlank
    private String nume;

    @Override
    public Long getDependencyId() {
        return locatieId;
    }
}
