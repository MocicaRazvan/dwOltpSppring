package com.mocicarazvan.dwoltp.dtos.body;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChelnerBody extends AngajatBody {
    @Positive
    private Short programStart;
    private Short programFinal;
    @Positive
    private Short ziVanzator;
}
