package com.ecovira.ecovira.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class PontoDeColeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Embedded
    private Endereco endereco;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "responsavel_ponto_id")
    private Pessoa responsavelPonto;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id")
    private List<Produto> produtos = new ArrayList<>();

}
