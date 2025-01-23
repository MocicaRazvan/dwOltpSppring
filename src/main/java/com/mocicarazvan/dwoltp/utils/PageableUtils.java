package com.mocicarazvan.dwoltp.utils;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;


public class PageableUtils {
    public static PageRequest createPageRequest(int page, int size, String sortField, Boolean ascending) {

        if (sortField == null || sortField.isEmpty() || sortField.equals("null") || ascending == null) {
            return PageRequest.of(page, size);
        }
        return PageRequest.of(page, size, ascending ? Sort.by(sortField).ascending() : Sort.by(sortField).descending());
    }
}
