package com.mocicarazvan.dwoltp.mappers.common;

public interface DtosModelMapper<BODY, MODEL, RESPONSE> {
    MODEL fromBodyToModel(BODY body);

    RESPONSE fromModelToResponse(MODEL model);

    void updateModelFromBody(BODY body, MODEL model);

    void updateModelFromOldModel(MODEL modelToBeChanged, MODEL modelToChangeFrom);
}
