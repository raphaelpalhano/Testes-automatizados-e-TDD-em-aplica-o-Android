package br.com.alura.leilao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leilao implements Serializable {

    private final String descricao;
    private final List<Lance> lances;
    private double maiorLance = Double.NEGATIVE_INFINITY;// garantir o menor valor possível para o double

    private double menorLance = Double.POSITIVE_INFINITY;


    public Leilao(String descricao) {
        this.descricao = descricao;
        this.lances = new ArrayList<>();
    }

    public void propoe(Lance lance) {
        lances.add(lance);
        Collections.sort(lances); // é necessário fazer que o lance seja comparável.
        double valorLance = lance.getValor();
        calculandoMaiorLance(valorLance);
        calculandoMenorLance(valorLance);


    }

    public void calculandoMaiorLance(double valorLance){
        if(valorLance > maiorLance){
            maiorLance = valorLance;

        }
    }

    public void calculandoMenorLance(double valorLance){
        if(valorLance < menorLance){
           menorLance = valorLance;
       }

    }

    public double getMaiorLance(){
        return maiorLance;
    }


    public String getDescricao() {
        return descricao;
    }

    public double getMenorLance() {
        return menorLance;
    }


    public List<Lance> tresMaioreslances() {
        int quantidadeMaxima = lances.size() > 3 ? 3 : lances.size();
        return lances.subList(0, quantidadeMaxima);
    }
}
