
package com.ecovira.ecovira.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transacoes_credito")
public class TransacaoCredito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverride(
        name = "quantidade",
        column = @Column(name = "valor", nullable = false)
    )
    private EcoCoin valor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoTransacao tipo;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @Column(length = 255)
    private String descricao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "carteira_id", nullable = false)
    private Carteira carteira;

    protected TransacaoCredito() {
    }

    public TransacaoCredito(
        EcoCoin valor,
        TipoTransacao tipo,
        String descricao,
        Carteira carteira
    ) {
        if (valor == null || valor.getQuantidade() <= 0) {
            throw new IllegalArgumentException(
                "O valor da transação deve ser positivo."
            );
        }

        if (tipo == null || carteira == null) {
            throw new IllegalArgumentException(
                "Tipo e carteira são obrigatórios."
            );
        }

        this.valor = valor;
        this.tipo = tipo;
        this.descricao = descricao;
        this.carteira = carteira;
        this.dataHora = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public EcoCoin getValor() {
        return valor;
    }

    public TipoTransacao getTipo() {
        return tipo;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public String getDescricao() {
        return descricao;
    }

    public Carteira getCarteira() {
        return carteira;
    }
}
