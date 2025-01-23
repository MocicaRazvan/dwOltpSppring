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

@Getter
@Setter
@Entity
@Table(name = "COFETAR")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Cofetar implements GetId<Long> {
    @Id
    @Column(name = "ID_ANGAJAT", nullable = false, updatable = false)
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ID_ANGAJAT", nullable = false)
    private Angajat angajat;

    @Column(name = "SPECIALIZARE", nullable = false, length = 10)
    private String specializare;

    @Override
    public Long getId() {
        return id;
    }
}