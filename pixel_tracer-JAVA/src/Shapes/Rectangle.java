/**
 * @file Rectangle.java
 * @brief Classe représentant un rectangle dans un système de dessin.
 *
 * Cette classe hérite de la classe `Shape` et permet de représenter un rectangle défini par un centre,
 * une largeur et une hauteur. Elle inclut des méthodes pour dessiner le rectangle et afficher ses propriétés.
 */

package Shapes;

import PixelTracer.Pixel;
import java.util.ArrayList;

/**
 * @class Rectangle
 * @brief Représente un rectangle dans un système de dessin.
 *
 * La classe Rectangle étend la classe Shape et permet de dessiner un rectangle à partir d'un centre, d'une largeur
 * et d'une hauteur spécifiés. Elle utilise les objets `Point` pour définir les coins du rectangle et la méthode
 * `draw()` pour retourner une liste de pixels représentant le rectangle.
 */
public class Rectangle extends Shape {

    /** Point central du rectangle. */
    private Point center;

    /** Largeur du rectangle. */
    private int width;

    /** Hauteur du rectangle. */
    private int height;

    /**
     * @brief Constructeur de la classe Rectangle.
     *
     * Crée un rectangle avec les coordonnées de son centre, sa largeur et sa hauteur.
     *
     * @param center Point représentant le centre du rectangle.
     * @param width Largeur du rectangle.
     * @param height Hauteur du rectangle.
     */
    public Rectangle(Point center, int width, int height) {
        super();
        this.center = center;
        this.width = width;
        this.height = height;
    }

    /**
     * @brief Dessine le rectangle.
     *
     * Cette méthode dessine le rectangle en calculant les pixels des quatre segments qui
     * composent les bords du rectangle. Elle renvoie une liste de pixels qui représentent
     * visuellement le rectangle.
     *
     * @return ArrayList<Pixel> Liste des pixels représentant le rectangle.
     */
    @Override
    public ArrayList<Pixel> draw() {
        ArrayList<Pixel> pixelList = new ArrayList<>();

        // Définir les coins du rectangle
        Point p1 = new Point(center.getX() - width / 2, center.getY() - height / 2);
        Point p2 = new Point(center.getX() + width / 2, center.getY() - height / 2);
        Point p3 = new Point(center.getX() + width / 2, center.getY() + height / 2);
        Point p4 = new Point(center.getX() - width / 2, center.getY() + height / 2);

        // Dessiner les quatre segments du rectangle
        drawSegment(p1.getX(), p1.getY(), p2.getX() - p1.getX(), p2.getY() - p1.getY(), pixelList);
        drawSegment(p2.getX(), p2.getY(), p3.getX() - p2.getX(), p3.getY() - p2.getY(), pixelList);
        drawSegment(p3.getX(), p3.getY(), p4.getX() - p3.getX(), p4.getY() - p3.getY(), pixelList);
        drawSegment(p4.getX(), p4.getY(), p1.getX() - p4.getX(), p1.getY() - p4.getY(), pixelList);

        return pixelList;
    }

    /**
     * @brief Retourne une représentation textuelle du rectangle.
     *
     * Cette méthode retourne une chaîne de caractères représentant le rectangle avec
     * ses coordonnées de centre, sa largeur et sa hauteur.
     *
     * @return String Représentation textuelle du rectangle.
     */
    @Override
    public String toString() {
        return "Rectangle: center=" + center + ", width=" + width + ", height=" + height;
    }
}
