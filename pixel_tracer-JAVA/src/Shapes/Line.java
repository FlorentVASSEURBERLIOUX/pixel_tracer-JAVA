/**
 * @file Line.java
 * @brief Classe représentant une ligne dans le système de dessin.
 *
 * Cette classe permet de créer et de dessiner une ligne entre deux points.
 */

package Shapes;

import PixelTracer.Pixel;
import java.util.ArrayList;

/**
 * @class Line
 * @brief Représente une ligne définie par deux points de départ et d'arrivée.
 */
public class Line extends Shape {
    private Point p1; ///< Point de départ de la ligne.
    private Point p2; ///< Point d'arrivée de la ligne.

    /**
     * @brief Constructeur de la classe Line.
     * @param p1 Point de départ de la ligne.
     * @param p2 Point d'arrivée de la ligne.
     */
    public Line(Point p1, Point p2) {
        super();
        this.p1 = p1;
        this.p2 = p2;
    }

    /**
     * @brief Dessine la ligne en générant une liste de pixels.
     * @return Une liste de pixels représentant le tracé de la ligne.
     */
    @Override
    public ArrayList<Pixel> draw() {
        ArrayList<Pixel> pixelList = new ArrayList<>();
        drawSegment(p1.getX(), p1.getY(), p2.getX() - p1.getX(), p2.getY() - p1.getY(), pixelList);
        return pixelList;
    }

    /**
     * @brief Retourne une représentation textuelle de la ligne.
     * @return Une chaîne de caractères décrivant la ligne.
     */
    @Override
    public String toString() {
        return "Line: " + p1 + " to " + p2;
    }
}
