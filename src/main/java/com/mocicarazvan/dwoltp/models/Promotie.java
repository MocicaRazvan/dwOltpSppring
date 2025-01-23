package com.mocicarazvan.dwoltp.models;

import com.mocicarazvan.dwoltp.utils.GetId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "PROMOTIE")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Promotie implements GetId<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROMOTIE", nullable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ID_PRODUS", nullable = false)
    private Produs produs;

    @Column(name = "PERIOADA_START", nullable = false)
    private LocalDate perioadaStart;

    @Column(name = "PERIOADA_FINAL")
    private LocalDate perioadaFinal;

    @Column(name = "DISCOUNT", precision = 3, scale = 2)
    private BigDecimal discount;

    @Override
    public Long getId() {
        return id;
    }
}