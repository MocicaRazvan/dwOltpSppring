package com.mocicarazvan.dwoltp.utils;

public class SearchStringUtils {
    public static String cleanSearchQuery(String searchQuery) {
        if (searchQuery != null) {
            return searchQuery.trim().replaceAll("\\s+", " ");
        }
        return null;
    }
}
