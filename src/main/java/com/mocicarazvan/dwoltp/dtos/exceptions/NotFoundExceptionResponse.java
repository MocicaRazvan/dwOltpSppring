package com.mocicarazvan.dwoltp.dtos.exceptions;

import com.mocicarazvan.dwoltp.exceptions.NotFoundException;
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

public class NotFoundExceptionResponse extends ExceptionResponse implements Transformable<NotFoundExceptionResponse> {
    private String entity;
    private String criteria;

    public static NotFoundExceptionResponse fromExceptionResponse(ExceptionResponse base) {
        return NotFoundExceptionResponse.builder()
                .message(base.getMessage())
                .timestamp(base.getTimestamp())
                .reason(base.getReason())
                .status(base.getStatus())
                .path(base.getPath())
                .build();
    }

    public static NotFoundExceptionResponse fromExceptionResponse(ExceptionResponse base, NotFoundException e) {
        return fromExceptionResponse(base)
                .map(r -> {
                    r.setEntity(e.getEntity());
                    r.setCriteria(e.getCriteria());
                    return r;
                });
    }
}
