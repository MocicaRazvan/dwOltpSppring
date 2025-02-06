package com.mocicarazvan.dwoltp.dtos.body;

import com.mocicarazvan.dwoltp.dtos.common.DependencyId;
import com.mocicarazvan.dwoltp.enums.SexTip;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AngajatBody implements DependencyId<Long> {
    @NotBlank
    private String nume;
    @NotBlank
    private String prenume;
    @Email
    private String email;
    @Min(2000)
    private BigDecimal salariu;
    @NotNull
    private SexTip sex;
    @Length(min = 10, max = 10)
    private String telefon;

    private Long cofetarieId;


    @Length(min = 13, max = 13)
    @Pattern(regexp = "^[1256]\\d{12}$", message = "CNP must start with 1, 2, 5, or 6 and have 13 digits")
    private String cnp;

    @Override
    @Schema(hidden = true)
    public Long getDependencyId() {
        return cofetarieId;
    }
}
