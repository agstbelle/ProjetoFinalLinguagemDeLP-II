package com.ecovira.ecovira.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Column;
@Embeddable

public class EcoCoin {

     @Column(name = "quantidade", nullable = false)
    private long quantidade;

    protected EcoCoin() {
    }

public EcoCoin(long quantidade) {
        if (quantidade < 0) {
            throw new IllegalArgumentException(
                "A quantidade de EcoCoins não pode ser negativa."
            );
        }

        this.quantidade = quantidade;
    }

    public long getQuantidade() {
        return quantidade;
    }

    public EcoCoin somar(EcoCoin outra) {
        if (outra == null || outra.quantidade <= 0) {
            throw new IllegalArgumentException(
                "A quantidade adicionada deve ser positiva."
            );
        }

        return new EcoCoin(
            Math.addExact(this.quantidade, outra.quantidade)
        );
    }

    public EcoCoin subtrair(EcoCoin outra) {
        if (outra == null || outra.quantidade <= 0) {
            throw new IllegalArgumentException(
                "A quantidade utilizada deve ser positiva."
            );
        }

        if (this.quantidade < outra.quantidade) {
            throw new IllegalArgumentException(
                "Quantidade de EcoCoins insuficiente."
            );
        }

        return new EcoCoin(
            this.quantidade - outra.quantidade
        );
    }


}


