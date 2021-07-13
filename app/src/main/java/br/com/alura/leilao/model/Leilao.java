package br.com.alura.leilao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.alura.leilao.exception.LAnceMenorQueUltimoLance;
import br.com.alura.leilao.exception.LanceSeguidoDoMesmoUsuarioException;
import br.com.alura.leilao.exception.UsuarioJaDeuCincoLancesException;

public class Leilao implements Serializable {

    private final String descricao;
    private final List<Lance> lances;
    private double maiorLance = 0.0;// garantir o menor valor possível para o double

    private double menorLance = 0.0;


    public Leilao(String descricao) {
        this.descricao = descricao;
        this.lances = new ArrayList<>();
    }

    public void propoe(Lance lance) {
        validaLance(lance);
        lances.add(lance);
        double valorLance = lance.getValor();
        // é necessário fazer que o lance seja comparável.
        if (defineMaiorEMenorLanceParaOPrimeiroLance(valorLance)) return;
        Collections.sort(lances);
        calculandoMaiorLance(valorLance);


    }

    private boolean defineMaiorEMenorLanceParaOPrimeiroLance(double valorLance) {
        if(lances.size() == 1){
            maiorLance = valorLance;
            menorLance = valorLance;
            return true;
        }
        return false;
    }

    private void validaLance(Lance lance) {
        double valorLance = lance.getValor();
        if (seOLanceForMenorQueOUltimoLance(valorLance)) throw new LAnceMenorQueUltimoLance();
        if(temLance()){
            Usuario usuarioNovo = lance.getUsuario();
            if (usuarioForOMesmoDoUltimoLance(usuarioNovo))
                throw new LanceSeguidoDoMesmoUsuarioException();
            if (usuarioDeuCincoLances(usuarioNovo)) throw new UsuarioJaDeuCincoLancesException();
        }

    }

    private boolean temLance() {
        return !lances.isEmpty();
    }

    private boolean usuarioDeuCincoLances(Usuario usuarioNovo) {
        int lancesDoUsuario = 0;
        for(Lance l: lances){
            Usuario usuarioExistente = l.getUsuario();
            if(usuarioExistente.equals(usuarioNovo)){
                lancesDoUsuario++;
                if(lancesDoUsuario == 5){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean usuarioForOMesmoDoUltimoLance(Usuario usuarioNovo) {
        Usuario ultimoUsuario = lances.get(0).getUsuario();
        if(usuarioNovo.equals(ultimoUsuario)){
            return true;
        }
        return false;
    }

    private boolean seOLanceForMenorQueOUltimoLance(double valorLance) {
        if(maiorLance > valorLance){
            return true;
        }
        return false;
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


    public int quantidadeDeLances() {
        return lances.size();
    }
}
