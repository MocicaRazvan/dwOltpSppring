package com.mocicarazvan.dwoltp.dtos.body;

import com.mocicarazvan.dwoltp.dtos.common.DependencyId;
import com.mocicarazvan.dwoltp.enums.SexTip;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @Override
    public Long getDependencyId() {
        return cofetarieId;
    }
}
