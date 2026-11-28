
package com.ecovira.ecovira.model;

import jakarta.persistence.*;

@Entity
@Table(name = "carteiras")
public class Carteira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverride(
        name = "quantidade",
        column = @Column(name = "saldo", nullable = false)
    )
     private EcoCoin saldo;

     protected Carteira() {
        this.saldo = new EcoCoin(0);
    }

     public Carteira(Long id) {
        this.id = id;
        this.saldo = new EcoCoin(0);
    }

     public Long getId() {
        return id;
    }

     public EcoCoin getSaldo() {
        return saldo;
    }

     public long consultarSaldo() {
        return saldo.getQuantidade();
    }

     public void adicionarCreditos(EcoCoin quantidade) {
        this.saldo = this.saldo.somar(quantidade);
    }

     public void utilizarCreditos(EcoCoin quantidade) {
        this.saldo = this.saldo.subtrair(quantidade);
    }
}
