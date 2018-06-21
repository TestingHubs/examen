package ar.com.mercadolibre.examen.models;

import ar.com.mercadolibre.examen.utils.Punto;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@NoArgsConstructor
public class Planeta {
    @Getter
    public enum Rotacion{
        ROTACION_HORARIA(1), ROTACION_ANTIHORARIA(-1);

        private int value;

        Rotacion(int value){
            this.value = value;
        }
    }

    //Calculada en grados x dia
    private long velocidadAngular;
    //Calculado en km
    private long distanciaAlSol;
    //Sentido de la rotaciòn de la velocidad angular(Horario o Antihorario)
    private Rotacion rotacion;

    /**
     * @param dia : Dia de la medición
     * @return punto: punto del planeta en un momento específico
     *
     * Me dice la posicion del planeta en coordenadas cartesianas
     * basado en las coordenadas polares del planeta
     */
    public Punto posicion(int dia){
        double theta = Math.toRadians(rotacion.getValue() * velocidadAngular * dia);
        return new Punto()
                .setX((distanciaAlSol * Math.sin(theta)))
                .setY((distanciaAlSol * Math.cos(theta)));
    }
}
