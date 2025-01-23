package com.mocicarazvan.dwoltp.dtos.exceptions;

import com.mocicarazvan.dwoltp.utils.Transformable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ValidationExceptionResponse extends ExceptionResponse implements Transformable<ValidationExceptionResponse> {
    @Schema(description = "Validation reasons, most of the time is like nameOfField: error message")
    private Map<String, Object> validationReasons;

    public static ValidationExceptionResponse fromExceptionResponse(ExceptionResponse base) {
        return ValidationExceptionResponse.builder()
                .message(base.getMessage())
                .timestamp(base.getTimestamp())
                .reason(base.getReason())
                .status(base.getStatus())
                .path(base.getPath())
                .build();
    }

    public static ValidationExceptionResponse fromExceptionResponse(ExceptionResponse base, Map<String, Object> validationReasons) {
        return fromExceptionResponse(base)
                .map(r -> {
                    r.setValidationReasons(validationReasons);
                    return r;
                });
    }
}
