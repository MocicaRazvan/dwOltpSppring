package com.mocicarazvan.dwoltp.converters;

import com.mocicarazvan.dwoltp.enums.SexTip;
import jakarta.persistence.Converter;


@Converter(autoApply = true)
public class SexConverter extends StringEnumConverter<SexTip> {

    public SexConverter() {
        super(SexTip.class);
    }
}
