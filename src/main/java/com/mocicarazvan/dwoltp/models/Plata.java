package com.mocicarazvan.dwoltp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mocicarazvan.dwoltp.enums.PlataTip;
import com.mocicarazvan.dwoltp.utils.GetId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "PLATA")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Plata implements GetId<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PLATA", nullable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ID_ANGAJAT", nullable = false)
    private Chelner chelner;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ID_COMANDA", nullable = false)
    @JsonIgnore
    private Comanda comanda;

    @Column(name = "TIP", length = 20)
    private PlataTip tip;

    @Column(name = "SUMA")
    private Double suma;

    @ColumnDefault("sysdate")
    @Column(name = "DATA_PLATA", nullable = false)
    private LocalDate dataPlata;

    @Override
    public Long getId() {
        return id;
    }
}