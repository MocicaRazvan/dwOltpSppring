package com.mocicarazvan.dwoltp.models;

import com.mocicarazvan.dwoltp.enums.ProdusTip;
import com.mocicarazvan.dwoltp.utils.GetId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "PRODUS")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Produs implements GetId<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUS", nullable = false, updatable = false)
    private Long id;

    @Column(name = "NUME", length = 100)
    private String nume;

    @Column(name = "PRET", precision = 5, scale = 2)
    private BigDecimal pret;

    @Column(name = "TIP", length = 12)
    private ProdusTip tip;

    @Column(name = "GRAMAJ")
    private Double gramaj;

    @OneToMany(mappedBy = "produs", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ProdusIngredient> produsIngredients = new ArrayList<>();

    @Override
    public Long getId() {
        return id;
    }
}