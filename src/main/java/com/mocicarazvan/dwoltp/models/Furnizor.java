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
@Table(name = "FURNIZOR")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Furnizor implements GetId<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FURNIZOR", nullable = false, updatable = false)
    private Long id;

    @Column(name = "NUME", nullable = false, length = 25)
    private String nume;

    @Column(name = "REP", length = 25)
    private String rep;

    @Override
    public Long getId() {
        return id;
    }
}