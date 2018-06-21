package ar.com.mercadolibre.examen.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Triangulo {
    public static final String ORIENTACION_POSITIVA = "POSITIVA";
    public static final String ORIENTACION_NEGATIVA = "NEGATIVA";

    private Punto p1;
    private Punto p2;
    private Punto p3;

    public String orientacion(){
        return ((p1.getX() - p3.getX()) * (p2.getY() - p3.getY()) - (p1.getY() - p3.getY()) * (p2.getX() - p3.getX())) > 0 ? ORIENTACION_POSITIVA : ORIENTACION_NEGATIVA;
    }

    public double perimetro() {
        return p1.distancia(p2) + p2.distancia(p3) + p3.distancia(p1);
    }
}
