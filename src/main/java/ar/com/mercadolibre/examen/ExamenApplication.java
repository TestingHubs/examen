package ar.com.mercadolibre.examen;

import ar.com.mercadolibre.examen.models.Planeta;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import static ar.com.mercadolibre.examen.models.Planeta.Rotacion.ROTACION_ANTIHORARIA;
import static ar.com.mercadolibre.examen.models.Planeta.Rotacion.ROTACION_HORARIA;

@SpringBootApplication
@EnableScheduling
public class ExamenApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExamenApplication.class, args);
    }

    @Bean(name = "vulcanos")
    public Planeta vulcanos(){
        return new Planeta()
                .setVelocidadAngular(5)
                .setRotacion(ROTACION_ANTIHORARIA)
                .setDistanciaAlSol(1000);
    }

    @Bean(name = "ferengis")
    public Planeta ferengis(){
        return new Planeta()
                .setVelocidadAngular(1)
                .setRotacion(ROTACION_HORARIA)
                .setDistanciaAlSol(500);
    }

    @Bean(name = "betasoides")
    public Planeta betasoides(){
        return new Planeta()
                .setVelocidadAngular(3)
                .setRotacion(ROTACION_HORARIA)
                .setDistanciaAlSol(2000);
    }
}
