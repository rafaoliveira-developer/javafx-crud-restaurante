package br.com.restaurante.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("BEBIDA")
public class Bebida extends Produto {
    protected Bebida() {}
    public Bebida(String nome, String descricao, double preco, String categoria) {
        super(nome, descricao, preco, categoria);
    }
    @Override public String getTipo() { return "Bebida"; }
}
