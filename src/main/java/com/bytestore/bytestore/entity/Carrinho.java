package com.bytestore.bytestore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carrinhos")
@Getter
@Setter
@NoArgsConstructor
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(
            mappedBy = "carrinho",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ItemCarrinho> itens = new ArrayList<>();

    @Transient
    public BigDecimal getValorTotal() {

        return itens.stream()
                .map(ItemCarrinho::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}

