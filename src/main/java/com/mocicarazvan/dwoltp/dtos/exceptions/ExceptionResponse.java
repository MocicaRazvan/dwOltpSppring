package com.mocicarazvan.dwoltp.dtos.exceptions;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {
    private String message;
    private String timestamp;
    private String reason;
    private int status;
    private String path;
}
