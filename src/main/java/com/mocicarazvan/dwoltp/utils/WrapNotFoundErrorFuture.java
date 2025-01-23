package com.mocicarazvan.dwoltp.utils;

import com.mocicarazvan.dwoltp.exceptions.NotFoundException;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionException;

public class WrapNotFoundErrorFuture {
    public static <T> T wrapCallable(Callable<T> callable, String serviceName) {
        try {
            return callable.call();
        } catch (Exception e) {
            if (e instanceof CompletionException) {

                if (e.getCause() instanceof NotFoundException) {
                    throw new NotFoundException(((NotFoundException) e.getCause()).getEntity(), ((NotFoundException) e.getCause()).getCriteria());
                }
                throw new NotFoundException(serviceName, "dependency");
            }

            throw new RuntimeException(e);
        }
    }
}
