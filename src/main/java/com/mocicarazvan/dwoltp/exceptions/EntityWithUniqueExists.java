package com.mocicarazvan.dwoltp.exceptions;

import lombok.Getter;

@Getter
public class EntityWithUniqueExists extends RuntimeException {
    private final String entity;
    private final String field;

    public EntityWithUniqueExists(String entity, String field) {
        super(String.format("%s with %s already exists", entity, field));
        this.entity = entity;
        this.field = field;
    }
}
