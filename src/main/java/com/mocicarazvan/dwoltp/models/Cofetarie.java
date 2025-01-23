package com.mocicarazvan.dwoltp.models;

import com.mocicarazvan.dwoltp.enums.CofetarieTip;
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
@Table(name = "COFETARIE")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Cofetarie implements GetId<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_COFETARIE", nullable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ID_LOCATIE", nullable = false)
    private com.mocicarazvan.dwoltp.models.Locatie locatie;

    @Column(name = "TIP", length = 14)
    private CofetarieTip tip;

    @Column(name = "NUME", nullable = false, length = 50)
    private String nume;

    @Override
    public Long getId() {
        return id;
    }
}