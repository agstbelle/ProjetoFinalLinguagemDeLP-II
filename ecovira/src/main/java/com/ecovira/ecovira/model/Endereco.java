package com.ecovira.ecovira.model;


import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Embeddable
public class Endereco {
    private Cep cep;
    private String bairro;
    private String rua;
    private int numero;
}
