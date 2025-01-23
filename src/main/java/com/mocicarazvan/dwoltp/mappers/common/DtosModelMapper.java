package com.mocicarazvan.dwoltp.mappers.common;

public interface DtosModelMapper<BODY, MODEL, RESPONSE> {
    MODEL fromBodyToModel(BODY body);

    RESPONSE fromModelToResponse(MODEL model);
}
