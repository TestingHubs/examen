package ar.com.mercadolibre.examen.jobs;

import ar.com.mercadolibre.examen.models.Dia;
import ar.com.mercadolibre.examen.models.Planeta;
import ar.com.mercadolibre.examen.utils.Punto;
import ar.com.mercadolibre.examen.utils.Triangulo;
import ar.com.mercadolibre.examen.service.DiaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReporteClimatico {
    @Autowired
    @Qualifier("vulcanos")
    private Planeta vulcanos;

    @Autowired
    @Qualifier("ferengis")
    private Planeta ferengis;

    @Autowired
    @Qualifier("betasoides")
    private Planeta betasoides;

    private static final Logger log = LoggerFactory.getLogger(ReporteClimatico.class);

    @Autowired
    private DiaService diaService;

    //Dejo este otro cron acà para probar el reporte
    @Scheduled(fixedRate = 15000)
    //Corro todos los años el reporte, el 31 a las 4 AM para tenerlo listo para empezar el año
//    @Scheduled(cron = "0 4 0 31 12 ?")
    public void reportCurrentTime() {
        Punto posicionVulcanos;
        Punto posicionFarengis;
        Punto posicionBetasoides;
        Punto posicionSol = new Punto(0, 0);

        List<Integer> diasSequia = new ArrayList<>();
        List<Integer> diasLluviosos = new ArrayList<>();
        List<Integer> diasOptimos = new ArrayList<>();
        int diaPicoMaximoLluvia = 0;

        double perimetroMaximo = 0;

        for(int i = 0; i < 365 * 10 + 2; i++){
            posicionVulcanos = vulcanos.posicion(i);
            posicionFarengis = ferengis.posicion(i);
            posicionBetasoides = betasoides.posicion(i);

            if(posicionVulcanos.estaAlineadoCon(posicionFarengis, posicionBetasoides)){
                if(posicionSol.estaAlineadoCon(posicionFarengis, posicionBetasoides)){
                    diasSequia.add(i);
                    diaService.save(new Dia().setDia(i).setClima("sequia"));
                }else{
                    diasOptimos.add(i);
                    diaService.save(new Dia().setDia(i).setClima("óptimo"));
                }
            } else{
                Triangulo triangulo = new Triangulo(posicionVulcanos, posicionFarengis, posicionBetasoides);
                if(posicionSol.enMedioDe(triangulo)){
                    double perimetro = triangulo.perimetro();
                    if(perimetro > perimetroMaximo){
                        perimetroMaximo = perimetro;
                        diaPicoMaximoLluvia = i;
                    } else{
                        diasLluviosos.add(i);
                        diaService.save(new Dia().setDia(i).setClima("lluvia"));
                    }
                }
            }
        }

        if(diaPicoMaximoLluvia != 0){
            diaService.save(new Dia().setDia(diaPicoMaximoLluvia).setClima("lluvia maxima"));
        }

        log.info("Períodos de sequia: " + calcularPeriodos(diasSequia));
        log.info("Períodos de lluvia: " + calcularPeriodos(diasLluviosos));
        log.info("Dia pico de lluvias: " + diaPicoMaximoLluvia);
        log.info("Periodos de condiciones optimas: " + calcularPeriodos(diasOptimos));
        log.info("Dias de sequia: "+ diasSequia.toString());
        log.info("Dias de lluvia: " + calcularPeriodos(diasLluviosos));
    }

    private int calcularPeriodos(List<Integer> dias) {
        int anteriorDia = 0;
        int periodos = 0;
        for(int dia : dias){
            if(dia != anteriorDia + 1){
                periodos++;
            }
            anteriorDia = dia;
        }

        return periodos;
    }
}
