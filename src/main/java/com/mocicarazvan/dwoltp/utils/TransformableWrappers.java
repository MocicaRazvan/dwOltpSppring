package com.mocicarazvan.dwoltp.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public interface TransformableWrappers {

    @Getter
    @RequiredArgsConstructor
    class MappableWrapper<T> implements Transformable<MappableWrapper<T>> {
        private final T value;


    }

    class DefaultMappableWrapper extends MappableWrapper<String> {
        public DefaultMappableWrapper() {
            super("");
        }
    }
}
