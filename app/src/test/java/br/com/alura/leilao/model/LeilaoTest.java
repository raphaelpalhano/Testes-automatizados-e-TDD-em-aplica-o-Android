package br.com.alura.leilao.model;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class LeilaoTest {

    public static final double DELTA = 0.0001;
    private final Leilao console = new Leilao("Console");
    private final Usuario robert = new Usuario("Robert");
    private final Usuario mariana = new Usuario("Mariana");

    @Test
    public void deveDevolverONomeDaDescricaoDaClassLeilao() {
        // Criar cenário de teste

        // executar ação esperada
        String descricao = console.getDescricao();

        // testar resultad esperado
        assertEquals("Console", descricao);


    }


    @Test
    public void deveDevolveOMaiorLanceQuandoRecebeUmValor(){
        //verificando se devolve maior lance com somente um lance
        console.propoe(new Lance(robert, 400.0));
        double valor = console.getMaiorLance();
        assertEquals(400.0, valor, DELTA);



    }


    @Test
    public void deveDevolverMaiorLanceEmOrdemCrescenteQuandoReceberMaisDeUmValor(){
        //dois lance em ordem crescente, pegando o maior valor
        console.propoe(new Lance(robert, 200.0));
        console.propoe(new Lance(mariana, 300.0));
        double maiorLancedoComputador =  console.getMaiorLance();

        assertEquals(300.0, maiorLancedoComputador, DELTA);

    }

    @Test
    public void deveDevolverMaiorLanceQuandoReceberMaisDeUmValorEmOrdemDecrescente(){
        // ordem decrescente, pega o maior valor
        console.propoe(new Lance(robert, 4500.0));
        console.propoe(new Lance(mariana, 4000.0));
        double maiorLancedoFusca = console.getMaiorLance();

        assertEquals(4500.0, maiorLancedoFusca, DELTA);

    }


    @Test
    public void deveDevolveOMenorLanceQuandoRecebeUmValor(){
        //verificando se devolve maior lance com somente um lance

        console.propoe(new Lance(new Usuario("Roberto"), 400.0));
        double valor = console.getMenorLance();
        assertEquals(400.0, valor, DELTA);



    }

    @Test
    public void deveDevolverMenorLanceEmOrdemCrescenteQuandoReceberMaisDeUmValor(){
        //dois lance em ordem crescente, pegando o maior valor
        console.propoe(new Lance(mariana, 200.0));
        console.propoe(new Lance(robert, 300.0));
        double menorLancedoComputador =  console.getMenorLance();

        assertEquals(200.0, menorLancedoComputador, DELTA);

    }

    @Test
    public void deveDevolverMenorLanceQuandoReceberMaisDeUmValorEmOrdemDecrescente(){
        // ordem decrescente, pega o maior valor
        console.propoe(new Lance(robert, 4500.0));
        console.propoe(new Lance(mariana, 4000.0));
        double menorLancedoFusca = console.getMenorLance();

        assertEquals(4000.0, menorLancedoFusca, DELTA);

    }

    @Test
    public void deveDevolverOsTresMaioresLances(){
        console.propoe(new Lance(mariana, 300.0));
        console.propoe(new Lance(robert, 900.0));
        console.propoe(new Lance(robert, 1000.0));
        List<Lance> tresMaioresLances = console.tresMaioreslances();

       assertEquals(3, tresMaioresLances.size());
       assertEquals(1000.0, tresMaioresLances.get(0).getValor(), DELTA);
       assertEquals(900.0, tresMaioresLances.get(1).getValor(), DELTA);
       assertEquals(300.0, tresMaioresLances.get(2).getValor(), DELTA);
    }


    @Test
    public void deveDevolverOsTresMaioresLancesQuandoNaoReceberLance(){
        List<Lance> tresMaioresLancesDevolvidos = console.tresMaioreslances();

        assertEquals(0, tresMaioresLancesDevolvidos.size());
    }

    @Test
    public void deveDevolverTresMaioresLancesQuandoReceberApenasUmLance(){
        console.propoe(new Lance(mariana, 400.0));
        List<Lance> tresMaioresLancesComumLance = console.tresMaioreslances();
        assertEquals(1, tresMaioresLancesComumLance.size());
        assertEquals(400.0, tresMaioresLancesComumLance.get(0).getValor(), DELTA);
    }



    @Test
    public void devolverMaioreLanceQuandoReceberDoisLances(){
        console.propoe(new Lance(robert, 600.0));
        console.propoe((new Lance(mariana, 90000.0)));

        List<Lance> maioresLancesEsperados = console.tresMaioreslances();

        assertEquals(2, maioresLancesEsperados.size());
        assertEquals(90000.0, maioresLancesEsperados.get(0).getValor(), DELTA);
        assertEquals(600.0, maioresLancesEsperados.get(1).getValor(), DELTA);
    }

    @Test
    public void devolverTresMaioreLancesQuandoReceberQuatroLances(){
        console.propoe(new Lance(robert, 100.0));
        console.propoe((new Lance(mariana, 900.0)));
        console.propoe((new Lance(new Usuario("Jorgina"), 600.0)));
        console.propoe((new Lance(new Usuario("Ricardinho"), 400.0)));

        List<Lance> maioresLancesEsperados = console.tresMaioreslances();

        maioresLancesEsperados.forEach(valor -> System.out.println(valor.getValor()));

        assertEquals(3, maioresLancesEsperados.size());
        assertEquals(900.0, maioresLancesEsperados.get(0).getValor(), DELTA);
        assertEquals(600.0, maioresLancesEsperados.get(1).getValor(), DELTA);
        assertEquals(400.0, maioresLancesEsperados.get(2).getValor(), DELTA);



    }

}
