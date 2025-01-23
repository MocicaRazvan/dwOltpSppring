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
@Table(name = "JUDET", uniqueConstraints = {
        @UniqueConstraint(name = "UNQ_NUME", columnNames = {"NUME"})
})
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Judet implements GetId<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_JUDET", nullable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ID_ZONA", nullable = false)
    private com.mocicarazvan.dwoltp.models.Zona zona;

    @Column(name = "NUME", nullable = false, length = 20)
    private String nume;

    @Override
    public Long getId() {
        return id;
    }
}