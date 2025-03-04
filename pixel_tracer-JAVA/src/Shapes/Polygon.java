/**
 * @file Polygon.java
 * @brief Classe représentant un polygone dans le système de dessin.
 *
 * Cette classe permet de créer et de dessiner un polygone défini par un ensemble de points.
 */

package Shapes;

import PixelTracer.Pixel;
import java.util.ArrayList;

/**
 * @class Polygon
 * @brief Représente un polygone défini par plusieurs points.
 */
public class Polygon extends Shape {
    private Point[] points; ///< Tableau des points définissant le polygone.

    /**
     * @brief Constructeur de la classe Polygon.
     * @param points Tableau des points constituant les sommets du polygone.
     */
    public Polygon(Point[] points) {
        super();
        this.points = points;
    }

    /**
     * @brief Dessine le polygone en générant une liste de pixels.
     * @return Une liste de pixels représentant le tracé du polygone.
     */
    @Override
    public ArrayList<Pixel> draw() {
        ArrayList<Pixel> pixelList = new ArrayList<>();
        for (int i = 0; i < points.length; i++) {
            Point p1 = points[i];
            Point p2 = points[(i + 1) % points.length]; // Connexion au point suivant, boucle sur le premier point
            drawSegment(p1.getX(), p1.getY(), p2.getX() - p1.getX(), p2.getY() - p1.getY(), pixelList);
        }
        return pixelList;
    }

    /**
     * @brief Retourne une représentation textuelle du polygone.
     * @return Une chaîne de caractères listant les sommets du polygone.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Polygon: ");
        for (Point p : points) {
            sb.append(p).append(" ");
        }
        return sb.toString();
    }
}
