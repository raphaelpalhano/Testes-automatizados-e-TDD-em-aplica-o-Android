package br.com.alura.leilao.model;

import android.support.annotation.NonNull;

import java.io.Serializable;

public class Lance implements Serializable, Comparable {

    private final Usuario usuario;
    private final double valor;


    public Lance(Usuario usuario, double valor) {
        this.usuario = usuario;
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        Lance lance = (Lance) o;
        if(valor > lance.getValor()){
            return -1; // pegar o valor em ordem decrescente, pq vai do maior até o menor
        }
        if(valor < lance.getValor()){
            return 1;
        }

        return 0;
    }
}
