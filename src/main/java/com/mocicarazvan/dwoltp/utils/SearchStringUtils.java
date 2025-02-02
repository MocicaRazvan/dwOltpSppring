package com.mocicarazvan.dwoltp.utils;

public class SearchStringUtils {
    public static String cleanSearchQuery(String searchQuery) {
        if (searchQuery != null) {
            return searchQuery.replaceAll("\\s+", " ").trim();
        }
        return null;
    }
}
