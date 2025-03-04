/**
 * @file Curve.java
 * @brief Classe représentant une courbe de Bézier cubique dans le système de dessin.
 *
 * Cette classe permet de créer et de dessiner une courbe de Bézier cubique
 * définie par quatre points de contrôle.
 */

package Shapes;

import PixelTracer.Pixel;
import java.util.ArrayList;

/**
 * @class Curve
 * @brief Représente une courbe de Bézier cubique définie par quatre points.
 */
public class Curve extends Shape {
    private Point p1; ///< Premier point de contrôle de la courbe.
    private Point p2; ///< Deuxième point de contrôle de la courbe.
    private Point p3; ///< Troisième point de contrôle de la courbe.
    private Point p4; ///< Quatrième point de contrôle de la courbe.

    /**
     * @brief Constructeur de la classe Curve.
     * @param p1 Premier point de contrôle.
     * @param p2 Deuxième point de contrôle.
     * @param p3 Troisième point de contrôle.
     * @param p4 Quatrième point de contrôle.
     */
    public Curve(Point p1, Point p2, Point p3, Point p4) {
        super();
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
    }

    /**
     * @brief Dessine la courbe de Bézier cubique en générant une liste de pixels.
     * @return Une liste de pixels représentant le tracé de la courbe.
     */
    @Override
    public ArrayList<Pixel> draw() {
        ArrayList<Pixel> pixelList = new ArrayList<>();
        for (double t = 0; t <= 1; t += 0.01) { // Itération sur t de 0 à 1 pour tracer la courbe
            int x = (int) ((1 - t) * (1 - t) * (1 - t) * p1.getX() +
                    3 * (1 - t) * (1 - t) * t * p2.getX() +
                    3 * (1 - t) * t * t * p3.getX() +
                    t * t * t * p4.getX());

            int y = (int) ((1 - t) * (1 - t) * (1 - t) * p1.getY() +
                    3 * (1 - t) * (1 - t) * t * p2.getY() +
                    3 * (1 - t) * t * t * p3.getY() +
                    t * t * t * p4.getY());

            pixelList.add(new Pixel(x, y)); // Ajouter chaque pixel calculé
        }
        return pixelList;
    }

    /**
     * @brief Retourne une représentation textuelle de la courbe.
     * @return Une chaîne de caractères décrivant la courbe.
     */
    @Override
    public String toString() {
        return "Curve: p1=" + p1 + ", p2=" + p2 + ", p3=" + p3 + ", p4=" + p4;
    }
}
