package com.mocicarazvan.dwoltp.models;

import com.mocicarazvan.dwoltp.enums.SexTip;
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

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "ANGAJAT", uniqueConstraints = {
        @UniqueConstraint(name = "UNQ_EMAIL_ANGAJ", columnNames = {"EMAIL"}),
        @UniqueConstraint(name = "UNQ_TEL_ANGAJ", columnNames = {"TELEFON"})
})
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Angajat extends ModificatMappedSuper implements GetId<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ANGAJAT", nullable = false, updatable = false)
    private Long id;

    @Column(name = "NUME", nullable = false, length = 25)
    private String nume;

    @Column(name = "PRENUME", length = 25)
    private String prenume;

    @Column(name = "EMAIL", length = 50)
    private String email;

    @Column(name = "SALARIU", precision = 6, scale = 2)
    private BigDecimal salariu;

    @Column(name = "SEX", columnDefinition = "CHAR(1)")
    private SexTip sex;

    @Column(name = "CNP", columnDefinition = "CHAR(13)")
    private String cnp;

    @Column(name = "TELEFON", columnDefinition = "CHAR(10)")
    private String telefon;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ID_COFETARIE", nullable = false)
    private com.mocicarazvan.dwoltp.models.Cofetarie cofetarie;

    @Override
    public Long getId() {
        return id;
    }

}