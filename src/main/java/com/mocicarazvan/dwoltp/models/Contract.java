package com.mocicarazvan.dwoltp.models;

import com.mocicarazvan.dwoltp.models.supers.ModificatMappedSuper;
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

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "CONTRACT")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Contract extends ModificatMappedSuper implements GetId<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CONTRACT", nullable = false, updatable = false)
    private Long id;

    @ColumnDefault("sysdate")
    @Column(name = "DATA_INCHEIERE")
    private LocalDate dataIncheiere;

    @Column(name = "COST", nullable = false, precision = 7, scale = 2)
    private BigDecimal cost;

    @Column(name = "ZI_ONORARE")
    private Short ziOnorare;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ID_FURNIZOR", nullable = false)
    private Furnizor idFurnizor;

    @Override
    public Long getId() {
        return id;
    }
}