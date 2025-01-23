package com.mocicarazvan.dwoltp.exceptions;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
    private final String entity;
    private final String criteria;

    public NotFoundException(String entity, String criteria) {

        super(String.format("Could not find %s with criteria %s", entity, criteria));
        this.entity = entity;
        this.criteria = criteria;
    }
}
