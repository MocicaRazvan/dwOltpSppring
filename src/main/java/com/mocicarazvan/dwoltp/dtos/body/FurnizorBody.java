package com.mocicarazvan.dwoltp.dtos.body;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FurnizorBody {

    @NotBlank
    private String nume;

    @NotBlank
    private String rep;
}
