/**
 * @file Pixel.java
 * @brief Classe représentant un pixel avec des coordonnées et une couleur.
 *
 * Cette classe définit un pixel individuel utilisé pour dessiner des formes.
 */

package PixelTracer;

import Shapes.*;

/**
 * @class Pixel
 * @brief Représente un pixel avec des coordonnées (x, y) et une couleur.
 */
public class Pixel {
    private int px; ///< Coordonnée X du pixel.
    private int py; ///< Coordonnée Y du pixel.
    private String color; ///< Couleur du pixel.

    /**
     * @brief Constructeur de la classe Pixel.
     * @param px Coordonnée X du pixel.
     * @param py Coordonnée Y du pixel.
     */
    public Pixel(int px, int py) {
        this.px = px;
        this.py = py;
    }

    /**
     * @brief Récupère la coordonnée X du pixel.
     * @return La coordonnée X du pixel.
     */
    public int getPx() {
        return px;
    }

    /**
     * @brief Récupère la coordonnée Y du pixel.
     * @return La coordonnée Y du pixel.
     */
    public int getPy() {
        return py;
    }

    /**
     * @brief Récupère la couleur du pixel.
     * @return La couleur du pixel sous forme de chaîne de caractères.
     */
    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return this.px + " " + this.py;
    }
}
