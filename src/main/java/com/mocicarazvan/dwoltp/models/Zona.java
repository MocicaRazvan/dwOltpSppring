package com.mocicarazvan.dwoltp.models;

import com.mocicarazvan.dwoltp.utils.GetId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@Table(name = "ZONA")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Zona implements GetId<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ZONA", nullable = false, updatable = false)
    private Long id;

    @Column(name = "NUME", nullable = false, length = 20)
    private String nume;

    @Column(name = "ISO", nullable = false, length = 6)
    private String iso;

    @Override
    public Long getId() {
        return id;
    }
}