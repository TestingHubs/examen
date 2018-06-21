package ar.com.mercadolibre.examen.models;

import ar.com.mercadolibre.examen.utils.Punto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PuntoTest {
    @Autowired
    @Qualifier("vulcanos")
    private Planeta vulcanos;

    @Autowired
    @Qualifier("ferengis")
    private Planeta ferengis;

    @Autowired
    @Qualifier("betasoides")
    private Planeta betasoides;

    @Test
    public void siTodosLosPuntosEstanEnSuPuntoDeInicioDebenEstarAlineados(){
        Punto puntoVulcanos = vulcanos.posicion(0);
        Punto puntoFarengis = ferengis.posicion(0);
        Punto puntoBetasoides = betasoides.posicion(0);

        assertTrue(puntoVulcanos.estaAlineadoCon(puntoFarengis, puntoBetasoides));
    }

    @Test
    public void siTodosLosPuntosEstanEnUnPuntoRandomNoDebenEstarAlineados(){
        Punto puntoVulcanos = vulcanos.posicion(1);
        Punto puntoFarengis = ferengis.posicion(1);
        Punto puntoBetasoides = betasoides.posicion(1);

        assertFalse(puntoVulcanos.estaAlineadoCon(puntoFarengis, puntoBetasoides));
    }
}