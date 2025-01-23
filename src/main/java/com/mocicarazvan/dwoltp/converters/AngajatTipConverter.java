package com.mocicarazvan.dwoltp.converters;

import com.mocicarazvan.dwoltp.enums.AngajatTip;
import jakarta.persistence.Converter;


@Converter(autoApply = true)
public class AngajatTipConverter extends StringEnumConverter<AngajatTip> {

    public AngajatTipConverter() {
        super(AngajatTip.class);
    }
}
