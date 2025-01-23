package com.mocicarazvan.dwoltp.converters;

import com.mocicarazvan.dwoltp.enums.PlataTip;
import jakarta.persistence.Converter;


@Converter(autoApply = true)
public class PlataTipConverter extends StringEnumConverter<PlataTip> {

    public PlataTipConverter() {
        super(PlataTip.class);
    }
}
