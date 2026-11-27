package com.ecovira.ecovira.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class PontoDeColeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Embedded
    private Endereco endereco;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "criado_por_id", nullable = false, updatable = false)
    private Pessoa criadoPor; //já que um adm pode criar um ponto e definir quem vai ser o responsável

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "responsavel_ponto_id", nullable = false)
    private Pessoa responsavelPonto;

    @OneToMany(mappedBy = "pontoColeta", fetch = FetchType.LAZY)
    private List<Produto> produtos = new ArrayList<>();

    @Version
    @Setter(AccessLevel.NONE)
    private Long version; //para controle de concorrência, já que o adm e o responsávelPonto tem o crud dos pontos

}
