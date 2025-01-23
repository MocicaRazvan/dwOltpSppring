package com.mocicarazvan.dwoltp.models;

import com.mocicarazvan.dwoltp.enums.AngajatTip;
import com.mocicarazvan.dwoltp.models.composedIds.IstoricId;
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
@Table(name = "ISTORIC")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Istoric implements GetId<IstoricId> {
    @EmbeddedId
    private IstoricId id;

    @MapsId("idAngajat")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ID_ANGAJAT", nullable = false)
    private Angajat angajat;

    @Column(name = "DATA_ANGAJARE_END")
    private LocalDate dataAngajareEnd;

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @JoinColumn(name = "ID_COFETARIE")
    private Cofetarie cofetarie;

    @Column(name = "TIP_ANGAJAT", length = 14)
    private AngajatTip tipAngajat;

    @Column(name = "SALARIU", precision = 6, scale = 2)
    private BigDecimal salariu;

    @Override
    public IstoricId getId() {
        return id;
    }

    public void setDataAngajareStart(LocalDate dataAngajareStart) {
        id.setDataAngajareStart(dataAngajareStart);
    }
}