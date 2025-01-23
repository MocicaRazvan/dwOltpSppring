package com.mocicarazvan.dwoltp.dtos.body;


import com.mocicarazvan.dwoltp.dtos.common.DependencyId;
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
public class LocatieBody implements DependencyId<Long> {
    @NotNull
    @Positive
    private Long orasId;
    @NotBlank
    private String numeStrada;
    @NotNull
    private Short nr;

    @Override
    public Long getDependencyId() {
        return orasId;
    }
}
