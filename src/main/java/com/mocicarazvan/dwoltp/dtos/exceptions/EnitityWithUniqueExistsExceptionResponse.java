package com.mocicarazvan.dwoltp.dtos.exceptions;

import com.mocicarazvan.dwoltp.exceptions.EntityWithUniqueExists;
import com.mocicarazvan.dwoltp.utils.Transformable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor

public class EnitityWithUniqueExistsExceptionResponse extends ExceptionResponse implements Transformable<EnitityWithUniqueExistsExceptionResponse> {
    private String entity;
    private String field;

    public static EnitityWithUniqueExistsExceptionResponse fromExceptionResponse(ExceptionResponse base) {
        return EnitityWithUniqueExistsExceptionResponse.builder()
                .message(base.getMessage())
                .timestamp(base.getTimestamp())
                .reason(base.getReason())
                .status(base.getStatus())
                .path(base.getPath())
                .build();
    }

    public static EnitityWithUniqueExistsExceptionResponse fromExceptionResponse(ExceptionResponse base, EntityWithUniqueExists e) {
        return fromExceptionResponse(base)
                .map(r -> {
                    r.setEntity(e.getEntity());
                    r.setField(e.getField());
                    return r;
                });
    }
}
