/**
 * @file Layer.java
 * @brief Classe représentant une couche (Layer) contenant des formes.
 *
 * Cette classe permet de gérer une couche de dessin qui peut contenir plusieurs formes.
 * Chaque couche possède un identifiant unique, un nom et une visibilité.
 */

package Layers;

import Shapes.Shape;
import java.util.ArrayList;

/**
 * @class Layer
 * @brief Représente une couche contenant plusieurs formes.
 */
public class Layer {
    private static int nextId = 0; ///< ID unique généré automatiquement pour chaque couche.
    private int id; ///< Identifiant unique de la couche.
    private String name; ///< Nom de la couche.
    private ArrayList<Shape> shapes; ///< Liste des formes contenues dans la couche.
    public boolean visible; ///< Indique si la couche est visible ou non.

    /**
     * @brief Constructeur de la classe Layer.
     * @param name Nom de la couche.
     */
    public Layer(String name) {
        this.id = nextId++;
        this.name = name;
        this.shapes = new ArrayList<>();
        this.visible = true;
    }

    /**
     * @brief Supprime toutes les formes contenues dans la couche.
     */
    public void deleteLayer() {
        shapes.clear();
    }

    /**
     * @brief Vérifie si la couche est visible.
     * @return true si la couche est visible, false sinon.
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * @brief Modifie la visibilité de la couche.
     * @param visible true pour rendre la couche visible, false pour la masquer.
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * @brief Récupère la liste des formes contenues dans la couche.
     * @return Liste des formes contenues dans la couche.
     */
    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    /**
     * @brief Ajoute une forme à la couche.
     * @param shape La forme à ajouter.
     */
    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    /**
     * @brief Supprime une forme de la couche.
     * @param shape La forme à supprimer.
     */
    public void removeShape(Shape shape) {
        shapes.remove(shape);
    }

    /**
     * @brief Récupère l'identifiant unique de la couche.
     * @return L'identifiant unique de la couche.
     */
    public int getId() {
        return id;
    }

    /**
     * @brief Récupère le nom de la couche.
     * @return Le nom de la couche.
     */
    public String getName() {
        return name;
    }
}
