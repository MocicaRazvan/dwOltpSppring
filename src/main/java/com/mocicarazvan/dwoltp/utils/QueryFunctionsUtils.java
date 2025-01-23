package com.mocicarazvan.dwoltp.utils;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.concurrent.Callable;


public class QueryFunctionsUtils {

    public static <T> Page<T> getFunction(List<String> queries, List<Callable<Page<T>>> functions, Callable<Page<T>> defaultFunction) {
        if (queries.size() != functions.size()) {
            throw new IllegalArgumentException("Queries and functions must have the same size");
        }

        String notBlankQuery = queries.stream().filter(query -> query != null && !query.isBlank()).findFirst().orElse(null);

        try {

            return notBlankQuery != null ?
                    functions.get(queries.indexOf(notBlankQuery)).call() :
                    defaultFunction.call();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
