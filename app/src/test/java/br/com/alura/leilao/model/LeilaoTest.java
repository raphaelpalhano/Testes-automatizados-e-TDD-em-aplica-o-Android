package br.com.alura.leilao.model;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import br.com.alura.leilao.exception.LAnceMenorQueUltimoLance;
import br.com.alura.leilao.exception.LanceSeguidoDoMesmoUsuarioException;
import br.com.alura.leilao.exception.UsuarioJaDeuCincoLancesException;

import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.*;

public class LeilaoTest {

    public static final double DELTA = 0.0001;
    private final Leilao CONSOLE = new Leilao("Console");
    private final Usuario robert = new Usuario("Robert");
    private final Usuario mariana = new Usuario("Mariana");

    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Test
    public void deveDevolverONomeDaDescricaoDaClassLeilao() {
        // Criar cenário de teste

        // executar ação esperada
        String descricao = CONSOLE.getDescricao();

        // testar resultad esperado
        assertEquals("Console", descricao);

        assertThat(descricao, is("Console"));

        assertThat(3.1 + 3.2, closeTo(3.2+3.1, DELTA));

    }


    @Test
    public void deveDevolveOMaiorLanceQuandoRecebeUmValor(){
        //verificando se devolve maior lance com somente um lance
        CONSOLE.propoe(new Lance(robert, 400.0));
        double valor = CONSOLE.getMaiorLance();
        assertEquals(400.0, valor, DELTA);

    }


    @Test
    public void deveDevolverMaiorLanceEmOrdemCrescenteQuandoReceberMaisDeUmValor(){
        //dois lance em ordem crescente, pegando o maior valor
        CONSOLE.propoe(new Lance(robert, 200.0));
        CONSOLE.propoe(new Lance(mariana, 300.0));
        double maiorLancedoComputador =  CONSOLE.getMaiorLance();

        assertEquals(300.0, maiorLancedoComputador, DELTA);

    }




    @Test
    public void deveDevolveOMenorLanceQuandoRecebeUmValor(){
        //verificando se devolve maior lance com somente um lance

        CONSOLE.propoe(new Lance(new Usuario("Roberto"), 400.0));
        double valor = CONSOLE.getMenorLance();
        assertEquals(400.0, valor, DELTA);

    }

    @Test
    public void deveDevolverMenorLanceEmOrdemCrescenteQuandoReceberMaisDeUmValor(){
        //dois lance em ordem crescente, pegando o maior valor
        CONSOLE.propoe(new Lance(mariana, 200.0));
        CONSOLE.propoe(new Lance(robert, 300.0));
        double menorLancedoComputador =  CONSOLE.getMenorLance();

        assertEquals(200.0, menorLancedoComputador, DELTA);

    }



    @Test
    public void deveDevolverOsTresMaioresLances(){
        CONSOLE.propoe(new Lance(robert, 200.0));
        CONSOLE.propoe(new Lance(new Usuario("Fran"), 300.0));
        CONSOLE.propoe(new Lance(robert, 400.0));

        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioreslances();

//        assertEquals(3, tresMaioresLancesDevolvidos.size());
//        assertThat(tresMaioresLancesDevolvidos, hasSize(equalTo(3)));

//        assertEquals(400.0,
//                tresMaioresLancesDevolvidos.get(0).getValor(), DELTA);
//        assertThat(tresMaioresLancesDevolvidos, hasItem(new Lance(ALEX, 400.0)));

//        assertEquals(300.0,
//                tresMaioresLancesDevolvidos.get(1).getValor(), DELTA);
//        assertEquals(200.0,
//                tresMaioresLancesDevolvidos.get(2).getValor(), DELTA);

//        assertThat(tresMaioresLancesDevolvidos, contains(
//                new Lance(ALEX, 400.0),
//                new Lance(new Usuario("Fran"), 300.0),
//                new Lance(ALEX, 200.0)));

        assertThat(tresMaioresLancesDevolvidos,
                both(Matchers.<Lance>hasSize(3))
                        .and(contains(
                                new Lance(robert, 400.0),
                                new Lance(new Usuario("Fran"), 300.0),
                                new Lance(robert, 200.0))));
    }


    @Test
    public void deveDevolverOsTresMaioresLancesQuandoNaoReceberLance(){
        List<Lance> tresMaioresLancesDevolvidos = CONSOLE.tresMaioreslances();

        assertEquals(0, tresMaioresLancesDevolvidos.size());
    }

    @Test
    public void deveDevolverTresMaioresLancesQuandoReceberApenasUmLance(){
        CONSOLE.propoe(new Lance(mariana, 400.0));
        List<Lance> tresMaioresLancesComumLance = CONSOLE.tresMaioreslances();
        assertEquals(1, tresMaioresLancesComumLance.size());
        assertEquals(400.0, tresMaioresLancesComumLance.get(0).getValor(), DELTA);
    }



    @Test
    public void devolverMaioreLanceQuandoReceberDoisLances(){
        CONSOLE.propoe(new Lance(robert, 600.0));
        CONSOLE.propoe((new Lance(mariana, 90000.0)));

        List<Lance> maioresLancesEsperados = CONSOLE.tresMaioreslances();

        assertEquals(2, maioresLancesEsperados.size());
        assertEquals(90000.0, maioresLancesEsperados.get(0).getValor(), DELTA);
        assertEquals(600.0, maioresLancesEsperados.get(1).getValor(), DELTA);
    }



    @Test
    public void deve_DevolverValorZeroParaMaiorLanceQuandoNaoTiverLances(){
        double maiorLanceDevolvido = CONSOLE.getMaiorLance();
        assertEquals(0.0, maiorLanceDevolvido, DELTA);
    }

    @Test
    public void deve_DevolverValorZeroParaMenorLance_QuandoNaoTiverLance(){
        double menorLanceDevolvido = CONSOLE.getMenorLance();

        assertEquals(0.0, menorLanceDevolvido, DELTA);
    }

    @Test(expected = LAnceMenorQueUltimoLance.class)
    public void naoDeveAdicionarLanceQuandoForMenorQueMaiorLance(){


        CONSOLE.propoe(new Lance(mariana, 400.0));
        CONSOLE.propoe(new Lance(robert, 399.0));

     }

    @Test(expected =  LanceSeguidoDoMesmoUsuarioException.class)
    public void naoDeveAdicionarUmNovoLanceQuandoForOMesmoUsuarioDoUltimoLance(){
        CONSOLE.propoe(new Lance(mariana, 500.0));
        CONSOLE.propoe(new Lance(mariana, 600.0));


    }

    @Test(expected = UsuarioJaDeuCincoLancesException.class)
    public void naoDeveAdicionarLanceQuandoUsuarioDerCincoLance(){
        //exception.expect(UsuarioJaDeuCincoLancesException.class);
        final Usuario FRAN = new Usuario("Fran");
        CONSOLE.propoe(new Lance(FRAN, 100));
        CONSOLE.propoe(new Lance(robert, 200));
        CONSOLE.propoe(new Lance(FRAN , 300));
        CONSOLE.propoe(new Lance(robert, 400));
        CONSOLE.propoe(new Lance(FRAN , 500));
        CONSOLE.propoe(new Lance(robert, 600));
        CONSOLE.propoe(new Lance(FRAN , 800));
        CONSOLE.propoe(new Lance(robert, 1200));
        CONSOLE.propoe(new Lance(FRAN, 2000));
        CONSOLE.propoe(new Lance(robert, 3500));
        CONSOLE.propoe(new Lance(FRAN, 4500));
    }

}
