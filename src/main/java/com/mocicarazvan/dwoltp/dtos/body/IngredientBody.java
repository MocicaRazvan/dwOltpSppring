package com.mocicarazvan.dwoltp.dtos.body;

import com.mocicarazvan.dwoltp.dtos.common.DependencyId;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class IngredientBody implements DependencyId<Long> {
    @NotBlank
    private String nume;
    @Positive
    private Short stocLunar = 400;

    @Positive
    private Short stoc;

    @NotNull
    private Long furnizorId;


    @Override
    @Schema(hidden = true)
    public Long getDependencyId() {
        return furnizorId;
    }
}
