package br.com.restaurante.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("COMIDA")
public class Comida extends Produto {
    protected Comida() {}
    public Comida(String nome, String descricao, double preco, String categoria) {
        super(nome, descricao, preco, categoria);
    }
    @Override public String getTipo() { return "Comida"; }
}
