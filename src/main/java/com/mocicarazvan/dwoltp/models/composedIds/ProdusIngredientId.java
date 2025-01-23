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
import java.util.Objects;

@Getter
@Setter
@Embeddable
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProdusIngredientId implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = -8511063018454560148L;
    @Column(name = "ID_PRODUS", nullable = false)
    private Long idProdus;

    @Column(name = "ID_INGREDIENT", nullable = false)
    private Long idIngredient;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProdusIngredientId entity = (ProdusIngredientId) o;
        return Objects.equals(this.idProdus, entity.idProdus) &&
                Objects.equals(this.idIngredient, entity.idIngredient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProdus, idIngredient);
    }

}