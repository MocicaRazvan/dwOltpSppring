package com.mocicarazvan.dwoltp.dtos.body;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientBody {

    @NotBlank
    private String nume;
    @NotBlank
    private String prenume;

    @Email
    private String email;

    @Length(min = 10, max = 10)
    private String telefon;
}
