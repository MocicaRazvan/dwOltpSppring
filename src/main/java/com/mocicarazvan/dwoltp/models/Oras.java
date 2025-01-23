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
@Table(name = "ORAS")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Oras implements GetId<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ORAS", nullable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ID_JUDET", nullable = false)
    private Judet judet;

    @Column(name = "NUME", nullable = false, length = 80)
    private String nume;

    @Column(name = "SECTOR")
    private Integer sector;

    @Override
    public Long getId() {
        return id;
    }
}