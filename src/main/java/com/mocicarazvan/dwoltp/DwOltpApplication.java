package com.mocicarazvan.dwoltp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class DwOltpApplication {

    public static void main(String[] args) {
        SpringApplication.run(DwOltpApplication.class, args);
    }

}
