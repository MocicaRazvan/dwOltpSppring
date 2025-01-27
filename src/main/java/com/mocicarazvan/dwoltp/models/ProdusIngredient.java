package com.mocicarazvan.dwoltp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mocicarazvan.dwoltp.models.composedIds.ProdusIngredientId;
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
@Table(name = "PRODUS_INGREDIENT")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ProdusIngredient extends ModificatMappedSuper implements GetId<ProdusIngredientId> {
    @EmbeddedId
    private ProdusIngredientId id;

    public ProdusIngredient(Produs produs, Ingredient ingredient) {
        this.produs = produs;
        this.ingredient = ingredient;
        this.id = new ProdusIngredientId(produs.getId(), ingredient.getId());
    }

    @MapsId("idProdus")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ID_PRODUS", nullable = false)
    @JsonIgnore
    private Produs produs;

    @MapsId("idIngredient")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ID_INGREDIENT", nullable = false)
    private Ingredient ingredient;

    @Override
    public ProdusIngredientId getId() {
        return id;
    }
}