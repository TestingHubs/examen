package ar.com.mercadolibre.examen.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.text.DecimalFormat;

import static java.lang.Math.pow;

@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Punto {
    private double x;
    private double y;
    static final double CERO = 0.00001;

    public Boolean estaAlineadoCon(Punto p1, Punto p2){
        return Math.abs(((x - p1.x) * (p2.y - y)) - ((y - p1.y) * (p2.x - x))) < CERO;
    }

    public double distancia(Punto punto) {
        DecimalFormat df = new DecimalFormat("#.##");
        double cateto1 = Double.parseDouble(df.format(x - punto.getX()).replace(",", "."));
        double cateto2 = Double.parseDouble(df.format(y - punto.getY()).replace(",", "."));
        return Math.sqrt(pow(cateto1, 2) + pow(cateto2, 2));
    }

    //Algoritmo para saber si un punto está en medio de un triángulo: http://www.dma.fi.upm.es/personal/mabellanas/tfcs/kirkpatrick/Aplicacion/algoritmos.htm
    public boolean enMedioDe(Triangulo triangulo) {
        Triangulo t1 = new Triangulo(triangulo.getP1(), triangulo.getP2(), this);
        Triangulo t2 = new Triangulo(triangulo.getP2(), triangulo.getP3(), this);
        Triangulo t3 = new Triangulo(triangulo.getP3(), triangulo.getP1(), this);
        return triangulo.orientacion().equals(t1.orientacion()) && triangulo.orientacion().equals(t2.orientacion()) && triangulo.orientacion().equals(t3.orientacion());
    }
}
