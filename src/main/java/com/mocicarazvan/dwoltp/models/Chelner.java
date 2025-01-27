package com.mocicarazvan.dwoltp.models;

import com.mocicarazvan.dwoltp.models.supers.ModificatMappedSuper;
import com.mocicarazvan.dwoltp.utils.GetId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "CHELNER")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Chelner extends ModificatMappedSuper implements GetId<Long> {
    @Id
    @Column(name = "ID_ANGAJAT", nullable = false, updatable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ID_ANGAJAT", nullable = false)
    private Angajat angajat;

    @Column(name = "PROGRAM_START")
    private Short programStart;

    @Column(name = "PROGRAM_FINAL")
    private Short programFinal;

    @Column(name = "ZI_VANZATOR")
    private Short ziVanzator;

    @Override
    public Long getId() {
        return id;
    }
}