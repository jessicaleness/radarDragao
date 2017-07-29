package logica;

import java.awt.Point;
import java.awt.geom.Point2D;

public class Ponto {

    Point2D ponto;
    Integer peso;

    public Ponto(Integer x, Integer y, Integer peso) {
        this.ponto = new Point(x, y);
        this.peso = peso;
    }

    public Ponto(Integer x, Integer y) {
        this.ponto = new Point(x, y);
        this.peso = 0;
    }

    public Point2D getPonto() {
        return ponto;
    }

    public Integer getX() {
        return (int) ponto.getX();
    }

    public Integer getY() {
        return (int) ponto.getY();
    }

    public void setX(Integer x) {
        this.ponto.setLocation(x, this.ponto.getY());
    }

    public void setY(Integer y) {
        this.ponto.setLocation(this.ponto.getX(), y);
    }

    public void setPoint(Integer x, Integer y) {
        this.ponto.setLocation(x, y);
    }

    public void setPonto(Point2D ponto) {
        this.ponto = ponto;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public boolean equals(Object object2) {
        return object2 instanceof Ponto && ponto.equals(((Ponto) object2).ponto);
    }
}
