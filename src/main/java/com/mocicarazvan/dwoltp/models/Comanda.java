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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "COMANDA")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Comanda extends ModificatMappedSuper implements GetId<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_COMANDA", nullable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ID_CLIENT", nullable = false)
    private Client client;

    @Column(name = "SUMA", nullable = false)
    private Double suma;

    @Column(name = "data_onorare", nullable = false)
    private LocalDate dataOnorare;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ID_LOCATIE", nullable = true)
    private Locatie locatie;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ID_SOFER", nullable = true)
    private SoferLivrari sofer;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "comanda")
    private List<Plata> plati = new ArrayList<>();

    @OneToMany(mappedBy = "comanda", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ComandaProdus> comandaProduses = new ArrayList<>();

    @Override
    public Long getId() {
        return id;
    }
}