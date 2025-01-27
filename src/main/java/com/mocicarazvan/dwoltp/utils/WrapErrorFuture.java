package com.mocicarazvan.dwoltp.utils;

import com.mocicarazvan.dwoltp.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionException;

@Slf4j
public class WrapErrorFuture {
    public static <T> T wrapCallable(Callable<T> callable, String serviceName) {
        try {
            return callable.call();
        } catch (Exception e) {
            log.error("Error in service: {}", serviceName, e);
            if (e instanceof CompletionException) {

                if (e.getCause() instanceof NotFoundException notFoundException) {
                    throw new NotFoundException(notFoundException.getEntity(), notFoundException.getCriteria());
                }
                throw new NotFoundException(serviceName, "dependency");
            }
            if (e.getCause() instanceof RuntimeException runtimeException) {
                throw runtimeException;
            }

            throw new RuntimeException(e);
        }
    }
}
