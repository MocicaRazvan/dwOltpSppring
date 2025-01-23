package com.mocicarazvan.dwoltp.models;

import com.mocicarazvan.dwoltp.utils.GetId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Entity
@Table(name = "INGREDIENT")
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient implements GetId<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_INGREDIENT", nullable = false, updatable = false)
    private Long id;

    @Column(name = "NUME", nullable = false, length = 25)
    private String nume;

    @Column(name = "STOC")
    private Short stoc;

    @ColumnDefault("400")
    @Column(name = "STOC_LUNAR")
    private Short stocLunar;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "ID_FURNIZOR", nullable = false)
    private Furnizor furnizor;

    @Override
    public Long getId() {
        return id;
    }
}