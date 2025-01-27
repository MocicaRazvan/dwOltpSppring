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
@Table(name = "LOCATIE")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Locatie extends ModificatMappedSuper implements GetId<Long> {
    @Id
    @Column(name = "ID_LOCATIE", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ID_ORAS", nullable = false)
    private Oras oras;

    @Column(name = "NUME_STRADA")
    private String numeStrada;

    @Column(name = "NR")
    private Short nr;

    @Override
    public Long getId() {
        return id;
    }
}