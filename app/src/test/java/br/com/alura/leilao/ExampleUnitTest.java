package br.com.alura.leilao;

import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        String nulo = null;
        assertEquals(4, 2 + 2); // valores iguais
        assertNotEquals(5, 2 + 2); // valores devem ser diferentes
        assertTrue(2+2 == 4);
        assertNull(nulo); // teste para verificar se é nulo
        assertThat(2 + 2, equalTo(4)); // muda a prática (atual e expected)

    }
}