/**
 * @file Circle.java
 * @brief Classe représentant un cercle dans le système de dessin.
 *
 * Cette classe permet de créer et de dessiner un cercle en utilisant l'algorithme de tracé
 * basé sur la symétrie des octants.
 */

package Shapes;

import PixelTracer.Pixel;
import java.util.ArrayList;

/**
 * @class Circle
 * @brief Représente un cercle défini par un centre et un rayon.
 */
public class Circle extends Shape {
    private Point center; ///< Centre du cercle.
    private int radius; ///< Rayon du cercle.

    /**
     * @brief Constructeur de la classe Circle.
     * @param center Point central du cercle.
     * @param radius Rayon du cercle.
     */
    public Circle(Point center, int radius) {
        super();
        this.center = center;
        this.radius = radius;
    }

    /**
     * @brief Dessine le cercle en générant une liste de pixels.
     * @return Une liste de pixels représentant le tracé du cercle.
     */
    @Override
    public ArrayList<Pixel> draw() {
        ArrayList<Pixel> pixelList = new ArrayList<>();
        int x = 0;
        int y = radius;
        int d = radius - 1;

        while (y >= x) {
            addCirclePixels(center.getX(), center.getY(), x, y, pixelList);
            if (d >= 2 * x) {
                d -= 2 * x + 1;
                x++;
            } else if (d < 2 * (radius - y)) {
                d += 2 * y - 1;
                y--;
            } else {
                d += 2 * (y - x - 1);
                y--;
                x++;
            }
        }
        return pixelList;
    }

    /**
     * @brief Ajoute les pixels d'un cercle en exploitant la symétrie des octants.
     * @param centerX Coordonnée X du centre du cercle.
     * @param centerY Coordonnée Y du centre du cercle.
     * @param x Coordonnée X d'un point du cercle par rapport au centre.
     * @param y Coordonnée Y d'un point du cercle par rapport au centre.
     * @param pixelList Liste de pixels à compléter.
     */
    private void addCirclePixels(int centerX, int centerY, int x, int y, ArrayList<Pixel> pixelList) {
        // Ajouter les pixels dans les 8 octants du cercle
        pixelList.add(new Pixel(centerX + x, centerY + y));
        pixelList.add(new Pixel(centerX + y, centerY + x));
        pixelList.add(new Pixel(centerX - x, centerY + y));
        pixelList.add(new Pixel(centerX - y, centerY + x));
        pixelList.add(new Pixel(centerX + x, centerY - y));
        pixelList.add(new Pixel(centerX + y, centerY - x));
        pixelList.add(new Pixel(centerX - x, centerY - y));
        pixelList.add(new Pixel(centerX - y, centerY - x));
    }

    /**
     * @brief Retourne une représentation textuelle du cercle.
     * @return Une chaîne de caractères décrivant le cercle.
     */
    @Override
    public String toString() {
        return "Circle: center=" + center + ", radius=" + radius;
    }
}
