/**
 * @file Point.java
 * @brief Classe représentant un point dans le système de dessin.
 *
 * Cette classe permet de créer un point défini par ses coordonnées (x, y).
 */

package Shapes;

import PixelTracer.Pixel;
import java.util.ArrayList;

/**
 * @class Point
 * @brief Représente un point avec des coordonnées (x, y).
 */
public class Point extends Shape {
    private int x; ///< Coordonnée X du point.
    private int y; ///< Coordonnée Y du point.

    /**
     * @brief Constructeur de la classe Point.
     * @param x Coordonnée X du point.
     * @param y Coordonnée Y du point.
     */
    public Point(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    /**
     * @brief Dessine le point en générant une liste de pixels.
     * @return Une liste contenant un seul pixel représentant le point.
     */
    @Override
    public ArrayList<Pixel> draw() {
        ArrayList<Pixel> pixelList = new ArrayList<>();
        pixelList.add(new Pixel(x, y)); // Ajout du pixel correspondant au point
        return pixelList;
    }

    /**
     * @brief Récupère la coordonnée X du point.
     * @return La coordonnée X.
     */
    public int getX() {
        return x;
    }

    /**
     * @brief Récupère la coordonnée Y du point.
     * @return La coordonnée Y.
     */
    public int getY() {
        return y;
    }

    /**
     * @brief Retourne une représentation textuelle du point.
     * @return Une chaîne de caractères représentant le point sous forme "(x, y)".
     */
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
