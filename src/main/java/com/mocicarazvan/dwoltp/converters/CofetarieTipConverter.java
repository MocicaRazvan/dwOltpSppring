package com.mocicarazvan.dwoltp.converters;

import com.mocicarazvan.dwoltp.enums.CofetarieTip;
import jakarta.persistence.Converter;


@Converter(autoApply = true)
public class CofetarieTipConverter extends StringEnumConverter<CofetarieTip> {

    public CofetarieTipConverter() {
        super(CofetarieTip.class);
    }
}
