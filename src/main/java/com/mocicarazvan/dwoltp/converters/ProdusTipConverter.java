package com.mocicarazvan.dwoltp.converters;

import com.mocicarazvan.dwoltp.enums.ProdusTip;
import jakarta.persistence.Converter;


@Converter(autoApply = true)
public class ProdusTipConverter extends StringEnumConverter<ProdusTip> {

    public ProdusTipConverter() {
        super(ProdusTip.class);
    }
}
