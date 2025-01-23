package com.mocicarazvan.dwoltp.models.composedIds;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@Embeddable
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class IstoricId implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = -2418403150450540392L;
    @Column(name = "ID_ANGAJAT", nullable = false)
    private Long idAngajat;

    @Column(name = "DATA_ANGAJARE_START", nullable = false)
    private LocalDate dataAngajareStart;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        IstoricId entity = (IstoricId) o;
        return Objects.equals(this.idAngajat, entity.idAngajat) &&
                Objects.equals(this.dataAngajareStart, entity.dataAngajareStart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAngajat, dataAngajareStart);
    }

}