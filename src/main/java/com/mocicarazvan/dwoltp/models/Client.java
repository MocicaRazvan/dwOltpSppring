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
@Table(name = "CLIENT", uniqueConstraints = {
        @UniqueConstraint(name = "UNQ_NUME_PRENUME_CLIENT", columnNames = {"NUME", "PRENUME"}),
        @UniqueConstraint(name = "UNQ_EMAIL_CLIENT", columnNames = {"EMAIL"})
})
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Client implements GetId<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLIENT", nullable = false, updatable = false)
    private Long id;

    @Column(name = "NUME", nullable = false, length = 25)
    private String nume;

    @Column(name = "PRENUME", length = 25)
    private String prenume;

    @Column(name = "EMAIL", length = 50)
    private String email;

    @Column(name = "TELEFON", nullable = false, columnDefinition = "char(10)")
    private String telefon;

    @Override
    public Long getId() {
        return id;
    }
}