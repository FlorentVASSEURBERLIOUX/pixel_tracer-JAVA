/**
 * @file Shape.java
 * @brief Classe abstraite représentant une forme géométrique.
 *
 * La classe `Shape` est une classe abstraite qui définit les propriétés de base d'une forme géométrique,
 * telles que l'ID, la couleur, l'épaisseur et la rotation. Elle fournit également des méthodes pour dessiner
 * des segments et accéder aux attributs de la forme.
 */

package Shapes;

import PixelTracer.Pixel;
import java.util.ArrayList;

/**
 * @class Shape
 * @brief Classe abstraite représentant une forme géométrique.
 *
 * La classe `Shape` est une classe de base pour les formes géométriques, définissant les propriétés communes
 * (ID, couleur, épaisseur, rotation) et une méthode abstraite `draw()` qui doit être implémentée par les classes
 * dérivées. Elle inclut également une méthode pour dessiner un segment de ligne en calculant les pixels
 * correspondants à l'aide de l'algorithme de tracé de Bresenham.
 */
public abstract class Shape {

    /** ID unique pour chaque forme créée. */
    protected static int nextId = 0;

    /** Identifiant de la forme. */
    protected int id;

    /** Couleur de la forme. */
    protected String color;

    /** Épaisseur de la forme. */
    protected double thickness;

    /** Rotation de la forme. */
    protected double rotation;

    /**
     * @brief Constructeur de la classe Shape.
     *
     * Initialise l'ID de la forme en incrémentant le compteur statique `nextId`.
     */
    public Shape() {
        id = nextId++;
    }

    /**
     * @brief Obtient la couleur de la forme.
     *
     * Cette méthode renvoie la couleur de la forme.
     *
     * @return String La couleur de la forme.
     */
    public String getColor() {
        return color;
    }

    /**
     * @brief Méthode abstraite pour dessiner la forme.
     *
     * Chaque sous-classe doit implémenter cette méthode pour dessiner la forme en retournant une liste de pixels.
     *
     * @return ArrayList<Pixel> Liste des pixels représentant la forme.
     */
    public abstract ArrayList<Pixel> draw();

    /**
     * @brief Dessine un segment de ligne entre deux points en utilisant l'algorithme de Bresenham.
     *
     * Cette méthode calcule les pixels d'un segment de ligne entre les coordonnées (x, y) et la direction (dx, dy)
     * et ajoute ces pixels à la liste fournie en paramètre.
     *
     * @param x Coordonnée x du point de départ du segment.
     * @param y Coordonnée y du point de départ du segment.
     * @param dx Changement en x du segment.
     * @param dy Changement en y du segment.
     * @param list Liste de pixels dans laquelle les pixels du segment seront ajoutés.
     */
    protected void drawSegment(int x, int y, int dx, int dy, ArrayList<Pixel> list) {
        int xinc = (dx > 0) ? 1 : -1;
        int yinc = (dy > 0) ? 1 : -1;
        dx = Math.abs(dx);
        dy = Math.abs(dy);

        // Ajoute le pixel initial
        list.add(new Pixel(x, y));

        if (dx > dy) {
            int cumul = dx / 2;
            for (int i = 1; i <= dx; i++) {
                x += xinc;
                cumul += dy;
                if (cumul >= dx) {
                    cumul -= dx;
                    y += yinc;
                }
                list.add(new Pixel(x, y));
            }
        } else {
            int cumul = dy / 2;
            for (int i = 1; i <= dy; i++) {
                y += yinc;
                cumul += dx;
                if (cumul >= dy) {
                    cumul -= dy;
                    x += xinc;
                }
                list.add(new Pixel(x, y));
            }
        }
    }

    /**
     * @brief Obtient l'ID de la forme.
     *
     * Cette méthode renvoie l'ID unique de la forme.
     *
     * @return int L'ID de la forme.
     */
    public int getId() {
        return id;
    }
}
