package com.mocicarazvan.dwoltp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "COMANDA_PRODUS")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ComandaProdus extends ModificatMappedSuper implements GetId<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_COMANDA_PRODUS", nullable = false, updatable = false)
    private Long id;

    public ComandaProdus(Comanda comanda, Produs produs, Short cantitate) {
        this.comanda = comanda;
        this.produs = produs;
        this.cantitate = cantitate;
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ID_COMANDA", nullable = false)
    @JsonIgnore
    private Comanda comanda;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ID_PRODUS", nullable = false)
    private com.mocicarazvan.dwoltp.models.Produs produs;

    @Column(name = "CANTITATE", nullable = false)
    private Short cantitate;

    @Override
    public Long getId() {
        return id;
    }
}