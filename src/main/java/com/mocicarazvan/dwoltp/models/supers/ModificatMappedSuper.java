package com.mocicarazvan.dwoltp.models.supers;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class ModificatMappedSuper {

    @Column(name = "modificat")
    private LocalDateTime modificat;

    @PrePersist
    public void prePersist() {
        modificat = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        modificat = LocalDateTime.now();
    }

}
